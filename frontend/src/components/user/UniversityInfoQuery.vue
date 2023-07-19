<script lang="ts">
import { provinces } from '@/const';
import axios from 'axios';
import { defineComponent } from 'vue';

type UniversityList = {
  uid: number,
  uname: string,
  utype: string,
  pname: string,
  upopularity: number
}

type UniversityDetail = {
  sexRatio: number,
  employRate: number,
  shipmentRate: number,
  enrollmentRate: number,
  uprofile: string,
}

type ComparisonData = {
  uname: string,
  sexRatio: string,
  employRate: string,
  shipmentRate: string,
  enrollmentRate: string,
}

type EnrollmentPlan = {
  uid: number,
  pname: string,
  subject: string,
  major: string,
  year: number,
  enrollmentNum: number,
}

type MPS = {
  year: number,
  umps: number,
  major: string,
  rank: number,
  subject: string,
  pname: string,
}

export default defineComponent({
  data() {
    return {
      universityList: [] as UniversityList[],
      universitySimpleList: [] as {
        uid: number,
        uname: string,
      }[],
      filteredUList: [] as UniversityList[],
      universityDetail: {} as UniversityDetail,
      uniEnrollmentPlan: [] as EnrollmentPlan[],
      uniMps: [] as MPS[],
      selectedUname: "",
      selectedUid: 0,
      provinces: provinces,
      conditions: {
        uname: "",
        pname: "不限",
        is985: false,
        reorder: false,
      },
      badgePath: "",
      landscapePath: "",
      // 对话框控制变量
      enrollmentDialogVisible: false,
      mpsDialogVisible: false,
      comparisonDialogVisible: false,
      rivalUName: "",
      comparisonData: [] as ComparisonData[],
    }
  },
  methods: {
    async getUniversityList() {
      try {
        const data = await axios.get('/api/info/university')
        this.universityList = data.data
        this.universitySimpleList = this.universityList.map(x => {
          return { uid: x.uid, uname: x.uname }
        })
      } catch (error: any) {
        this.$message.error("大学列表获取失败")
      }
    },
    async getUniversityInfo() {
      try {
        const data = await axios.get(`/api/info/university/${this.selectedUid}`)
        this.universityDetail = data.data
      } catch (error: any) {
        this.$message.error("大学信息获取失败")
      }
    },
    async selectUniversity(uid: number) {
      this.selectedUid = uid;
      this.selectedUname = this.universityList[uid - 1].uname
      await this.getUniversityInfo()
      await this.getEnrollmentPlan()
      await this.getMpsList()
      this.getImageUri()
    },
    async getEnrollmentPlan() {
      try {
        const data = await axios.get(`/api/info/university/${this.selectedUid}/admission`)
        this.uniEnrollmentPlan = data.data;
      } catch (error: any) {
        this.$message.error("招生计划获取失败: " + error)
      }
    },
    async getMpsList() {
      try {
        const data = await axios.get(`/api/info/university/${this.selectedUid}/mps`)
        this.uniMps = data.data;
      } catch (error: any) {
        this.$message.error("分数线获取失败: " + error)
      }
    },
    setFilter() {
      this.filteredUList = this.universityList.filter(x => {
        return ((this.conditions.pname === "不限" || x.pname === this.conditions.pname) &&
          (!this.conditions.is985 || x.utype === "985") &&
          (x.uname.includes(this.conditions.uname.trim())))
      })
      if (this.conditions.reorder) {
        this.filteredUList.sort((a, b) => b.upopularity - a.upopularity)
      }
    },
    cleanCondition() {
      this.conditions = {
        uname: "",
        pname: "不限",
        is985: false,
        reorder: false,
      }
      this.filteredUList = this.universityList
    },
    getSexRatioOption() {
      const male = this.universityDetail.sexRatio
      const female = 1 - male
      const ratio = (male / female).toFixed(2)
      return {
        title: {
          text: `男女比例\n${ratio}:1`,
          left: 'center',
          top: 'center'
        },
        series: [
          {
            type: 'pie',
            data: [
              {
                name: "男",
                value: male,
                itemStyle: { color: '#566EC4' },
              },
              {
                name: "女",
                value: female,
                itemStyle: { color: '#EE6A6C' },
              }
            ],
            radius: ['40%', '70%']
          }
        ],
      }
    },
    getEmployRatioOption() {
      const employ = this.universityDetail.employRate
      const unemploy = 1 - employ
      const ratio = (employ * 100).toFixed(1)
      return {
        title: {
          text: `就业率\n${ratio}%`,
          left: 'center',
          top: 'center'
        },
        series: [
          {
            type: 'pie',
            data: [
              {
                name: "就业",
                value: employ,
                itemStyle: { color: '#566EC4' },
              },
              {
                name: "未就业",
                value: unemploy,
                itemStyle: { color: '#EE6A6C' },
              }
            ],
            radius: ['40%', '70%']
          }
        ],
      }
    },
    getShipmentRatioOption() {
      const shipment = this.universityDetail.shipmentRate
      const unshipment = 1 - shipment
      const ratio = (shipment * 100).toFixed(1)
      return {
        title: {
          text: `出国率\n${ratio}%`,
          left: 'center',
          top: 'center'
        },
        series: [
          {
            type: 'pie',
            data: [
              {
                name: "选择出国",
                value: shipment,
                itemStyle: { color: '#566EC4' },
              },
              {
                name: "不出国",
                value: unshipment,
                itemStyle: { color: '#EE6A6C' },
              }
            ],
            radius: ['40%', '70%']
          }
        ],
      }
    },
    getEnrollmentRatioOption() {
      const enrollment = this.universityDetail.enrollmentRate
      const unenrollment = 1 - enrollment
      const ratio = (enrollment * 100).toFixed(1)
      return {
        title: {
          text: `升学率\n${ratio}%`,
          left: 'center',
          top: 'center'
        },
        series: [
          {
            type: 'pie',
            data: [
              {
                name: "升学",
                value: enrollment,
                itemStyle: { color: '#566EC4' },
              },
              {
                name: "就业",
                value: unenrollment,
                itemStyle: { color: '#EE6A6C' },
              }
            ],
            radius: ['40%', '70%']
          }
        ],
      }
    },
    getImageUri() {
      this.badgePath = `../../src/assets/badge/${this.selectedUid}.jpg`
      this.landscapePath = `../../src/assets/landscape/${this.selectedUid}.png`
    },
    yearFilterHandler<T extends { year: number }>(data: T[]) {
      const years = Array.from(new Set(data.map(x => x.year)));
      return years.map(x => { return { text: x.toString(), value: x.toString() } });
    },
    querySearch(queryString: string, cb: any) {
      let unis = this.universitySimpleList.map(x => {
        return {
          value: x.uname
        }
      })
      unis.splice(this.selectedUid - 1, 1)
      const results = queryString
        ? unis.filter(x => x.value.includes(queryString))
        : unis
      cb(results)
    },
    async handleSelect(item: any) {
      this.comparisonData.splice(0, this.comparisonData.length)
      const rivalUid = this.universitySimpleList.find(x => x.uname === item.value)?.uid
      const { sexRatio, employRate, shipmentRate, enrollmentRate } = this.universityDetail
      let rivalDetail = {} as any
      try {
        const data = await axios.get(`/api/info/university/${rivalUid}`)
        rivalDetail = data.data        
      } catch (error: any) {
        this.$message.error("大学信息获取失败")
        return
      }

      const _sexRatio = (sexRatio / (1 - sexRatio)).toFixed(2)
      this.comparisonData.push({
        uname: this.universitySimpleList[this.selectedUid - 1].uname,
        sexRatio: `${_sexRatio}:1`,
        employRate: `${(employRate * 100).toFixed(1)}%`,
        shipmentRate: `${(shipmentRate * 100).toFixed(1)}%`,
        enrollmentRate: `${(enrollmentRate * 100).toFixed(1)}%`
      })
      
      const rivalSexRatio = rivalDetail.sexRatio as number
      const __sexRatio = (rivalSexRatio / (1 - rivalSexRatio)).toFixed(2)
      this.comparisonData.push({
        uname: rivalDetail.uname,
        sexRatio: `${__sexRatio}:1`,
        employRate: `${(rivalDetail.employRate * 100).toFixed(1)}%`,
        shipmentRate: `${(rivalDetail.shipmentRate * 100).toFixed(1)}%`,
        enrollmentRate: `${(rivalDetail.enrollmentRate * 100).toFixed(1)}%`
      })
    }
  },
  async created() {
    await this.getUniversityList()
    this.filteredUList = this.universityList
  }
})
</script>


<template>
  <div class="common-layout">
    <el-container style="padding-top: 20px; max-height: 90vh;">
      <el-aside width="300px" style="border-right: 1px gray solid;">
        <div class="form">
          <el-form :model="conditions">
            <el-form-item label="大学名">
              <el-input v-model="conditions.uname" />
            </el-form-item>
            <el-form-item label="所在省份">
              <el-select v-model="conditions.pname" placeholder="不限">
                <el-option label="不限" value="不限" />
                <el-option v-for="(item, _) in provinces" :label="item" :value="item" />
              </el-select>
            </el-form-item>
            <el-checkbox v-model="conditions.is985" label="985" />
            <el-checkbox v-model="conditions.reorder" label="按热度排序" style="margin-bottom: 20px;" />
            <el-button style="margin-left: 45px;" @click="cleanCondition">重置</el-button>
            <el-button style="margin-left: 45px;" type="primary" @click="setFilter">筛选</el-button>
            <el-divider></el-divider>
          </el-form>
        </div>
        <ul>
          <li>
            <UniversityListCard v-for="(university, index) in filteredUList" :key="index" :uname="university.uname"
              :utype="university.utype" :pname="university.pname" :upopularity="university.upopularity"
              @click="selectUniversity(university.uid)" />
          </li>
        </ul>
      </el-aside>
      <el-main>
        <div v-if="selectedUid !== 0">
          <el-row style="background-color: rgb(247, 247, 247);">
            <el-col :span="24">
              <img :src="landscapePath" class="landscape" onerror="src='../../src/assets/landscape/fallback.png'" />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-image :src="badgePath" class="badge">
                <template #error>
                  <div class="image-slot">
                    <el-image src="../../src/assets/landscape/fallback.png" fit="scale-down" />
                  </div>
                </template>
              </el-image>
            </el-col>
            <el-col :span="9">
              <p style="font-size: 250%; padding-left: 20px; font-weight: bold;">{{ universityList[selectedUid - 1].uname
              }}</p>
            </el-col>
            <el-col :span="3" style="text-align: right;">
              <div style="margin-top: 60px;">
                <span class="tag t-985" v-if="universityList[selectedUid - 1].utype === '985'">{{
                  universityList[selectedUid - 1].utype }}</span>
                <span class="tag t-211" v-else>{{
                  universityList[selectedUid - 1].utype }}</span>
                <span class="tag">{{ universityList[selectedUid - 1].pname }}</span>
              </div>
            </el-col>
            <el-col :span="6" style="text-align: right;">
              <el-button style="margin-top: 55.5px;" @click="enrollmentDialogVisible = true">招生计划</el-button>
              <el-button style="margin-top: 55.5px;" @click="mpsDialogVisible = true">分数线</el-button>
              <el-button type="primary" style="margin-top: 55.5px;" @click="comparisonDialogVisible = true">对比</el-button>
            </el-col>
          </el-row>
          <div v-if="universityDetail.sexRatio !== null">
            <p class="profile">{{ universityDetail.uprofile }}</p>
            <el-row>
              <el-col :span="12">
                <base-echart :option="getSexRatioOption()" width="400px" />
              </el-col>
              <el-col :span="12">
                <base-echart :option="getEmployRatioOption()" width="400px" />
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <base-echart :option="getShipmentRatioOption()" width="400px" />
              </el-col>
              <el-col :span="12">
                <base-echart :option="getEnrollmentRatioOption()" width="400px" />
              </el-col>
            </el-row>
          </div>
          <div v-else style="text-align: center; margin-top: 150px;">
            <strong style="font-size: 300%;">暂无数据</strong>
          </div>
        </div>
        <div v-else>
          👈从左边选择一个学校吧！
        </div>
      </el-main>
    </el-container>
  </div>

  <el-dialog v-model="enrollmentDialogVisible" :title="selectedUname">
    <el-table :data="uniEnrollmentPlan" max-height="700px">
      <el-table-column prop="pname" label="省份" :filters="provinces.map(x => { return { text: x, value: x } })"
        :filter-method="(value: string, row: EnrollmentPlan) => row.pname === value" />
      <el-table-column prop="year" label="年份" :filters="yearFilterHandler(uniEnrollmentPlan)"
        :filter-method="(value: string, row: EnrollmentPlan) => value === row.year.toString()" />
      <el-table-column prop="subject" label="文理" :filters="[
        { text: '文科', value: '文科' }, { text: '理科', value: '理科' }]"
        :filter-method="(value: string, row: EnrollmentPlan) => value === row.subject" />
      <el-table-column prop="major" label="专业" />
      <el-table-column prop="enrollmentNum" label="招生数量" />
    </el-table>
  </el-dialog>

  <!--  -->
  <el-dialog v-model="mpsDialogVisible" :title="selectedUname">
    <el-table :data="uniMps" max-height="700px">
      <el-table-column prop="pname" label="省份" :filters="provinces.map(x => { return { text: x, value: x } })"
        :filter-method="(value: string, row: EnrollmentPlan) => { return row.pname === value }" />
      <el-table-column prop="year" label="年份" :filters="yearFilterHandler(uniMps)"
        :filter-method="(value: string, row: EnrollmentPlan) => value === row.year.toString()" />
      <el-table-column prop="subject" label="文理" :filters="[
        { text: '文科', value: '文科' }, { text: '理科', value: '理科' }]"
        :filter-method="(value: string, row: EnrollmentPlan) => value === row.subject" />
      <el-table-column prop="major" label="专业" />
      <el-table-column prop="umps" label="最低分" sortable />
      <el-table-column prop="rank" label="最低位次" />
    </el-table>
  </el-dialog>

  <!-- 大学对比 -->
  <el-dialog v-model="comparisonDialogVisible" :title="'与' + selectedUname + '对比'">
    <el-form>
      <el-form-item label="对比学校">
        <el-autocomplete v-model="rivalUName" :fetch-suggestions="querySearch" :trigger-on-focus="false" clearable
          placeholder="请输入大学名" @select="handleSelect" />
      </el-form-item>
    </el-form>
    <el-table :data="comparisonData">
      <el-table-column prop="uname" label="学校名" />
      <el-table-column prop="sexRatio" label="男女比例" />
      <el-table-column prop="employRate" label="就业率" />
      <el-table-column prop="shipmentRate" label="出国率" />
      <el-table-column prop="enrollmentRate" label="升学率" />
    </el-table>
  </el-dialog>
</template>

<style scoped>
ul {
  overflow: auto;
  margin-block-start: 0;
  margin-block-end: 0;
  padding-inline-start: 0;
  height: 63vh;
}

li {
  list-style: none;
}

.tag {
  background-color: rgb(53, 141, 241);
  color: white;
  border-radius: 4px;
  padding: 5px;
  padding-left: 10px;
  padding-right: 10px;
  font-size: 90%;
  margin: 5px;
}

.t-985 {
  background-color: rgb(0, 67, 145);
}

.t-211 {
  background-color: rgb(85, 155, 234);
}

.form {
  margin-left: 20px;
  margin-right: 20px;
}

.badge {
  margin-left: 40px;
  margin-top: -100px;
  border-radius: 16px;
  box-shadow: 1px 3px 3px gray;
  height: 200px;
  width: 200px;
}

.landscape {
  max-height: 150px;
  width: 100%;
  object-fit: cover;
  object-position: center;
}

.profile {
  background-color: rgb(241, 241, 241);
  padding: 10px;
  text-indent: 2em;
  border-radius: 10px;
}
</style>
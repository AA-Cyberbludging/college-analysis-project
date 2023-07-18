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

type EnrollmentPlan = {
  uid: number,
  pname: string,
  subject: string,
  major: string,
  year: number,
  enrollmentNum: number,
}

export default defineComponent({
  data() {
    return {
      universityList: [] as UniversityList[],
      universityDetail: {} as UniversityDetail,
      uniEnrollmentPlan: [] as EnrollmentPlan[],
      selectedUname: "",
      selectedUid: 0,
      provinces: provinces,
      conditions: {
        uname: "",
        pname: "不限",
        is985: false,
        reorder: false,
      },
      filteredUList: [] as UniversityList[],
      badgePath: "",
      enrollmentDialogVisible: false,
    }
  },
  methods: {
    async getUniversityList() {
      try {
        const data = await axios.get('/api/display/university')
        this.universityList = data.data
      } catch (error: any) {
        this.$message.error("大学列表获取失败")
      }
    },
    async getUniversityInfo() {
      try {
        const data = await axios.get(`/api/display/university/${this.selectedUid}`)
        this.universityDetail = data.data
        console.log(this.universityDetail)
      } catch (error: any) {
        this.$message.error("大学信息获取失败")
      }
    },
    async selectUniversity(uid: number) {
      this.selectedUid = uid;
      this.selectedUname = this.universityList[uid - 1].uname
      await this.getUniversityInfo()
      await this.getEnrollmentPlan()
      this.getImageUri()
    },
    async getEnrollmentPlan() {
      try {
        const data = await axios.get(`/api/display/university/${this.selectedUid}/admission`)
        this.uniEnrollmentPlan = data.data;
      } catch (error: any) {
        this.$message.error("招生计划获取失败: " + error)
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
    },
  },
  async created() {
    await this.getUniversityList()
    this.filteredUList = this.universityList
  },
  computed: {

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
              <el-input v-model="conditions.uname"></el-input>
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
            <el-col>
              <el-image src="../../src/assets/landscape/1.jpg" fit="scale-down" style="max-height: 100px;">

              </el-image>
              <!-- <img src="@/assets/landscape/1.jpg" style="height: 100px; object-fit: fill;" /> -->
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-image :src="badgePath" class="badge">
                <template #error>
                  <div class="image-slot">
                    <el-image src="../../src/assets/badge/fallback.jpg" fit="scale-down" />
                  </div>
                </template>
              </el-image>
            </el-col>
            <el-col :span="12">
              <p style="font-size: 250%; padding-left: 20px; padding-top: 20px;">{{ universityList[selectedUid - 1].uname
              }}</p>
            </el-col>
            <el-col :span="3">
              <div style="margin-top: 76px;">
                <span class="tag">{{ universityList[selectedUid - 1].utype }}</span>
                <span class="tag">{{ universityList[selectedUid - 1].pname }}</span>
              </div>
            </el-col>
            <el-col :span="3">
              <el-button type="primary" style="margin-top: 71px;" @click="enrollmentDialogVisible = true">招生计划</el-button>
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
      <el-table-column prop="pname" label="省份"/>
      <el-table-column prop="year" label="年份"/>
      <el-table-column prop="subject" label="文理"/>
      <el-table-column prop="major" label="专业"/>
      <el-table-column prop="enrollmentNum" label="招生数量"/>
    </el-table>

  </el-dialog>
</template>

<style scoped>
ul {
  overflow: auto;
  margin-block-start: 0;
  margin-block-end: 0;
  padding-inline-start: 0;
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

.form {
  margin-left: 20px;
  margin-right: 20px;
}

.badge {
  margin-left: 40px;
  margin-top: -60px;
  border-radius: 16px;
  box-shadow: 1px 3px 3px gray;
  height: 200px;
  width: 200px;
}

.profile {
  background-color: rgb(241, 241, 241);
  padding: 10px;
  text-indent: 2em;
  border-radius: 10px;
}
</style>

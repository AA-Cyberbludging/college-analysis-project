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

export default defineComponent({
  data() {
    return {
      universityList: [] as UniversityList[],
      universityDetail: {} as UniversityDetail,
      selectedUid: 0,
      provinces: provinces,
      conditions: {
        uname: "",
        pname: "不限",
        is985: false,
        reorder: false,
      },
      filteredUList: [] as UniversityList[],
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

      } catch (error: any) {
        this.$message.error("大学信息获取失败")
      }
    },
    async selectUniversity(index: number) {
      this.selectedUid = index;
      await this.getUniversityInfo()
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
  <div class="container">
    <div class="left">
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
    </div>
    <div class="right">
      <div v-if="selectedUid !== 0">
        <h2>{{ universityList[selectedUid - 1].uname }}</h2>
        <p>{{ universityList[selectedUid - 1].utype }}</p>
        <p>{{ universityList[selectedUid - 1].pname }}</p>

        <p>{{ universityDetail.uprofile }}</p>
        <base-echart :option="getSexRatioOption()"></base-echart>
        <base-echart :option="getEmployRatioOption()"></base-echart>
        <base-echart :option="getShipmentRatioOption()"></base-echart>
        <base-echart :option="getEnrollmentRatioOption()"></base-echart>
      </div>
      <div v-else>
        没有数据哦!
      </div>
    </div>
  </div>
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

.container {
  display: flex;
  flex-direction: row;
}

.left {
  background-color: white;
  width: 30%;
  max-width: 300px;
  min-width: 300px;
}

.right {
  background-color: rgb(250, 250, 250);
  flex: 1;
}

.form {
  margin-left: 20px;
  margin-right: 20px;
}
</style>

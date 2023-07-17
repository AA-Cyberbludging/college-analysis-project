<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';

type Province = {
  pname: string,
  unum: number
}

type Popularity = {
  uid: number,
  uname: string,
  upopularity: number
}
export default defineComponent({
  data() {
    return {
      provinceList: [] as Province[],
      popularityList: [] as Popularity[],
    }
  },
  methods: {
    async getProvinceList() {
      try {
        const data = await axios.get('/api/display/province')
        this.provinceList = data.data
      } catch (error: any) {
        this.$message.error("省份列表获取失败")
      }
    },
    getProvinceListOption() {
      return {

      }
    },
    async getPopularityList() {
      try {
        const data = await axios.get('/api/display/university/popularity')
        this.popularityList = data.data
      } catch (error: any) {
        this.$message.error("热度列表获取失败")
      }
    },
    getPopularityListOption() {
      const axisList = this.popularityList.map(x => x.uname).reverse()
      const dataList = this.popularityList.map(x => x.upopularity).reverse()
      return {
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        title: [
          {
            text: "大学热度排名",
            left: 'center'
          }
        ],
        xAxis: {
          type: "value",
          max: 'dataMax',
          axisLabel: { show: false }
        },
        yAxis: {
          type: "category",
          data: axisList,
        },
        series: [
          {
            type: "bar",
            data: dataList,
            itemStyle: {
              color: '#3165BE'
            },
            label: {
              show: true,
              position: 'inside',
              color: 'white'
            }
          }
        ]
      }
    }
  },
  async created() {
    await this.getProvinceList()
    await this.getPopularityList()
  }
})
</script>

<template>
  <div class="container">
    <div class="column left">

    </div>
    <div class="column middle"></div>
    <div class="column right">
      <div class="chart-grid">
        <base-echart :option="getPopularityListOption()" width="130" style="padding-top: 20px;" />
      </div>
    </div>

  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: row;
  height: 100vh;
  /* 清除浮动 */
}

.column {
  /* 设置高度，便于查看效果 */
  float: left;
  /* 设置浮动 */
}

.left {
  width: 27%;
  /* 左侧栏宽度 */
  background-color: #ccc;
}

.middle {
  width: 46%;
  /* 中间栏宽度 */
  background-color: #ddd;
}

.right {
  width: 27%;
  /* 右侧栏宽度 */
  background-color: #ccc;
}

.chart-grid {
  background-color: burlywood;
  margin: 10px;
}
</style>

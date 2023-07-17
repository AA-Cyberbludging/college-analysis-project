<script lang="ts">
import axios from 'axios';
import * as echarts from 'echarts';
import "echarts";
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
      mapLoaded: false,
    }
  },
  methods: {
    async registerMap() {
      const mapRawData = await axios.get('https://geojson.cn/api/data/china.json')
      echarts.registerMap('china', mapRawData.data)
      this.mapLoaded = true
    },
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
    getMapChartOption() {
      const dataList = this.provinceList.map(x => {
        return {
          name: x.pname,
          value: x.unum
        }
      })
      return {
        title: {
          name: "test",
          text: "中国 985/211 高校分布",
          left: "center"
        },
        tooltip: {
          trigger: 'item',
          showDelay: 0,
          transitionDuration: 0.2
        },
        series: [{
          type: 'map',
          map: 'china',
          data: dataList
        }]
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
              color: '#BA2A17'
            },
            label: {
              show: true,
              position: 'inside',
              color: 'white'
            }
          }
        ]
      }
    },
  },
  async created() {
    await this.registerMap()
    await this.getProvinceList()
    await this.getPopularityList()
  }
})
</script>

<template>
  <div class="container">
    <div class="column left">

    </div>
    <div class="column middle">
      <div class="chart-grid">
        <base-echart :option="getMapChartOption()" v-if="mapLoaded" style="padding-top: 200px;"/>
      </div>
    </div>
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

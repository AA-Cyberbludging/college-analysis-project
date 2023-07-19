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

type Admission = {
  pname: string,
  year: number,
  admissionLine: number,
  subject: string,
}

export default defineComponent({
  data() {
    return {
      provinceList: [] as Province[],
      popularityList: [] as Popularity[],
      admissionList: [] as Admission[],
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
        const data = await axios.get('/api/info/province')
        this.provinceList = data.data
      } catch (error: any) {
        this.$message.error("省份列表获取失败")
      }
    },
    getProvinceListOption() {
      const slice = this.provinceList.slice(0, 5)
      const data = slice.map(x => {
        return {
          value: x.unum,
          name: x.pname
        }
      })
      return {
        title: [
          {
            text: "大学数量 TOP5",
            left: 'center'
          }
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c}'
        },
        series: [{
          type: 'funnel',
          data: data,
          label: {
            show: true,
            position: 'inside'
          },
          gap: 1,
          minSize: '50%',
          maxSize: '150%',
          bottom: 20
        }]
      }
    },
    async getAdmissionList() {
      try {
        const data = await axios.get('/api/info/tendency')
        this.admissionList = data.data
      } catch (error: any) {
        this.$message.error("历年分数线获取失败: " + error)
      }
    },
    getAdmissionListOption(type: string) {
      const rdata = this.admissionList.filter(x => x.subject === type)

      const seriesData = rdata.reduce<{ [key: number]: Admission[] }>((acc, item) => {
        const year = item.year;
        if (!acc[year]) {
          acc[year] = [];
        }
        acc[year].push(item);
        return acc;
      }, {});

      const series = Object.entries(seriesData).map(([year, data]) => ({
        name: year.toString(),
        type: 'bar',
        data: data.map((item) => item.admissionLine.toString()),
      }));
      return {
        title: {
          text: `近四年各省${type}一本分数线`,
          left: "center"
        },
        tooltip: {
          show: true,
          trigger: 'item',
          axisPointer: {
            type: 'shadow'
          },
        },
        legend: {
          data: ['2020', '2021', '2022', '2023'],
          top: "10%"
        },
        xAxis: [
          {
            type: 'category',
            data: Array.from(new Set(rdata.map(x => x.pname))),
            axisPointer: {
              type: 'shadow'
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            min: 300,
            max: 600,
            interval: 50,
          }],
        series
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
          left: "center",
          textStyle: {
            fontSize: 25
          }
        },
        roam: true,
        tooltip: {
          trigger: 'item',
          showDelay: 0,
          transitionDuration: 0.2,
          formatter: "<b>{b}</b>: {c}"
        },
        visualMap: {
          min: 0,
          max: 26,
          left: 'left',
          top: 'bottom',
          inRange: {
            color: ['rgba(181, 27, 0, 0.2)', '#b51b00']
          },
        },
        series: [{
          type: 'map',
          map: 'china',
          data: dataList,
          itemStyle: {
            emphasis: {
              areaColor: 'rgb(255, 0, 0, 0.65)'
            }
          }
        }]
      }
    },
    async getPopularityList() {
      try {
        const data = await axios.get('/api/info/university/popularity')
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
    await this.getAdmissionList()
  }
})
</script>

<template>
  <div class="container">
    <div class="column left">
      <div class="chart-grid animated">
        <base-echart :option="getProvinceListOption()" width="130" style="padding-top: 20px;" />
      </div>
      <div class="chart-grid animated">
        <base-echart :option="getAdmissionListOption('文科')" width="130" style="padding-top: 20px;" />
      </div>
    </div>
    <div class="column middle">
      <div class="chart-grid animated" v-if="mapLoaded">
        <base-echart :option="getMapChartOption()" width="200" style="height: 600px; padding-top: 50px;" />
      </div>
    </div>
    <div class="column right">
      <div class="chart-grid animated">
        <base-echart :option="getPopularityListOption()" width="130" style="padding-top: 20px;" />
      </div>
      <div class="chart-grid animated">
        <base-echart :option="getAdmissionListOption('理科')" width="130" style="padding-top: 20px;" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  height: 91vh;
}

.column {
  float: left;
}

.left {
  width: 27%;
  /* background-color: #ccc; */
}

.middle {
  width: 46%;
  /* background-color: #ddd; */
}

.right {
  width: 27%;
  /* background-color: #ccc; */
}

.chart-grid {
  background-color: rgb(248, 248, 248);
  margin: 10px;
  box-shadow: 2px 3px 10px rgb(128, 128, 128, 0.5);
  border-radius: 16px;
}

.animated {
  animation: slide-up 1s ease-out;
}

@keyframes slide-up {
  from {
    transform: translateY(100%);
    opacity: 0;
  }

  to {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
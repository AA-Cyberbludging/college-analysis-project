<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  props: {
    uname: String,
    rate: String,
    type: String,
    lowestMps: Object,
  },
  data() {
    return {}
  },
  computed: {
    getStyle() {
      const baseStyle = `
        border-radius: 50%;
        font-size: 18px;
        padding: 5px;
        padding-left: 8px; 
        padding-right: 8px;
        font-weight: bold;
        `
      let colorStyle = ""

      switch (this.type) {
        case "冲":
          colorStyle = 'color: rgba(255, 75, 36); background-color: rgba(255, 75, 36, 0.1);'
          break;
        case "稳":
          colorStyle = 'color: rgba(36, 120, 255); background-color: rgba(36, 120, 255, 0.1);'
          break;
        case "保":
          colorStyle = 'color: rgba(0, 165, 28); background-color: rgba(0, 165, 28, 0.1);'
          break;
        default:
          colorStyle = 'color: #FFFFFF; background-color: #f0f0f0;'
          break;
      }
      return baseStyle + colorStyle
    }
  }
})
</script>

<template>
  <div class="card-container">
    <el-row>
      <el-col :span="2">
        <div style=" margin-top: 20px;">
          <span :style="getStyle">{{ type }}</span>
        </div>
      </el-col>
      <el-col :span="3">
        <div>
          <div style="font-size: 5px; padding-top: 12px;">录取率</div>
          <span class="rate">{{ rate }}</span>
        </div>
      </el-col>
      <el-col :span="12">
        <div style="padding-top: 22px;">{{ uname }}</div>
      </el-col>
      <el-col :span="3" v-if="lowestMps">
        <div>
          <div style="font-size: 5px; padding-top: 5px; font-weight: bold;">历年</div>
          <span style="font-size: 5px;">最低分</span>
          <br />
          <span style="font-size: 5px;">最低位次</span>
        </div>
      </el-col>
      <el-col :span="3" v-if="lowestMps">
        <div>
          <div style="font-size: 5px; padding-top: 5px; font-weight: bold;">{{ lowestMps?.year }} 年</div>
          <span style="font-size: 5px;">{{ lowestMps?.umps }}</span>
          <br />
          <span style="font-size: 5px;">{{ lowestMps?.rank }}</span>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.card-container {
  transition: all 0.1s ease-in;
  padding: 5px;
  height: 65px;
  border-radius: 8px;
}

h3 {
  width: 150px;
  font-size: 120%;
}

.rate {
  font-family: '华文仿宋';
  font-weight: bold;
  font-size: 20px;
}

.rank {
  font-family: '华文仿宋';
  font-weight: bold;
  font-size: 10px;
  margin: 0;
}

.card-container:hover {
  background-color: rgb(128, 128, 128, 0.1);
}
</style>

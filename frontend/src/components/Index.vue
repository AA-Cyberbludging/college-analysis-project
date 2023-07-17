<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({ data() { return {} } })
</script>

<template>
  <div class="container">
    <div class="column left"></div>
    <div class="column middle"></div>
    <div class="column right"></div>
    
  </div>
</template>

<style scoped>
.container {
  width: 100%;
  height: 100%;
  overflow: hidden;
  /* 清除浮动 */
}

.column {
  height: 200px;
  /* 设置高度，便于查看效果 */
  float: left;
  /* 设置浮动 */
}

.left {
  width: 25%;
  /* 左侧栏宽度 */
  background-color: #ccc;
}

.middle {
  width: 50%;
  /* 中间栏宽度 */
  background-color: #ddd;
}

.right {
  width: 25%;
  /* 右侧栏宽度 */
  background-color: #ccc;
}</style>

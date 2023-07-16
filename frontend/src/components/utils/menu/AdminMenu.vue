<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({ data() { return {} } })
</script>

<template>
  <el-menu mode="horizontal" default-active="1" id="menu">
    <el-menu-item index="1">首页</el-menu-item>
    <el-menu-item index="2">大学信息管理</el-menu-item>
    <el-menu-item index="3">用户信息管理</el-menu-item>
  </el-menu>
</template>

<style scoped>
#menu {
  margin-top: 5px;
  max-width: 450px;
  scale: 110%
}
</style>
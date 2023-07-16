<script lang="ts">
import { miscStore } from '@/store/misc';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
export default defineComponent({
   data() { return {} },
   methods: {
    handleMenu(index: number) {
      switch (index) {
        case 1:
          this.$router.push('/')
          break
        case 2:
          this.$router.push('/')
          break
        case 3:
        if (!this.miscStore.login) {
            this.$message.info("请先登录")
          } else {
            this.$router.push('/')
          }
          break
        case 4:
          if (!this.miscStore.login) {
            this.$message.info("请先登录")
          } else {
            this.$router.push('/userInfo')
          }
          break
        default:
          break;
      }
    }
   },
   computed: {
    ...mapStores(miscStore)
   }
})
</script>

<template>
  <el-menu mode="horizontal" default-active="1" id="menu">
    <el-menu-item index="1" @click="handleMenu(1)">首页</el-menu-item>
    <el-menu-item index="2" @click="handleMenu(2)">大学信息查询</el-menu-item>
    <el-menu-item index="3" @click="handleMenu(3)">志愿推荐</el-menu-item>
    <el-menu-item index="4" @click="handleMenu(4)">个人信息</el-menu-item>
  </el-menu>
</template>

<style scoped>
#menu {
  margin-top: 5px;
  max-width: 450px;
  scale: 110%
}
</style>




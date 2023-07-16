<script lang="ts">
import { miscStore } from '@/store/misc';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
export default defineComponent({
  data() {
    return {}
  },
  methods: {
    handleMenu(index: number) {
      switch (index) {
        case 1:
          this.$router.push('/')
          break
        case 2:
        if (!this.miscStore.login) {
            this.$message.info("请先登录")
          } else {
            this.$router.push('/')
          }
          break
        case 3:
          if (!this.miscStore.login) {
            this.$message.info("请先登录")
          } else {
            this.$router.push('/user-manage')
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
    <el-menu-item index="2" @click="handleMenu(2)">大学信息管理</el-menu-item>
    <el-menu-item index="3" @click="handleMenu(3)">用户信息管理</el-menu-item>
  </el-menu>
</template>

<style scoped>
#menu {
  margin-top: 5px;
  max-width: 450px;
  scale: 110%
}
</style>
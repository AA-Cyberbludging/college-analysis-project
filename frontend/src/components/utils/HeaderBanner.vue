<script lang="ts">
import { userStore } from '@/store/user';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
export default defineComponent({
   data() { return {} },
   computed: {
    ...mapStores(userStore)
   }
})
</script>

<template>
  <header>
    <el-row justify="space-between">
      <el-col :span="6">
        <h2>大学院校信息与推荐系统</h2>
      </el-col>
      <el-col :span="9">
      <StudentMenu v-if="userStore.isStudent"/>
      <AdminMenu v-else />
    </el-col>
      <el-col :span="6">
        <HeaderUserStatus />
      </el-col>
    </el-row>
  </header>
</template>

<style scoped>
header {
  width: 100%;
  box-shadow: 0px 4px 50px rgba(128, 128, 128, 0.5);
}

h2 {
  margin-left: 20px;
}
</style>
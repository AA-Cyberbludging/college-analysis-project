<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';

type User = {
  userId: number,
  userName: string,
  userScore: number,
  pname: string,
  subject: string,
}

export default defineComponent({
  data() {
    return {
      userList: [] as User[],
      deleteDialogVisible: false,
      confirmUserIndex: {} as number,
      confirmUserName: {} as string,
      confirmUserId: 0 as number,
    }
  },
  methods: {
    async getAllUsers() {
      try {
        const data = await axios.get('/api/admin/users', {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        })
        this.userList = data.data
      } catch (error: any) {
        this.$message.error("用户列表获取失败")
      }
    },
    showDeleteDialog(index: number) {
      this.deleteDialogVisible = true
      this.confirmUserName = this.userList[index].userName
      this.confirmUserIndex = index
      this.confirmUserId = this.userList[index].userId
    },
    async deleteUser() {
      try {
        const data = await axios.delete(`/api/admin/users/${this.confirmUserId}`, {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        })
        if (data.data.success) {
          this.$message.success("用户删除成功")
          await this.getAllUsers()
          this.deleteDialogVisible = false
        } else {
          this.$message.error(data.data.message)
        }
      } catch (error: any) {
        this.$message.error("用户删除失败")
      }
    }
  },
  async created() {
    this.getAllUsers()
  }
})
</script>

<template>
  <h2 style="text-align: center;">用户管理</h2>
  <el-table :data="userList" style="width: auto;" :max-height="800" stripe>
    <el-table-column prop="userId" label="ID" />
    <el-table-column prop="userName" label="用户名" />
    <el-table-column prop="pname" label="省份" />
    <el-table-column prop="subject" label="文理" />
    <el-table-column prop="userScore" label="分数" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="danger" @click="showDeleteDialog(scope.$index)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="deleteDialogVisible" title="请确认操作">
    你确定要删除用户 <strong>{{ confirmUserName }}</strong> 吗?
    <template #footer>
      <el-button @click="deleteDialogVisible = false">取消</el-button>
      <el-button type="danger" @click="deleteUser()">确认</el-button>
    </template>
  </el-dialog>
</template>

<style scoped></style>

<script lang="ts">
import { provinces } from '@/const';
import axios from 'axios';
import { defineComponent } from 'vue';

type User = {
  userId: number,
  userName: string,
  userScore: number,
  userRank: number,
  pname: string,
  subject: string,
}

export default defineComponent({
  data() {
    return {
      provinces: provinces,
      userList: [] as User[],
      deleteDialogVisible: false,
      modifyDialogVisible: false,
      confirmUserIndex: {} as number,
      confirmUserName: {} as string,
      confirmUserId: 0 as number,
      newUserInfoForm: {
        userName: "" as string,
        userScore: 0 as number,
        userRank: 0 as number,
        pname: {} as string,
        subject: {} as string,
        newPassword: "" as string,
      },
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
    },
    showModifyDialog(index: number) {
      this.modifyDialogVisible = true
      this.confirmUserId = this.userList[index].userId
      this.newUserInfoForm = {
        newPassword: "",
        ...this.userList[index]
      }
    },
    async updateUserInfo() {
      if (this.newUserInfoForm.userScore < 400 || this.newUserInfoForm.userScore > 750) {
        this.$message.error("请输入正确范围内的分数 (400~750)")
        return
      }
      if (this.newUserInfoForm.userRank < 1) {
        this.$message.error("请输入正确的位次")
        return
      }
      try {
        const res = await axios.post(`/api/user/${this.confirmUserId}`, this.newUserInfoForm, {
          headers: {
            Authorization: window.localStorage.getItem('cap-access')
          }
        })
        if (res.data.success) {
          this.getAllUsers()
          this.$message.success("信息更新成功")
          this.modifyDialogVisible = false;
        } else {
          this.$message.error("信息更新失败")
        }
      } catch (error: any) {
        this.$message.error("信息更新失败")
      }
    },
  },
  async created() {
    this.getAllUsers()
  }
})
</script>

<template>
  <h2 style="text-align: center;">用户管理</h2>
  <el-table :data="userList" style="width: auto; padding-left: 50px; padding-right: 50px;" :max-height="800" stripe>
    <el-table-column prop="userId" label="ID" />
    <el-table-column prop="userName" label="用户名" />
    <el-table-column prop="pname" label="省份" />
    <el-table-column prop="subject" label="文理" />
    <el-table-column prop="userScore" label="分数" />
    <el-table-column prop="userRank" label="位次" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="primary" @click="showModifyDialog(scope.$index)">修改信息</el-button>
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

  <el-dialog v-model="modifyDialogVisible" title="信息修改" style="max-width: 500px;">
    <el-form v-model="newUserInfoForm">
      <el-form-item label="用户名">
        <el-input v-model="newUserInfoForm.userName" />
      </el-form-item>
      <el-form-item label="省份">
        <el-select v-model="newUserInfoForm.pname">
          <el-option v-for="(item, _) in provinces" :label="item" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="分数">
        <el-input v-model="newUserInfoForm.userScore" />
      </el-form-item>
      <el-form-item label="位次">
        <el-input v-model="newUserInfoForm.userRank" />
      </el-form-item>
      <el-form-item label="文理">
        <el-select v-model="newUserInfoForm.subject">
          <el-option label="文科" value="文科" />
          <el-option label="理科" value="理科" />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="modifyDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="updateUserInfo">确认</el-button>
    </template>
  </el-dialog>
</template>

<style scoped></style>

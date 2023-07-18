<script lang="ts">
import { userStore } from '@/store/user';
import axios from 'axios';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
import { provinces } from '@/const'
import { ElMessage } from 'element-plus';
import { miscStore } from '@/store/misc';
import { nowTimestamp } from '@/utils/time';
export default defineComponent({
  data() {
    return {
      isChangable: false,
      provinces: provinces,
      changePasswordDialogVisible: false,
      newUserInfoForm: {
        userName: "" as string,
        userScore: 0 as number,
        userRank: 0 as number,
        pname: {} as string,
        subject: {} as string,
      },
      newPasswordForm: {
        oldPassword: "",
        newPassword: "",
      },
      feedbackDialogVisible: false,
      feedback: "",
    }
  },
  methods: {
    initForm() {
      this.newUserInfoForm = {
        userName: this.userStore.userName,
        userScore: this.userStore.userScore,
        userRank: this.userStore.userRank,
        pname: this.userStore.pname,
        subject: this.userStore.subject
      }
    },
    async getUserInfo() {
      try {
        const data = await axios.get(`/api/user/${this.userStore.userId}`, {
          headers: { Authorization: window.localStorage.getItem('cap-access') }
        })
        this.userStore.$state = data.data
        console.log(this.userStore.$state)
      } catch (error: any) {
        this.$message.error("用户信息请求失败: " + error.toString())
      }
    },
    changeInfo() {
      this.isChangable = true;
    },
    async updateUserInfo() {
      if (this.newUserInfoForm.userScore < 400 || this.newUserInfoForm.userScore > 750) {
        this.$message.error("请输入正确范围内的分数 (400~750)")
        return
      }
      if (this.newUserInfoForm.userRank < 0) {
        this.$message.error("请输入正确的位次")
        return
      }
      try {
        const res = await axios.post(`/api/user/${this.userStore.userId}`, this.newUserInfoForm, {
          headers: {
            Authorization: window.localStorage.getItem('cap-access')
          }
        })
        if (res.data.success) {
          this.getUserInfo()
          this.$message.success("信息更新成功")
        } else {
          this.restoreUserForm()
          this.$message.error("信息更新失败")
        }
      } catch (error: any) {
        this.restoreUserForm()
        this.$message.error("信息更新失败")
      }
      this.isChangable = false;
    },
    restoreUserForm() {
      this.initForm()
      this.isChangable = false;
    },
    changePassword() {
      if (this.newPasswordForm.oldPassword.trim() === ""
        || this.newPasswordForm.newPassword.trim() === "") {
        this.$message.error("请输入密码")
        return
      }

      if (this.newPasswordForm.newPassword.trim().length < 6) {
        this.$message.error("密码长度应大于 6 位")
        this.newPasswordForm.newPassword = ""
        return
      }

      let path = `/api/user/${this.userStore.userId}/password`
      try {
        axios.post(path, this.newPasswordForm, {
          headers: {
            Authorization: window.localStorage.getItem('cap-access')
          }
        }).then(res => {
          ElMessage({ message: res.data.message, type: res.data.success ? "success" : "error" })
          if (res.data.success) {
            this.logout()
          }
        })
      } catch (error: any) {
        this.$message.error("请求失败: " + error.toString())
      }
      this.newPasswordForm.oldPassword = this.newPasswordForm.newPassword = ""
    },
    async submitFeedback() {
      if (this.feedback.trim() === "") {
        this.$message.error("请输入反馈")
        return
      }
      const time = nowTimestamp()
      console.log(time)
      try {
        const data = await axios.post('/api/feedback/add', {
          userId: this.userStore.userId,
          feedback: this.feedback,
          timestamp: time
        }, {
          headers: { Authorization: window.localStorage.getItem('cap-access') }
        })
        if (data.data.success) {
          this.$message.success("反馈成功")
          this.feedback = ""
          this.feedbackDialogVisible = false
        } else {
          this.$message.error(data.data.message)
        }
      } catch (error: any) {
        this.$message.error("反馈提交失败")
      }
    },
    logout() {
      this.miscStore.login = false
      this.$router.replace('/')
    }
  },
  computed: {
    ...mapStores(userStore, miscStore)
  },
  async created() {
    await this.getUserInfo()
    this.initForm()
  }
})
</script>

<template>
  <div class="center-container">
    <div class="container">
      <div v-if="userStore.isStudent">
        <h2>考生信息</h2>
        <el-form v-model="newUserInfoForm" :disabled="!isChangable" style="padding: 20px;" label-width="60px">
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
        <el-row style="margin-bottom: 40px;" v-if="!isChangable">
          <el-col :span="12">
            <el-button @click="changePasswordDialogVisible = true">修改密码</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" @click="changeInfo">修改基本信息</el-button>
          </el-col>
        </el-row>
        <el-row style="margin-bottom: 40px;" v-else>
          <el-col :span="12">
            <el-button @click="restoreUserForm">取消</el-button>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" @click="updateUserInfo">确认修改</el-button>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-button type="text" @click="feedbackDialogVisible = true">反馈</el-button>
          </el-col>
        </el-row>
      </div>
      <div v-else>
        <h2>管理员信息</h2>
        <p>ID: {{ userStore.userId }}</p>
        <p>用户名: {{ userStore.userName }}</p>
        <el-button @click="changePasswordDialogVisible = true">修改密码</el-button>
      </div>
    </div>
  </div>

  <el-dialog v-model="changePasswordDialogVisible" title="修改密码" style="max-width: 500px;">
    <el-form :model="newPasswordForm">
      <el-form-item label="旧密码">
        <el-input v-model="newPasswordForm.oldPassword" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="newPasswordForm.newPassword" type="password" show-password></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="changePasswordDialogVisible = false,
          newPasswordForm.newPassword = newPasswordForm.oldPassword = ''">取消</el-button>
        <el-button type="primary" @click="changePassword">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="feedbackDialogVisible" title="用户反馈" style="max-width: 500px;">
    <el-input v-model="feedback" autosize type="textarea" placeholder="请输入您的反馈" />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="feedbackDialogVisible = false, feedback = ''">取消</el-button>
        <el-button type="primary" @click="submitFeedback">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.center-container {
  text-align: center;
}

.container {
  margin-top: 50px;
  display: inline-block;
  min-width: 300px;
  max-width: 1200px;
  border-radius: 16px;
  box-shadow: 2px 3px 10px rgb(128, 128, 128, 0.5);
  padding: 50px;
}
</style>

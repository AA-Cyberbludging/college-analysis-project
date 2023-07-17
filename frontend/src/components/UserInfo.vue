<script lang="ts">
import { userStore } from '@/store/user';
import axios from 'axios';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
import { provinces } from '@/const'
import { ElMessage } from 'element-plus';
import { miscStore } from '@/store/misc';
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
      }
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
      try {
        const res = await axios.post(`/api/user/${this.userStore.userId}`, this.newUserInfoForm, {
          headers: {
            Authorization: window.localStorage.getItem('cap-access')}
          })
        if (res.data.success) {
          this.getUserInfo()
          this.$message.success("信息更新成功")
        } else {
          this.restoreUserForm()
          this.$message.error("信息更新失败")
        }
      } catch (error: any) {
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
      <h2>考生信息</h2>

      <el-row style="min-width: 400px;">
        <el-col :span="12">
          <p>ID: </p>
        </el-col>
        <el-col :span="12">
          <p>{{ userStore.userId }} </p>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <p>用户名: </p>
        </el-col>
        <el-col :span="12">
          <el-input v-model="newUserInfoForm.userName" :disabled="!isChangable" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <p>省份: </p>
        </el-col>
        <el-select v-model="newUserInfoForm.pname" placeholder="无">
          <el-option v-for="(item, _) in provinces" :label="item" :value="item" :disabled="!isChangable" />
        </el-select>
      </el-row>
      <el-row>
        <el-col :span="12">
          <p>分数: </p>
        </el-col>
        <el-col :span="12">
          <el-input v-model="newUserInfoForm.userScore" :disabled="!isChangable" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <p>位次: </p>
        </el-col>
        <el-col :span="12">
          <el-input v-model="newUserInfoForm.userRank" :disabled="!isChangable" />
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <p>文理: </p>
        </el-col>
        <el-col :span="12">
          <el-radio-group v-model="newUserInfoForm.subject" style="margin-bottom: 40px; " :disabled="!isChangable">
            <el-radio-button label="文科" />
            <el-radio-button label="理科" />
          </el-radio-group>
        </el-col>
      </el-row>
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
    </div>
  </div>

  <el-dialog v-model="changePasswordDialogVisible" title="修改密码" style="max-width: 500px;">
    <el-form :model="newPasswordForm">
      <el-form-item label="旧密码">
        <el-input v-model="newPasswordForm.oldPassword" type="password"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="newPasswordForm.newPassword" type="password"></el-input>
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
</template>

<style scoped>
.center-container {
  text-align: center;
}

.container {
  margin-top: 50px;
  display: inline-block;
  min-width: 500px;
  max-width: 1200px;
  border-radius: 16px;
  box-shadow: 0px 2px 20px gray;
}
</style>

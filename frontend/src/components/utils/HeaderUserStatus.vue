<script lang="ts">
import axios from 'axios';
import { defineComponent } from 'vue';
import { userStore } from '@/store/user';
import { miscStore } from '@/store/misc';
import { mapStores } from 'pinia';
import { provinces } from '@/const';

export default defineComponent({
  data() {
    return {
      loginDialogVisible: false,
      registerDialogVisible: false,
      radioButtonStatus: "学生",
      loginForm: {
        name: "",
        password: "",
        isStudent: true,
      },
      registerForm: {
        name: "",
        password: "",
        confirmedPassword: "",
        pname: "",
        subject: "理科",
        userScore: 0 as number,
        userRank: 0 as number,
      },
      provinces: provinces
    }
  },
  methods: {
    async login() {
      if (this.loginForm.name.trim() === "" || this.loginForm.password.trim() === "") {
        this.$message.error("请输入用户名和密码")
        return
      }

      this.loginForm.isStudent = this.radioButtonStatus === "学生" ? true : false;
      try {
        const loginResult = await axios.post('/api/auth/login', this.loginForm)

        if (loginResult.data.success === true) {
          this.$message.success("登录成功")
          window.localStorage.setItem('cap-access', loginResult.data.data.token)
          this.userStore.userId = loginResult.data.data.id
          this.userStore.userName = this.loginForm.name
          this.userStore.isStudent = this.loginForm.isStudent
          this.loginDialogVisible = false
          this.miscStore.login = true
          this.loginForm.name = ""
          this.loginForm.password = ""
          this.loginForm.isStudent = true
        } else {
          this.$message.error(loginResult.data.message)
        }
      } catch (error: any) {
        this.$message.error("请求失败: " + error.toString())
      }
    },
    openRegisterDialog() {
      this.loginDialogVisible = false;
      this.registerDialogVisible = true;
    },
    async register() {
      if (this.registerForm.name.trim() === "" || this.loginForm.password.trim() === "") {
        this.$message.error("请输入用户名和密码")
        return
      }
      if (this.registerForm.password.trim().length < 6) {
        this.$message.error("密码长度应大于 6 个字符")
        return
      }
      if (this.registerForm.confirmedPassword.trim() === "") {
        this.$message.error("请再次输入密码")
        return
      }
      if (this.registerForm.password !== this.registerForm.confirmedPassword) {
        this.$message.error("两次输入的密码不一致")
        return
      }
      if (this.registerForm.pname === "") {
        this.$message.error("请选择省份")
        return
      }
      if (this.registerForm.userScore < 400 || this.registerForm.userScore > 750) {
        this.$message.error("请输入正确范围内的分数 (400~750)")
        return
      }
      if (this.registerForm.userRank < 1) {
        this.$message.error("请输入正确的位次")
        return
      }
      try {
        const registerResult = await axios.post('/api/auth/register', this.registerForm)
        if (registerResult.data.success === true) {
          this.$message.success("注册成功，请重新登录")
          this.registerDialogVisible = false
          this.registerForm = {
            name: "",
            password: "",
            confirmedPassword: "",
            pname: "",
            subject: "理科",
            userScore: 0,
            userRank: 0
          }
        } else {
          this.$message.error(registerResult.data.message)
        }
      } catch (error: any) {
        this.$message.error("请求失败: " + error.toString())
      }
    },
    logout() {
      this.miscStore.login = false
      this.$message.success("您已成功退出")
      this.$router.replace('/')
      window.localStorage.clear() // ...
      this.userStore.isStudent = true
    }
  },
  computed: {
    ...mapStores(userStore, miscStore),
  }
})
</script>

<template>
  <div class="vertical-center-container">
    <el-button @click="loginDialogVisible = true" v-if="!miscStore.login">登录</el-button>
    <div v-else>
      <span>欢迎您, {{ userStore.userName }}!</span>
      <el-button type="text" @click="logout">退出</el-button>
    </div>
  </div>

  <!-- 表单部分 -->
  <el-dialog v-model="loginDialogVisible" style="max-width: 400px;">
    <div class="center-container">
      <h2> 用户登录 </h2>
      <el-radio-group v-model="radioButtonStatus" style="margin-bottom: 40px;">
        <el-radio-button label="学生" />
        <el-radio-button label="管理员" />
      </el-radio-group>
      <el-form :model="loginForm" label-width="60px">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="loginForm.password" type="password" show-password/>
        </el-form-item>
        <el-row justify="center">
          <el-col :span="8">
            <el-button @click="openRegisterDialog" style="margin: 20px;">用户注册</el-button></el-col>
          <el-col :span="8">
            <el-button type="primary" @click="login" style="margin: 20px;">登录</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </el-dialog>

  <el-dialog v-model="registerDialogVisible">
    <div class="center-container">
      <h2> 新用户注册 </h2>
      <el-form :model="registerForm" label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="registerForm.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="registerForm.password" type="password" show-password />
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="registerForm.confirmedPassword" type="password" show-password />
        </el-form-item>
        <el-form-item label="省份">
          <el-select v-model="registerForm.pname" placeholder="无">
            <el-option v-for="(item, _) in provinces" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="文理">
          <el-select v-model="registerForm.subject" placeholder="理科">
            <el-option label="文科" value="文科" />
            <el-option label="理科" value="理科" />
          </el-select>
        </el-form-item>
        <el-form-item label="分数">
          <el-input v-model="registerForm.userScore" />
        </el-form-item>
        <el-form-item label="位次">
          <el-input v-model="registerForm.userRank" />
        </el-form-item>
        <el-button type="primary" @click="register" style="margin: 20px;">注册</el-button>
      </el-form>
    </div>
  </el-dialog>
</template>

<style scoped>
.center-container {
  text-align: center;
}

.vertical-center-container {
  text-align: right;
  margin-top: 20px;
  margin-right: 40px;
}
</style>
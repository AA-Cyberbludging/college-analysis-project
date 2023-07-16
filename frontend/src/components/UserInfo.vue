<script lang="ts">
import { userStore } from '@/store/user';
import axios from 'axios';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';
import { provinces } from '@/const'
export default defineComponent({
  data() {
    return {
      isChangable: false,
      provinces: provinces,
      newUserInfoForm: {
        userName: {} as string,
        userScore: {} as number,
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
      this.newUserInfoForm.userName = this.userStore.userName
      this.newUserInfoForm.userScore = this.userStore.userScore
      this.newUserInfoForm.pname = this.userStore.pname
      this.newUserInfoForm.subject = this.userStore.subject
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
    updateUserInfo() {
      this.$message.info("还没写😔")
      this.isChangable = false;
    },
    restoreUserForm() {
      this.initForm()
    },
    changePassword() {
      
    }
  },
  computed: {
    ...mapStores(userStore)
  },
  created() {
    this.getUserInfo()
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
          <el-button>修改密码</el-button>
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

<script lang="ts">
import { provinces } from '@/const';
import { userStore } from '@/store/user';
import axios from 'axios';
import { mapStores } from 'pinia';
import { defineComponent } from 'vue';

import RecommendationCard from '../utils/RecommendationCard.vue';

// type PAdmission = {
//   pname: string,
//   year: number,
//   admissionLine: number,
//   subject: string,
// }

// type Recommendation = {
//   uid: number,
//   uname: number,

// }

export default defineComponent({
  data() {
    return {
      provinces: provinces,
      newUserInfoForm: {
        userName: "" as string,
        userScore: 0 as number,
        userRank: 0 as number,
        pname: {} as string,
        subject: {} as string,
      },
    };
  },
  methods: {
    initForm() {
      this.newUserInfoForm = {
        userName: this.userStore.userName,
        userScore: this.userStore.userScore,
        userRank: this.userStore.userRank,
        pname: this.userStore.pname,
        subject: this.userStore.subject
      };
    },
    async getUserInfo() {
      try {
        const data = await axios.get(`/api/user/${this.userStore.userId}`, {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        });
        this.userStore.$state = data.data;
        console.log(this.userStore.$state);
      }
      catch (error: any) {
        this.$message.error("用户信息请求失败: " + error.toString());
      }
    },
    async getRecommendation() {
      // try {
      //   const data = await axios.post(`/api/display/recommend`, {
      //     userScore: "",
      //     userRank: "",
      //     pname: "",
      //     subject: "",
      //   }, { headers: {Authorization: window.localStorage.getItem('cap-access')}})
      // } catch (error: any) {
      //   this.$message.error("志愿推荐获取失败: " + error.toString())
      // }
    }
  },
  computed: {
    ...mapStores(userStore)
  },
  async created() {
    this.initForm();
    await this.getRecommendation()
  },
  components: { RecommendationCard }
})
</script>

<template>
  <div class="center-container">
    <div class="container">
      <div style="margin-bottom: 30px;">
        <span style="font-size: 150%;">志愿推荐</span>
      </div>
      <div class="left">
        <el-form v-model="newUserInfoForm" style="padding-right: 20px; padding-top: 60px;" label-width="60px">
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
        <el-button @click="initForm()">重置</el-button>
        <el-button type="primary" @click="getRecommendation()" style="margin-left: 10px;">重新推荐</el-button>
      </div>
      <div class="right">
        <ul>
          <li>
            <RecommendationCard uname="中国电力大学 (华北)" rate="5%" type="冲" />
            <RecommendationCard uname="🍊⚡" rate="60%" type="稳" />
            <RecommendationCard uname="Sichuan University" rate="90%" type="保" />
            <RecommendationCard uname="Sichuan University" rate="90%" type="保" />
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<style scoped>
.center-container {
  text-align: center;
}

ul {
  overflow: auto;
  margin-block-start: 0;
  margin-block-end: 0;
  padding-inline-start: 0;
}

li {
  list-style: none;
}

.container {
  margin-top: 50px;
  display: inline-block;
  min-width: 900px;
  border-radius: 16px;
  box-shadow: 2px 3px 10px rgb(128, 128, 128, 0.5);
  padding: 50px;
}

.left {
  float: left;
  width: 30%;
  border-right: 1px solid gray;
}

.right {
  padding-left: 5px;
  float: right;
  width: 70%;
}

.left,
.right {
  box-sizing: border-box;
  height: 500px;
}
</style>

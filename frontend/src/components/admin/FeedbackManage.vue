<script lang="ts">
import { timestampToString } from '@/utils/time';
import axios from 'axios';
import { defineComponent } from 'vue';
type Feedback = {
  fbId: number,
  userId: number,
  feedback: string,
  timestamp: number,
  fbDeal: boolean,
  userName: string,
  //-------------
  time: string,
  status: string,
}

export default defineComponent({
  data() {
    return {
      feedbackList: [] as Feedback[],
      deleteDialogVisible: false,
      confirmFeedbackIndex: {} as number,
    }
  },
  methods: {
    async getAllFeedbacks() {
      try {
        const data = await axios.get('/api/feedback', {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        })
        this.feedbackList = data.data
        this.feedbackList.forEach(x => {
          x.time = timestampToString(x.timestamp)
          x.status = x.fbDeal ? "已解决" : "未解决"
        })
      } catch (error: any) {
        console.log(error)
        this.$message.error("反馈列表获取失败: " + error)
      }
    },
    showDeleteDialog(index: number) {
      this.deleteDialogVisible = true
      this.confirmFeedbackIndex = this.feedbackList[index].fbId
    },
    async deleteFeedback() {
      try {
        const data = await axios.delete(`/api/feedback/${this.confirmFeedbackIndex}`, {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        })
        if (data.data.success) {
          this.$message.success("反馈删除成功")
          await this.getAllFeedbacks()
          this.deleteDialogVisible = false
        } else {
          this.$message.error(data.data.message)
        }
      } catch (error: any) {
        this.$message.error("反馈删除失败")
      }
    },
    async confirmFeedback(index: number) {
      const fbId = this.feedbackList[index].fbId
      try {
        const data = await axios.post(`/api/feedback/${fbId}`, {
          headers: { Authorization: window.localStorage.getItem("cap-access") }
        })
        if (data.data.success) {
          this.$message.success("反馈已完成")
          await this.getAllFeedbacks()
        } else {
          this.$message.error(data.data.message)
        }
      } catch (error: any) {
        this.$message.error("反馈状态修改失败: " + error)
      }
    }
  },
  async created() {
    this.getAllFeedbacks()
  }
})

</script>


<template>
  <h2 style="text-align: center;">反馈管理</h2>
  <el-table :data="feedbackList" style="width: auto; padding-left: 50px; padding-right: 50px;" :max-height="800" stripe>
    <el-table-column prop="fbId" label="反馈 ID" />
    <el-table-column prop="userId" label="用户 ID" />
    <el-table-column prop="userName" label="用户名" />
    <el-table-column prop="feedback" label="反馈" :show-overflow-tooltip="true"/>
    <el-table-column prop="time" label="反馈时间" :show-overflow-tooltip="true"/>
    <el-table-column prop="status" label="状态" :filters="[
      { text: '已解决', value: '已解决' },
      { text: '未解决', value: '未解决' }
    ]" 
    :filter-method="(value, row) => { return row.tag === value }"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="success" @click="confirmFeedback(scope.$index)" v-if="!feedbackList[scope.$index].fbDeal">完成</el-button>
        <el-button type="danger" @click="showDeleteDialog(scope.$index)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="deleteDialogVisible" title="请确认操作">
    你确定要删除这条反馈吗?
    <template #footer>
      <el-button @click="deleteDialogVisible = false">取消</el-button>
      <el-button type="danger" @click="deleteFeedback()">确认</el-button>
    </template>
  </el-dialog>
</template>

<style scoped></style>

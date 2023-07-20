import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@c/Index.vue'
import Error from '@c/Error.vue'
import UserInfo from '@c/UserInfo.vue'
import UInfoQuery from '@c/user/UniversityInfoQuery.vue'
import UserManage from '@c/admin/UserManage.vue'
import FeedbackManage from '@c/admin/FeedbackManage.vue'
import Recom from '@c/user/Recommendation.vue'


const routes = [
  { path: '/', component: Index },
  { path: '/userInfo', component: UserInfo },
  { path: '/uinfo', component: UInfoQuery },
  { path: '/user-manage', component: UserManage },
  { path: '/feedback-manage', component: FeedbackManage},
  { path: '/recom', component: Recom },
  { path: '/:catchAll(.*)', component: Error }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
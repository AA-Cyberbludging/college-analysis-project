import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@c/Index.vue'
import Error from '@c/Error.vue'
import UserInfo from '@c/UserInfo.vue'


const routes = [
  { path: '/', component: Index },
  { path: '/userInfo', component: UserInfo },
  { path: '/:catchAll(.*)', component: Error }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
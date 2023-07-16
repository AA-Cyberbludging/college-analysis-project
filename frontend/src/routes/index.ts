import { createRouter, createWebHashHistory } from 'vue-router'
import Index from '@c/Index.vue'
import Error from '@c/Error.vue'
import UserInfo from '@c/UserInfo.vue'
import UInfoQuery from '@c/user/UniversityInfoQuery.vue'
import UserManage from '@c/admin/UserManage.vue'
import Recom from '@c/user/Recommendation.vue'


const routes = [
  { path: '/', component: Index },
  { path: '/userInfo', component: UserInfo },
  { path: '/uinfo', component: UInfoQuery },
  { path: '/user-manage', component: UserManage },
  { path: '/recom', component: Recom },
  { path: '/:catchAll(.*)', component: Error }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

/*
router.beforeEach((to, _, next) => {
  if (to.path.startsWith('/login')) {
    window.localStorage.removeItem('dbsim-access')
    next()
  } else {
    // 取出 token
    let accessItem = window.localStorage.getItem('dbsim-access')
    if (accessItem === null) {
      next({ path: '/login' })
    } else {
      // 校验 token            
      axios({
        url: '/api/login/check',
        method: 'get',
        headers: {
          Authorization: accessItem
        },
      }).then(res => {
        if (!res.data) {
          console.log('token 校验失败')
          next({ path: '/login' })
        } else {
          next()
        }
      })
    }
  }
})
*/

export default router
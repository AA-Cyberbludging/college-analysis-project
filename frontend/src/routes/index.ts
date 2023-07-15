import { createRouter, createWebHashHistory } from 'vue-router'
import axios from 'axios'
import Error from '@c/Error.vue'


const routes = [
  { path: '/:catchAll(.*)', component: Error }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

export default router
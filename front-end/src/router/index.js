import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/home/Home.vue'
import SignIn from '../views/main/components/SignIn.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: SignIn,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

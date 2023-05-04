import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateView from '@/components/CreatePost.vue'
import EditView from '@/components/EditPost.vue'
import PostView from '@/components/ViewPost.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: { name: 'home'}
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView
  },
  {
    path: '/create',
    name: 'create',
    component: CreateView
  },
  {
    path: '/edit/:id',
    name: 'edit',
    component: EditView
  },
  {
    path: '/post/:id',
    name: 'post',
    component: PostView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

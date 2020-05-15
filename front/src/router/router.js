import Vue from 'vue'
import Router from 'vue-router'
import HomePage from "../views/HomePage";

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: HomePage
    },
    {
      path: '/users',
      component: () => import('../views/UsersPage.vue'),
    },
    {
      path: '/users/:id/projects',
      name: 'projects',
      component: () => import('../views/ProjectsPage.vue')
    }
  ]
})

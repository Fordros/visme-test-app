import Vue from 'vue'
import Vuex from 'vuex'
import users from './modules/users'
import projects from './modules/projects'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    users,
    projects
  }
})

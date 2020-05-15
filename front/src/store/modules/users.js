import {getAll, create, edit, remove} from "../../api/UserRestApi";

const state = {
    users: []
  }
const mutations = {
    SET_USERS(state, users) {
      state.users = users
    },
    PUSH_USER(state, user) {
        state.users.push(user)
    },
    REMOVE_USER(state, userId) {
        state.users.splice(userId, 1)
    },
    EDIT_USER(state, user) {
        const item = state.users.find(item => item.id === user.id);
        Object.assign(item, user);
    }
  }
const actions = {

    LOAD_USERS ({commit}) {
      getAll().then(response => {
        commit('SET_USERS', response)
      }).catch(error => {
        console.log(error);
      })
    },
    createUser ({commit}, user) {
        create(user).then(r => {
            commit('PUSH_USER', r)
        }).catch( error =>{
            console.log(error);
        })
    },
    removeUser ({commit}, userId) {
        remove(userId).then(r => {
            commit('REMOVE_USER', r)
        }).catch( error =>{
            console.log(error);
        })
    },
    editUser ({commit}, user) {
        console.log("api request for edit " + user.id)
    edit(user).then(r => {
        commit('EDIT_USER', r)
    }).catch( error =>{
        console.log(error);
    })
}
  }
const getters = {
    GET_USERS(state){
      return state.users
    }
  }

export default {
  state,
  mutations,
  actions,
  getters
}

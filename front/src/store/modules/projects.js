import {
    getAllByUserId,
    create,
    edit,
    remove,
    changeFavoriteState,
    getAccessTimesByProjectId
} from "../../api/ProjectRestApi";

const state = {
    projects: [],
    accesses: []
}
const mutations = {
    SET_PROJECTS(state, item) {
        state.projects = item
    },
    PUSH_PROJECT(state, item) {
        state.projects.push(item)
    },
    REMOVE_PROJECT(state, itemId) {
        const item = state.projects.find(item => item.id === itemId);
        console.log('found item id' + item.id)
        state.projects.splice(item.index, 1)
    },
    EDIT_PROJECT(state, project) {
        const item = state.projects.find(item => item.id === project.id);
        Object.assign(item, project);
    },
    EDIT_FAVORITE_STATE(state, projectId) {
        const item = state.projects.find(item => item.id === projectId);
        item.favorite = !item.favorite
        Object.assign(item, item);
    },
    SET_ACCESSES(state, item) {
        state.accesses = item
    },
}
const actions = {

    loadAllForUser({commit}, userId) {
        getAllByUserId(userId).then(response => {
            commit('SET_PROJECTS', response)
        }).catch(error => {
            console.log(error);
        })
    },
    createProject({commit}, project) {
        console.log('store before create' + JSON.stringify(project));
        create(project).then(r => {
            commit('PUSH_PROJECT', r)
        }).catch(error => {
            console.log(error);
        })
    },
    removeProject({commit}, projectId) {
        remove(projectId).then(() => {
            console.log("remove project" + projectId);
            commit('REMOVE_PROJECT', projectId)
        }).catch(error => {
            console.log(error);
        })
    },
    editProject({commit}, project) {
        edit(project).then(r => {
            commit('EDIT_PROJECT', r)
        }).catch(error => {
            console.log(error);
        })
    },
    changeFavoriteState({commit}, projectId) {
        console.log("api request for edit " + projectId)
        changeFavoriteState(projectId).then(r => {
            console.log(r);
            commit('EDIT_FAVORITE_STATE', projectId)
        }).catch(error => {
            console.log(error);
        })
    },
    loadAllAccessTimes( {commit}, projectId) {
        console.log("store " + JSON.stringify(projectId));
        return getAccessTimesByProjectId(projectId).then(r => {
            commit('SET_ACCESSES', r)
            console.log("store return" + JSON.stringify(r));
            return r;
        }).catch(error => {
            console.log(error);
        })
    }
}
const getters = {
    GET_PROJECTS(state) {
        return state.projects
    },
    GET_ACCESSES(state) {
        return state.accesses
    }
}

export default {
    state,
    mutations,
    actions,
    getters
}

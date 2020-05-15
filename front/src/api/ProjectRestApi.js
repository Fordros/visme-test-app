import request from '@/utils/request'

export function getAllByUserId(userId) {
    return request({
        url: `/api/users/${userId}/projects`,
        method: 'get'
    })
}

export function create(data) {
    let req = JSON.stringify(data)
    console.log('before request' + req);
    return request.post(`/api/users/${data.userId}/projects`, req, {headers: {"Content-Type": "application/json"}})
}

export function edit(data) {
    let req = JSON.stringify(data)
    return request.put(`/api/projects/${data.id}`, req, {headers: {"Content-Type": "application/json"}})
}

export function remove(id) {
    return request({
        url: `/api/projects/${id}`,
        method: 'delete',
    })
}

export function changeFavoriteState(id) {
    return request.put(`/api/projects/${id}/favorite`, {}, {headers: {"Content-Type": "application/json"}})
}

export function getAccessTimesByProjectId(projectId) {
    console.log('before request access' + JSON.stringify(projectId));
    return request({
        url: `/api/projects/${projectId}/access-times`,
        method: 'get'
    })
}


import request from '@/utils/request'

export function getAll() {
  return request({
    url: '/api/users',
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/users',
    headers: {
    "Content-Type": "application/json"
    },
    method: 'post',
    data
  })
}

export function edit(data) {
  let req = JSON.stringify(data)
  let id = data.id
  console.log("data for send " + id)
  return request.put(`/api/users/${id}`,req, {headers: {
      "Content-Type": "application/json"
    }})
}
export function remove(id) {
  return request({
    url: `/api/users/${id}`,
    method: 'delete',
  })
}


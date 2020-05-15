import axios from 'axios'
// import { getToken, removeToken } from '../utils/auth'

axios.defaults.withCredentials = false

const service = axios.create({
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    // let sessionId = getToken()
    // if (sessionId) {
    //   config.headers.Authorization = `JSESSIONID=${sessionId}`
    // }
    config.headers.HTTP2_HEADER_CONTENT_TYPE = 'application/json'
         return config
  },
  err => {
    return Promise.reject(err)
  }
)

service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
        case 403:
          // removeToken()
          window.location.href = 'http://localhost:8080/login'
      }
    }
    return Promise.reject(error.response.data)
  }
)

export default service

import axios from 'axios'

axios.defaults.withCredentials = false

const service = axios.create({
  timeout: 5000
})

service.interceptors.request.use(
  config => {
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
      console.log(error.response)
    }
    return Promise.reject(error.response.data)
  }
)

export default service

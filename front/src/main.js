import Vue from 'vue'
import App from './App.vue'
import router from './router/router'
import store from './store/index'
import Element from 'element-ui'
import moment from 'moment'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Element)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  moment,
  render: h => h(App),
}).$mount('#app')

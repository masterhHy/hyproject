// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import router from './router'
import axios from './plugins__config/axios_config.js'
import {AlertPlugin, ToastPlugin} from 'vux'

Vue.use(AlertPlugin)
Vue.use(ToastPlugin)

Vue.use(VueRouter)
axios.init(Vue);
require("./common/common.css")
FastClick.attach(document.body)


Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')

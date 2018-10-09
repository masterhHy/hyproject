import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import VuxIndex from '@/components/VuxIndex'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'VuxIndex',
      component: VuxIndex
    },
    {
      path: '/VuxIndex',
      name: 'VuxIndex',
      component: VuxIndex
    }
  ]
})

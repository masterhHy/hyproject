import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import HomePage from '@/components/h5/coin/HomePage'
import TimeCoin from '@/components/h5/coin/TimeCoin'
import TimeCoinEdit from '@/components/h5/coin/TimeCoinEdit'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HomePage',
      component: HomePage
    },
    {
      path: '/HelloWorld',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/TimeCoin',
      name: 'TimeCoin',
      component: TimeCoin
    },
    {
      path: '/TimeCoin/TimeCoinEdit',
      name: 'TimeCoinEdit',
      component: TimeCoinEdit
    }
  ]
})

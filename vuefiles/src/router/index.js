import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component:  () =>
      import(/* webpackChunkName: "home" */ "@/view")
    },
    {
      path: '/',
      name: 'HelloWorld',
      component:  () =>
      import(/* webpackChunkName: "HelloWorld" */ "@/components/HelloWorld")
    },
  ]
})

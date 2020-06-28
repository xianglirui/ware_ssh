import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/resgister/index')
    // hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },


  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: '用户信息', icon: 'form' }
      }
    ]
  },


  {
    path: '/enter',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Enter',
        component: () => import('@/views/table/Enter'),
        meta: { title: '入库信息', icon: 'el-icon-folder-add' }
      }
    ]
  },

  {
    path: '/out',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Out',
        component: () => import('@/views/table/Out'),
        meta: { title: '出库信息', icon: 'el-icon-folder-remove' }
      }
    ]
  },

  {
    path: '/purchase',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Purchase',
        component: () => import('@/views/table/Purchase'),
        meta: { title: '采购管理', icon: 'el-icon-s-claim' }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  mode: 'history',
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}



export default router

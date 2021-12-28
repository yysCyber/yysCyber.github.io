import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../views/Home.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    alias: ['/index', '/home'],
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import('../views/About.vue')
  },

  {
    path: '/aerial-photography/calculator',
    name: 'AerialPhotographyCalculator',
    component: () => import('../views/ap/Calculator.vue')
  },

  {
    path: '/aerial-photography/help',
    name: 'AerialPhotographyHelp',
    component: () => import('../views/ap/Help.vue')
  },

  {
    path: '/aerial-photography/calculator/record',
    name: 'RecordInfo',
    component: () => import('../views/ap/record/RecordInfo.vue')
  },

  {
    path: '/aerial-photography/calculator/uav-camera',
    name: 'CameraInfo',
    component: () => import('../views/ap/camera/CameraInfo.vue')
  },

  {
    path: '/aerial-photography/calculator/uav-camera/new',
    name: 'CameraAdd',
    component: () => import('../views/ap/camera/CameraAdd.vue')
  }
];

const router = new VueRouter({
  routes,
  mode: 'history'
});

export default router

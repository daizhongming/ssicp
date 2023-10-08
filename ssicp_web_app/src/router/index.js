import { createRouter, createWebHistory } from 'vue-router';
import surveillance from './surveillance.js';
import strategy from './strategy.js';
import systemmanage from './systemmanage.js';
import threeDimension from './threeDimension.js';
import deviceManage from './deviceManage.js';
import develop from './develop.js';
const routes = [
  {
    path: '',
    name: 'index',
    redirect: '/home/todoCenter',
  },
  {
    path: '/home',
    name: 'home',

    component: () => import('../views/HomeView.vue'),
    children: [
      {
        path: 'todoCenter',
        name: 'todoCenter',

        component: () =>
          import('../views/threatIntelligence/TodoCenterView.vue'),
      },
      {
        path: 'operationCenter',
        name: 'operationCenter',

        component: () =>
          import('../views/threatIntelligence/OperationCenterView.vue'),
      },
      ...surveillance,
      ...strategy,
      ...systemmanage,
      ...threeDimension,
      ...deviceManage,
      ...develop,
    ],
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/LoginView.vue'),
  },
];
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});
export default router;

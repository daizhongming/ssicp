export default [
  {
    path: 'modelTest',
    name: 'modelTest',

    component: () => import('../views/develop/ModelTestView.vue'),
  },
  {
    path: 'threeDimensionTest',
    name: 'threeDimensionTest',

    component: () => import('../views/develop/ThreeDimensionTestView.vue'),
  },

  {
    path: 'deviceGraphTest',
    name: 'deviceGraphTest',
    component: () => import('../views/develop/DeviceGraphTestView.vue'),
  },
];

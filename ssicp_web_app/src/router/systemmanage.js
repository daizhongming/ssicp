export default [
  {
    path: 'userManage',
    name: 'userManage',

    component: () => import('../views/systemmanage/UserCenterView.vue'),
  },
  {
    path: 'menuManage',
    name: 'menuManage',

    component: () => import('../views/systemmanage/MenuManageView.vue'),
  },
  {
    path: 'logmanager',
    name: 'logmanager',

    component: () => import('../views/systemmanage/LogManagerView.vue'),
  },
  {
    path: 'dictType',
    name: 'dictType',
    redirect: '/home/dictType',
    children: [
      {
        path: '',
        name: 'dictType',
        component: () => import('../views/systemmanage/DictTypeCenterView.vue'),
      },
      {
        path: ':code',
        name: 'dictData',
        meta: {
          title: '配置数据',
          back: true,
        },
        component: () => import('../views/systemmanage/DictDataCenterView.vue'),
      },
    ],
  },
  {
    path: 'roleManage',
    name: 'roleManage',

    component: () => import('../views/systemmanage/RoleManageView.vue'),
  },
  {
    path: 'paramsManage',
    name: 'paramsManage',

    component: () =>
      import('../views/systemmanage/paramsManage/ParamsCenterView.vue'),
  },
  {
    path: 'jobmanage',
    name: 'jobmanage',

    component: () => import('../views/systemmanage/JobCenterView.vue'),
  },
  {
    path: 'buttonManage',
    name: 'buttonManage',

    component: () =>
      import('../views/systemmanage/buttonManage/buttonView.vue'),
  },
];

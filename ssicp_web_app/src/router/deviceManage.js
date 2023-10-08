export default [
  {
    path: 'pipeNetworkhome',
    name: 'pipeNetworkhome',
    redirect: '/home/pipeNetworkhome',
    children: [
      {
        path: '',
        name: 'pipeNetworkhome',
        component: () =>
          import('../views/surveillance/pipeNetworkhome/PipeNetworkView.vue'),
      },
      {
        path: 'pipework',
        name: 'pipeNetworkPipework',
        meta: {
          title: '管段管理',
          back: true,
        },
        component: () => import('../views/surveillance/pipe/PipeView.vue'),
      },
    ],
  },
  {
    path: 'pipework',
    name: 'pipework',
    component: () => import('../views/surveillance/pipe/PipeView.vue'),
  },
  {
    path: 'instrumentManagement',
    name: 'instrumentManagement',

    component: () =>
      import(
        '../views/surveillance/device/instrumentManagement/InstrumentManagementView.vue'
      ),
  },
  {
    path: 'valveManagement',
    name: 'valveManagement',

    component: () =>
      import(
        '../views/surveillance/device/valveManagement/ValveManagementView.vue'
      ),
  },
  {
    path: 'steamTrapsManagement',
    name: 'steamTrapsManagement',

    component: () =>
      import(
        '../views/surveillance/device/steamTrapsManagement/SteamTrapsManagementView.vue'
      ),
  },
  {
    path: 'compensatorManagement',
    name: 'compensatorManagement',

    component: () =>
      import(
        '../views/surveillance/device/compensatorManagement/CompensatorManagementView.vue'
      ),
  },
  {
    path: 'elbowTeeRegistry',
    name: 'elbowTeeRegistry',

    component: () =>
      import(
        '../views/surveillance/device/elbowTeeRegistry/ElbowTeeRegistryView.vue'
      ),
  },
  {
    path: 'gasPointManagement',
    name: 'gasPointManagement',

    component: () =>
      import(
        '../views/surveillance/device/gasPointManagement/GasPointManagementView.vue'
      ),
  },
];

export default [
  {
    path: 'fullLifecycle',
    name: 'fullLifecycle',
    component: () =>
      import('../views/surveillance/fullLifecycle/fullLifecycleView.vue'),
  },
  {
    path: 'manualInspection',
    name: 'manualInspection',
    redirect: '/home/manualInspection',
    children: [
      {
        path: '',
        name: 'manualInspection',
        component: () =>
          import(
            '../views/surveillance/manualInspection/ManualInspectionView.vue'
          ),
      },
      {
        path: 'manualoutcome',
        name: 'manualInspectionManualoutcome',
        meta: {
          title: '人工巡检实绩',
          back: true,
        },
        component: () =>
          import('../views/surveillance/manualoutcome/ManualoutcomeView.vue'),
      },
    ],
  },
  {
    path: 'manualoutcome',
    name: 'manualoutcome',
    component: () =>
      import('../views/surveillance/manualoutcome/ManualoutcomeView.vue'),
  },
  {
    path: 'intelligentInspection',
    name: 'intelligentInspection',
    redirect: '/home/intelligentInspection',
    children: [
      {
        path: '',
        name: 'intelligentInspection',
        component: () =>
          import(
            '../views/surveillance/intelligentInspection/IntelligentInspectionView.vue'
          ),
      },
      {
        path: 'inspectionoutcome',
        name: 'intelligentInspectionInspectionoutcome',
        meta: {
          title: '智能巡检实绩',
          back: true,
        },
        component: () =>
          import(
            '../views/surveillance/inspectionoutcome/InspectionoutcomeView.vue'
          ),
      },
    ],
  },
  {
    path: 'inspectionoutcome',
    name: 'inspectionoutcome',
    component: () =>
      import(
        '../views/surveillance/inspectionoutcome/InspectionoutcomeView.vue'
      ),
  },
  {
    path: 'earlyWarningCenter',
    name: 'earlyWarningCenter',

    component: () =>
      import('../views/threatIntelligence/EarlyWarningCenterView.vue'),
  },
];

export default [
  {
    path: 'simulationOptimization',
    name: 'simulationOptimization',

    component: () =>
      import('../views/simulation/SimulationOptimizationView.vue'),
  },
  {
    path: 'threatIntelligenceCenter',
    name: 'threatIntelligenceCenter',

    component: () =>
      import('../views/threatIntelligence/ThreatIntelligenceCenterView.vue'),
  },
  {
    path: 'fullTimeStatusDetection',
    name: 'fullTimeStatusDetection',

    component: () =>
      import('../views/surveillance/FullTimeStatusDetectionView.vue'),
  },
];

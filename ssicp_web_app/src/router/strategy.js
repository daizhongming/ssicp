export default [
  {
    path: 'pipeNetworkAnalysis',
    name: 'pipeNetworkAnalysis',

    component: () =>
      import('../views/decisionanalysis/PipeNetworkAnalysisView.vue'),
  },
  {
    path: 'intelligentDecisionPlan',
    name: 'intelligentDecisionPlan',

    component: () =>
      import('../views/decisionanalysis/IntelligentDecisionPlan.vue'),
  },
  {
    path: 'benefitAnalysisReport',
    name: 'benefitAnalysisReport',

    component: () =>
      import('../views/decisionanalysis/BenefitAnalysisReportView.vue'),
  },
  {
    path: 'intelligentOperationAnalysis',
    name: 'intelligentOperationAnalysis',

    component: () =>
      import('../views/decisionanalysis/OperationAnalyseView.vue'),
  },
  {
    path: 'reportRecords',
    name: 'reportRecords',

    component: () =>
      import('../views/decisionanalysis/reportRecords/reportRecordsView.vue'),
  },
  {
    path: 'knowledgeBase',
    name: 'knowledgeBase',
    redirect: '/home/knowledgeBase',
    children: [
      {
        path: '',
        name: 'knowledgeBase',
        component: () =>
          import(
            '../views/decisionanalysis/knowledgeBase/knowledgeBaseView.vue'
          ),
      },
      {
        path: 'knowledgeBaseDetailed',
        name: 'knowledgeBaseDetailedKnowledgeBase',
        meta: {
          title: '知识库查看',
          back: true,
        },
        component: () =>
          import('../views/decisionanalysis/knowledgeBase/knowledgeBaseDetailed.vue'),
      },
    ]
  },
  {
    path: 'knowledgeBaseDetailed',
    name: 'knowledgeBaseDetailed',

    component: () =>
      import(
        '../views/decisionanalysis/knowledgeBase/knowledgeBaseDetailed.vue'
      ),
  },
];

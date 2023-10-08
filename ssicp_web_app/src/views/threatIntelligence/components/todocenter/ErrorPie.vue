<script setup>
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { PieChart } from 'echarts/charts';
  import {
    TitleComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent,
  } from 'echarts/components';
  import VChart, { THEME_KEY } from 'vue-echarts';

  import { ref, provide, onMounted, reactive } from 'vue';
  import { countDeviceAnomalies } from '@/api/index.js';

  import { titleTextStyle, pieColorList, pieRadius } from './config';
  use([
    CanvasRenderer,
    PieChart,
    GridComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
  ]);

  provide(THEME_KEY, 'dark');

  const option = reactive({
    color: pieColorList,
    backgroundColor: 'transparent',
    title: {
      text: '异常未处理分析',
      left: 'center',
      bottom: '0',
      textStyle: titleTextStyle,
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c}次',
    },
    legend: {
      formatter: function (name) {
        return name + '（' + getValueByName(name) + '次）';
      },
    },
    series: [
      {
        type: 'pie',
        radius: pieRadius,
        label: {
          normal: {
            position: 'center',
            formatter: () => {
              return `{number|${getTotal()}次}`;
            },
            rich: {
              text: {
                color: '#58C4F6',
                fontSize: '1.2rem',
              },
              number: {
                color: 'white',
                fontSize: '2rem',
              },
            },
          },
        },

        data: [],
      },
    ],
  });

  function getTotal() {
    return option.series[0].data.reduce((x, y) => x + y.value, 0);
  }
  async function search() {
    const response = await countDeviceAnomalies();
    let data = response.data
      .filter((o) => o.groupByName)
      .map(({ groupByName, handledTimes, unHandleTimes }) => {
        return { name: groupByName, value: unHandleTimes };
      });
    option.legend.data = data.map((e) => e.name);
    option.series[0].data = data;
  }
  function getValueByName(name) {
    const item = option.series[0].data.find((item) => item.name === name);
    return item ? item.value : '';
  }
  onMounted(() => {
    search();
  });
</script>

<template>
  <v-chart :option="option" autoresize />
</template>

<style scoped lang="scss"></style>

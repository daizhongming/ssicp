<script setup>
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { BarChart } from 'echarts/charts';
  import {
    TitleComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent,
  } from 'echarts/components';
  import VChart, { THEME_KEY } from 'vue-echarts';

  import { ref, provide, onMounted, reactive } from 'vue';
  import { analyseAlarmByMonth } from '@/api/index.js';

  import {
    titleTextStyle,
    gridSplitterColor,
    barColorList,
    barWidth,
  } from './config';
  use([
    CanvasRenderer,
    BarChart,
    GridComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
  ]);

  provide(THEME_KEY, 'dark');

  const option = reactive({
    color: barColorList,
    backgroundColor: 'transparent',
    title: {
      text: '近六月报警分析',
      left: 'center',
      bottom: '0',
      textStyle: titleTextStyle,
    },
    legend: {
      formatter: function (name) {
        return name + '（' + findTotalByName(name) + '次）';
      },
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
    },
    xAxis: [
      {
        type: 'category',
        data: [],
      },
    ],
    yAxis: [
      {
        type: 'value',
        splitLine: {
          lineStyle: {
            color: [gridSplitterColor],
          },
        },
      },
    ],
    series: [
      {
        name: '已处理',
        type: 'bar',
        barWidth: barWidth,
        stack: 'group1',
        emphasis: {
          focus: 'series',
        },
        data: [],
      },
      {
        name: '未处理',
        type: 'bar',
        barWidth: barWidth,
        stack: 'group1',
        emphasis: {
          focus: 'series',
        },
        data: [],
      },
    ],
  });

  function findTotalByName(name) {
    return option.series
      .find((item) => item.name === name)
      .data.reduce((x, y) => x + y, 0);
  }

  async function search() {
    const response = await analyseAlarmByMonth();
    const data = response.data.filter((o) => o.groupByName);
    option.xAxis[0].data = data.map((e) => +e.groupByName.split('-')[1] + '月');
    option.series[0].data = data.map((e) => e.handledTimes);
    option.series[1].data = data.map((e) => e.unHandleTimes);
  }
  onMounted(() => {
    search();
  });
</script>

<template>
  <v-chart :option="option" autoresize />
</template>

<style scoped lang="scss"></style>

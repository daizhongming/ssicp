<script setup>
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { LineChart } from 'echarts/charts';
  import {
    TitleComponent,
    TooltipComponent,
    GridComponent,
    LegendComponent,
    DataZoomComponent,
  } from 'echarts/components';
  import VChart, { THEME_KEY } from 'vue-echarts';

  import {
    ref,
    provide,
    onMounted,
    reactive,
    getCurrentInstance,
    watch,
  } from 'vue';
  import { findIndexLogsHistory } from '@/api/surveillance/manualInspection.js';

  import {
    titleTextStyle,
    gridSplitterColor,
    barColorList,
    barWidth,
  } from '@/views/threatIntelligence/components/todocenter/config.js';

  use([
    CanvasRenderer,
    LineChart,
    GridComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    DataZoomComponent,
  ]);

  provide(THEME_KEY, 'dark');
  const props = defineProps(['deviceId','index']);
  const { $date } = getCurrentInstance().appContext.config.globalProperties;
  const option = reactive({
    color: barColorList,
    backgroundColor: 'transparent',
    title: {
      show: false,
    },
    legend: {
      show: false,
    },
    grid: {
      top: '10px',
      bottom: '30px',
      left: '50px',
      right: '50px',
    },
    tooltip: {
      trigger: 'axis',
      formatter: '{a}:{c}<br />{b}',
      axisPointer: {
        type: 'shadow',
      },
    },
    dataZoom: [
      {
        type: 'inside',
        xAxisIndex: [0],
        start: 0,
        end: 100,
      },
    ],
    xAxis: {
      type: 'category',
      data: [],
      axisLabel: {
        formatter: function (value) {
          return $date.datetime(value);
        },
      },
    },
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
        type: 'line',
        emphasis: {
          focus: 'series',
        },
        name: props.index,
        data: [],
      },
    ],
  });

  async function search() {
    console.log(props.data);
    const response = await findIndexLogsHistory(
      props.deviceId,
      props.index
    );
    option.xAxis.data = response.data.map((e) => e.time);
    option.series[0].data = response.data.map((e) => {
      return { name: $date.datetime(e.time), value: e.value };
    });

  }
  onMounted(() => {
    search();
  });
  watch(() => props.index, search);
</script>

<template>
  <v-chart :option="option" autoresize v-if="option.xAxis.data.length>0" />
  <div v-else>
    暂时没有数据
  </div>
</template>
<style scoped lang="scss"></style>
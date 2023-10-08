<script setup>
  import { use } from 'echarts/core';
  import { CanvasRenderer } from 'echarts/renderers';
  import { GraphChart } from 'echarts/charts';
  import _ from 'lodash';
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

  use([
    CanvasRenderer,
    GraphChart,
    GridComponent,
    TitleComponent,
    TooltipComponent,
    LegendComponent,
    DataZoomComponent,
  ]);

  provide(THEME_KEY, 'dark');

  // 分类
  const categories = [
    {
      name: '仪表',
    },
    {
      name: '阀门',
    },
    {
      name: '疏水阀',
    },
    {
      name: '管段',
    },
    {
      name: '产汽点',
    },
    {
      name: '用汽点',
    },
    {
      name: '补偿器',
    },
    {
      name: '三通',
    },
    {
      name: '弯头',
    },
  ];

  //   provide(THEME_KEY, 'dark');
  const option = reactive({
    // 在这里配置网状关系图的选项
    title: {
      text: '网状关系图示例',
    },
    legend: [
      {
        selectedMode: 'none',
        data: categories.map((e) => e.name),
        emphasis: {
          iconStyle: {
            color: 'initial', // 或其他颜色值，以使图例项保持不变
          },
        },
      },
    ],
    series: [
      {
        name: 'Les Miserables',
        type: 'graph',
        layout: 'none',
        // 在这里配置节点和连接数据
        data: [
          // 添加更多节点
        ],
        links: [
          // 添加更多连接
        ],
        roam: true,
        categories: categories,
        label: {
          show: true,
          position: 'right',
          formatter: '{b}',
        },
        lineStyle: {
          color: 'source',
          curveness: 0.3,
        },
        emphasis: {
          focus: 'adjacency',
          lineStyle: {
            width: 10,
          },
        },
      },
    ],
  });

  import { findAllDeviceData } from '@/api/surveillance/device';
  async function search() {
    let { data } = await findAllDeviceData();
    console.log(data);
    const dataList = _.filter(
      data,
      ({ device }) => device.parentDeviceType !== '3'
    );
    // 节点
    const nodeList = [];
    // 关系
    const relationList = [];
    const addRelation = (pipeIds, deviceId) => {
      pipeIds
        .filter((pipeId) => pipeId)
        .forEach((pipeId) => {
          if (!data[pipeId]) {
            pipeId = '0';
            data[pipeId] = {
              device: { id: pipeId, deviceName: '未知管段' },
              extra: {},
            };
          }
          relationList.push({ source: deviceId, target: pipeId });
        });
    };
    dataList.forEach(({ device, extra }) => {
      // 关系
      switch (device.parentDeviceType) {
        //阀门
        case '2': {
          addRelation([extra.pipeExit, extra.pipeInlet], device.id);
          break;
        }
        //疏水阀
        // case '3': {
        //   addRelation([device.pid], device.id);
        //   break;
        // }
        //仪表
        case '1': {
          addRelation([extra.pipeExit, extra.pipeInlet], device.id);
          break;
        }
        //产汽点
        case '5': {
          addRelation([extra.pid], device.id);
          break;
        }
        //用汽点
        case '6': {
          addRelation([extra.pid], device.id);
          break;
        }
        //补偿器
        case '7': {
          addRelation([extra.pipeExit, extra.pipeInlet], device.id);
          break;
        }
        //三通
        case '8': {
          addRelation(
            [...JSON.parse(extra.pipeExit), ...JSON.parse(extra.pipeInlet)],
            device.id
          );
          break;
        }
        //弯头
        case '9': {
          addRelation(
            [...JSON.parse(extra.pipeExit), ...JSON.parse(extra.pipeInlet)],
            device.id
          );
          break;
        }
      }
    });
    dataList.forEach(({ device, extra }) => {
      // 节点
      nodeList.push({
        id: device.id,
        name: device.deviceName,
        category: +device.parentDeviceType - 1,
        color: 'red',
        x: Math.random(),
        y: Math.random(),
      });
    });

    console.log('relationList', relationList);
    console.log('nodeList', nodeList);
    console.log('categories', categories);
    option.series[0].data = nodeList;
    option.series[0].links = relationList;
    option.series[0].categories = categories;
  }
  onMounted(search);
</script>

<template>
  <v-chart :option="option" autoresize />
</template>

<style scoped lang="scss"></style>

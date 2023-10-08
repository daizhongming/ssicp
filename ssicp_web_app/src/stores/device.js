import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { tree as treeReq } from '@/api/surveillance/device.js';
import _ from 'lodash';
export const useDeviceStore = defineStore('device', () => {
  const UN_LINKED_PIPELINE_ID = '0';
  const UN_LINKED_PIPELINE_NAME = '未关联';
  const pipelines = ref([]);
  const pipes = ref([]);
  const valves = ref([]);
  const traps = ref([]);
  const instruments = ref([]);
  const productions = ref([]);
  const consumptions = ref([]);
  const compensators = ref([]);
  const tees = ref([]);
  const elbows = ref([]);
  async function queryAllDevices() {
    const {
      data: {
        pipelines,
        pipes,
        valves,
        traps,
        instruments,
        productions,
        consumptions,
        compensators,
        tees,
        elbows,
      },
    } = await treeReq();
    setPipelines(pipelines);
    setPipes(pipes);
    setValves(valves);
    setTraps(traps);
    setInstruments(instruments);
    setProductions(productions);
    setConsumptions(consumptions);
    setCompensators(compensators);
    setTees(tees);
    setElbows(elbows);
  }
  function setPipelines(list) {
    pipelines.value = list;
  }
  function setPipes(list) {
    pipes.value = list;
  }
  function setValves(list) {
    valves.value = list;
  }
  function setTraps(list) {
    traps.value = list;
  }
  function setInstruments(list) {
    instruments.value = list;
  }
  function setProductions(list) {
    productions.value = list;
  }
  function setConsumptions(list) {
    consumptions.value = list;
  }
  function setCompensators(list) {
    compensators.value = list;
  }
  function setTees(list) {
    tees.value = list;
  }
  function setElbows(list) {
    elbows.value = list;
  }

  const deviceTree = computed(() => {
    const deviceTypeMap = {
      pipe: { title: '管段', sort: 0 },
      elbow: { title: '弯头/三通', sort: 1 },
      tee: { title: '弯头/三通', sort: 1 },
      compensator: { title: '补偿器', sort: 2 },
      valve: { title: '阀门', sort: 3 },
      trap: { title: '疏水阀', sort: 4 },
      instrument: { title: '仪表', sort: 5 },
      production: { title: '产汽点', sort: 6 },
      consumption: { title: '用汽点', sort: 7 },
    };
    // 管段id->name映射，方便做转换
    const pipeIdNameMap = {};
    pipes.value.forEach((pipe) => {
      pipeIdNameMap[pipe.id] = pipe.pipeName;
    });
    // 管线id->管线对象映射，方便做转换
    const pipelineIdPipelineMap = {};
    pipelines.value.forEach((pipeline) => {
      pipelineIdPipelineMap[pipeline.id] = pipeline;
    });
    // 获得管段所属管线集合
    const pipeId_pipelineIdMap = {};
    pipelines.value.forEach((pipeline) => {
      if (pipeline.pipeIds) {
        JSON.parse(pipeline.pipeIds)
          .filter((pipeId) => pipeIdNameMap[pipeId])
          .forEach((pipeId) => {
            const pipelines = pipeId_pipelineIdMap[pipeId] || [];
            pipelines.push(pipeline.id);
            pipeId_pipelineIdMap[pipeId] = pipelines;
          });
      }
    });
    // 获得设备大宽表（包括设备id，name,类型，所属管线）
    let deviceAll = [];
    const addObj = (pipeIds, deviceId, deviceName, deviceType) => {
      const pipelineIds = pipeIds.flatMap(
        (pipeId) => pipeId_pipelineIdMap[pipeId] || []
      );
      if (pipelineIds.length === 0) {
        deviceAll.push({
          deviceId,
          deviceName,
          deviceType,
          pipelineId: UN_LINKED_PIPELINE_ID,
        });
      } else {
        _.uniq(pipelineIds).forEach((pipelineId) => {
          deviceAll.push({
            deviceId,
            deviceName,
            deviceType,
            pipelineId,
          });
        });
      }
    };

    pipes.value.forEach((obj) => {
      addObj([obj.id], obj.id, obj.pipeName, 'pipe');
    });
    valves.value.forEach((obj) => {
      addObj([obj.pipeExit, obj.pipeInlet], obj.id, obj.deviceName, 'valve');
    });
    traps.value.forEach((obj) => {
      addObj([obj.pid], obj.id, obj.deviceName, 'trap');
    });
    instruments.value.forEach((obj) => {
      addObj(
        [obj.pipeExit, obj.pipeInlet],
        obj.id,
        obj.deviceName,
        'instrument'
      );
    });
    productions.value.forEach((obj) => {
      addObj([obj.pid], obj.id, obj.deviceName, 'production');
    });
    consumptions.value.forEach((obj) => {
      addObj([obj.pid], obj.id, obj.deviceName, 'consumption');
    });
    compensators.value.forEach((obj) => {
      addObj([obj.pid], obj.id, obj.deviceName, 'compensator');
    });
    tees.value.forEach((obj) => {
      addObj(
        [...JSON.parse(obj.pipeExit), ...JSON.parse(obj.pipeInlet)],
        obj.id,
        obj.deviceName,
        'tee'
      );
    });
    elbows.value.forEach((obj) => {
      addObj(
        [...JSON.parse(obj.pipeExit), ...JSON.parse(obj.pipeInlet)],
        obj.id,
        obj.deviceName,
        'elbow'
      );
    });
    // 将大宽表进行分组处理
    let result = _.groupBy(deviceAll, 'pipelineId');
    // 将未关联管段/设备的管线也添加到结果中
    pipelines.value.forEach((pipeline) => {
      if (!result[pipeline.id]) {
        result[pipeline.id] = [];
      }
    });
    result = _.map(result, (devices, pipelineId) => {
      return {
        id: pipelineId,
        name: pipelineIdPipelineMap[pipelineId]
          ? pipelineIdPipelineMap[pipelineId].pipelineName
          : UN_LINKED_PIPELINE_NAME,
        region: pipelineIdPipelineMap[pipelineId]
          ? pipelineIdPipelineMap[pipelineId].region
          : UN_LINKED_PIPELINE_NAME,
        sort: pipelineId === UN_LINKED_PIPELINE_ID ? 1 : 0,
        children: _.map(
          _.groupBy(devices, 'deviceType'),
          (devices2, deviceType) => {
            return {
              id: deviceType,
              name: deviceTypeMap[deviceType].title,
              sort: deviceTypeMap[deviceType].sort,
              children: devices2.map((device) => {
                return {
                  id: device.deviceId,
                  name: device.deviceName,
                  sort: 0,
                };
              }),
            };
          }
          // 设备类型分组按照sort进行排序
        ).sort((t1, t2) => t1.sort - t2.sort),
      };
    })
      // 管线分组按照sort进行排序（未关联排在最后）
      .sort((p1, p2) => {
        return p1.sort - p2.sort;
      });
    return result;
  });
  const pipeTree = computed(() => {
    let result = _.cloneDeep(deviceTree.value);
    result = result
      .map((e) => {
        e.children = (
          e.children.find((o) => o.id === 'pipe') || { children: [] }
        ).children;
        return e;
      })
      .filter((e) => e.children.length > 0 && e.id != '0');
    return result;
  });

  return {
    pipelines,
    pipes,
    valves,
    traps,
    instruments,
    productions,
    consumptions,
    compensators,
    tees,
    elbows,
    deviceTree,
    pipeTree,
    queryAllDevices,
  };
});

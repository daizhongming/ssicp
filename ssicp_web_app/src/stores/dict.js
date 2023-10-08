import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { findAll } from '@/api/dict.js';
import _ from 'lodash';

export const dictStore = defineStore('dict', () => {
  const dicts = ref([]);
  const dictsMap = ref({});
  async function queryAllDicts() {
    const { data } = await findAll();
    data.forEach((type) => {
      type.dictDataMap = _.keyBy(type.dictDataList, 'dictValue');
    });
    dicts.value = data;
    dictsMap.value = _.keyBy(data, 'code');
  }

  function expandTypes() {
    const list = [
      { name: '颜色', value: 'color' },
      { name: '时间', value: 'date' },
      { name: '数字', value: 'number' },
      { name: '单位', value: 'unit' },
      { name: '文本', value: 'text' },
    ];
    return {
      all: list,
      label: (code) => {
        const obj = list.find((item) => item.value === code);
        return obj ? obj.name : '未知';
      },
    };
  }

  function getMapByType(type) {
    return (dictsMap.value[type] || { dictDataMap: {} }).dictDataMap;
  }
  function getListByType(type) {
    return (dictsMap.value[type] || { dictDataList: [] }).dictDataList;
  }

  function getFieldByTypeCodeAndDataCode(type, code, fieldName) {
    return (getMapByType(type)[code] || {})[fieldName];
  }
  function getLabelByTypeCodeAndDataCode(type, code) {
    return getFieldByTypeCodeAndDataCode(type, code, 'dictLabel');
  }
  function getExpand1ByTypeCodeAndDataCode(type, code) {
    return getFieldByTypeCodeAndDataCode(type, code, 'expand1');
  }
  function getExpand2ByTypeCodeAndDataCode(type, code) {
    return getFieldByTypeCodeAndDataCode(type, code, 'expand2');
  }
  function getExpand3ByTypeCodeAndDataCode(type, code) {
    return getFieldByTypeCodeAndDataCode(type, code, 'expand3');
  }

  const alarm_levels = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('alarm_level', code) || '未知',
      color: (code) =>
        getExpand1ByTypeCodeAndDataCode('alarm_level', code) || 'gray',
    };
  };

  const manual_inspection_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('manual_inspection_type', code) || '未知',
      all: getListByType('manual_inspection_type'),
    };
  };

  const time_unit = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('time_unit', code) || '未知',
      all: getListByType('time_unit'),
    };
  };

  const regional_division = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('regional_division', code) || '未知',
      all: getListByType('regional_division'),
    };
  };

  const inspection_device_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('inspection_device_type', code) || '未知',
      all: getListByType('inspection_device_type'),
    };
  };

  const security_level = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('security_level', code) || '未知',
      all: getListByType('security_level'),
    };
  };

  const pipeline_Insulation = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipeline_Insulation', code) || '未知',
      all: getListByType('pipeline_Insulation'),
    };
  };

  const pipe_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipe_index', code) || '未知',
      all: getListByType('pipe_index'),
    };
  };

  const compensator_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('compensator_index', code) || '未知',
      all: getListByType('compensator_index'),
    };
  };

  const instrument_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('instrument_index', code) || '未知',
      all: getListByType('instrument_index'),
    };
  };

  const with_steam_spots_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('with_steam_spots_index', code) || '未知',
      all: getListByType('with_steam_spots_index'),
    };
  };

  const steam_traps_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('steam_traps_index', code) || '未知',
      all: getListByType('steam_traps_index'),
    };
  };

  const valve_index = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('valve_index', code) || '未知',
      all: getListByType('valve_index'),
    };
  };

  const pipeline_famen_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipeline_famen_type', code) || '未知',
      all: getListByType('pipeline_famen_type'),
    };
  };

  const pipeline_compensator_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipeline_compensator_type', code) ||
        '未知',
      all: getListByType('pipeline_compensator_type'),
    };
  };

  const pipeline_yibiao_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipeline_yibiao_type', code) || '未知',
      all: getListByType('pipeline_yibiao_type'),
    };
  };

  const pipeline_steamtraps_type = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipeline_steamtraps_type', code) ||
        '未知',
      all: getListByType('pipeline_steamtraps_type'),
    };
  };

  const laying_method = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('laying_method', code) || '未知',
      all: getListByType('laying_method'),
    };
  };

  const pipe_segment_material = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('pipe_segment_material', code) || '未知',
      all: getListByType('pipe_segment_material'),
    };
  };

  const wall_thickness_series = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('wall_thickness_series', code) || '未知',
      all: getListByType('wall_thickness_series'),
    };
  };

  const per_unit = () => {
    return {
      label: (code) =>
        getLabelByTypeCodeAndDataCode('per_unit', code) || '未知',
      all: getListByType('per_unit'),
    };
  };

  const device_type = () => {
    return {
      label: (code) => getLabelByTypeCodeAndDataCode('device_type', code),
    };
  };

  const dictData = (type) => {
    return {
      label: (code) => getLabelByTypeCodeAndDataCode(type, code) || '未知',
      all: getListByType(type),
    };
  };

  const file_types = () => {
    return {
      label: (code) => getLabelByTypeCodeAndDataCode('file_type', code) || code,
      all: getListByType('file_type').map((item) => {
        const obj = { ...item };
        obj.dictLabel = obj.dictLabel + '.' + obj.dictValue;
        return obj;
      }),
    };
  };

  return {
    dicts,
    dictsMap,
    expandTypes,
    queryAllDicts,
    alarm_levels,
    device_type,
    dictData,
    manual_inspection_type,
    time_unit,
    regional_division,
    inspection_device_type,
    security_level,
    pipeline_Insulation,
    per_unit,
    pipe_index,
    compensator_index,
    instrument_index,
    with_steam_spots_index,
    steam_traps_index,
    valve_index,
    pipeline_famen_type,
    pipeline_compensator_type,
    pipeline_yibiao_type,
    pipeline_steamtraps_type,
    file_types,
    laying_method,
    pipe_segment_material,
    wall_thickness_series,
  };
});

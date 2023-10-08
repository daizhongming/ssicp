import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { findAll } from '@/api/config.js';
import _ from 'lodash';
export const useConfigStore = defineStore('config', () => {
  const configs = ref([]);
  const configMap = ref({});
  async function queryAllConfigs() {
    const { data } = await findAll();
    configs.value = data;
    configMap.value = _.keyBy(data, 'configKey');
  }

  const threeDimensionUrl = computed(() => {
    const obj = configMap.value['threeDimension.url'] || {};
    return obj.configValue;
  });

  return { configs, queryAllConfigs, configMap, threeDimensionUrl };
});

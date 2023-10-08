<script setup>
  import { ElMessage } from 'element-plus';
  import { useDeviceStore } from '@/stores/device.js';
  import { computed, onMounted, ref, watch, watchEffect } from 'vue';
  import _ from 'lodash';
  import { tree } from '@/api/surveillance/device';
  const props = defineProps(['pipeIds', 'pipelineId', 'regions']);
  const emits = defineEmits(['update:pipeIds', 'update:pipelineId']);
  const deviceStore = useDeviceStore();
  const treeData = ref([]);
  const pipeIds = ref([]);
  watchEffect(() => {
    console.log('初始化管段树');
    // 全部管段树
    const regionSet = new Set(props.regions);
    let result = _.cloneDeep(deviceStore.pipeTree).filter((pipeline) => {
      if (regionSet.size === 0) return true;
      return regionSet.has(pipeline.region);
    });
    result.forEach((pipeline) => {
      pipeline.key = pipeline.id;
      pipeline.children.forEach((pipe) => {
        pipe.key = pipeline.id + '_' + pipe.id;
      });
    });
    treeData.value = result;
  });
  watchEffect(() => {
    // 先计算可以正确回显的数据
    const pipeline = treeData.value.find(
      (pipeline) => pipeline.id === props.pipelineId
    );
    const checked = [];
    if (pipeline) {
      const pipeIdSet = new Set(pipeline.children.map((e) => e.id));
      (props.pipeIds || []).forEach((pipeId) => {
        if (pipeIdSet.has(pipeId)) {
          checked.push(pipeline.id + '_' + pipeId);
        }
      });
    }

    if (
      (props.pipeIds.length > 0 || props.pipelineId) &&
      ((treeData.value.length > 0 && props.pipeIds.length !== checked.length) ||
        treeData.value.length === 0)
    ) {
      // 清空数据
      console.log('清空数据');
      pipeIds.value = checked;
      updateOutSide(checked);
    } else {
      // 回显数据
      console.log('回显');
      pipeIds.value = checked;
    }

    return;

    if (!needClear()) {
      // 回显
      console.log('回显');
    } else {
      // 清空数据
      console.log('清空数据');
    }
    return;
    // 回显
  });
  watchEffect(() => {
    console.log('禁用');
    // 只能选择一根管段
    const pipelineId =
      pipeIds.value.length > 0 ? pipeIds.value[0].split('_')[0] : null;
    treeData.value.forEach((pipeline) => {
      pipeline.disabled = pipelineId && pipeline.id !== pipelineId;
      pipeline.children.forEach((pipe) => {
        pipe.disabled = pipeline.disabled;
      });
    });
  });
  // watchEffect(() => {
  //   // updateOutSide(pipeIds.value)
  //   console.log('pipeIds.value', pipeIds.value);
  // });

  function updateOutSide(list) {
    if (list.length === 0) {
      emits('update:pipeIds', []);
      emits('update:pipelineId', '');
    } else {
      emits(
        'update:pipeIds',
        list.map((e) => e.split('_')[1])
      );
      emits('update:pipelineId', list[0].split('_')[0]);
    }
  }

  function needClear() {
    console.log('treeData.length', treeData.value.length);
    if (
      (props.pipeIds.length > 0 || props.pipelineId) &&
      (treeData.value.length > 0 || treeData.value.length === 0)
    ) {
      return true;
    }
    // const list = store.dictData('pipeline_pressure').all;
    // if (
    //   (props.pressureRating &&
    //     list.length > 0 &&
    //     !list.some((dict) => dict.dictValue === props.pressureRating)) ||
    //   list.length === 0
    // ) {
    //   return true;
    // }
    return false;
  }

  function checkHandle() {
    console.log('change');
    updateOutSide(pipeIds.value);
  }
</script>

<template>
  <el-tree-select
    ref="treeRef"
    v-model="pipeIds"
    :data="treeData"
    node-key="key"
    multiple
    :render-after-expand="false"
    show-checkbox
    :props="{ children: 'children', label: 'name' }"
    @change="checkHandle"
  />
</template>

<style scoped lang="scss"></style>

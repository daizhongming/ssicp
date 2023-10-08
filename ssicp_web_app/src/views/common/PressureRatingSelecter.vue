<script setup>
  import { dictStore } from '@/stores/dict';
  const store = dictStore();
  import { ref, watchEffect } from 'vue';
  const props = defineProps(['pressureRating']);
  const emits = defineEmits(['update:pressureRating']);
  const data = ref('');
  watchEffect(() => {
    //如果不在则修改数据
    if (!needClear()) {
      //回显
      console.log('回显');
      data.value = props.pressureRating;
    } else {
      //清空数据
      console.log('清空数据');
      data.value = '';
      emits('update:pressureRating', '');
    }
  });
  function change() {
    console.log('change', data.value);
    const value = data.value;
    emits('update:pressureRating', value);
  }
  function needClear() {
    const list = store.dictData('pipeline_pressure').all;
    if (
      (props.pressureRating &&
        list.length > 0 &&
        !list.some((dict) => dict.dictValue === props.pressureRating)) ||
      list.length === 0
    ) {
      return true;
    }
    return false;
  }
</script>

<template>
  <el-select
    v-model="data"
    clearable
    placeholder="请选择(单选)"
    @change="change"
  >
    <el-option
      v-for="yali in store.dictData('pipeline_pressure').all"
      :label="yali.dictLabel"
      :value="yali.dictValue"
      :key="yali.dictLabel"
    />
  </el-select>
</template>

<style scoped lang="scss"></style>

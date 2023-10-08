<script setup>
  import { onMounted, watch } from 'vue';

  const props = defineProps('modelValue', 'list', 'label', 'value');
  const emits = defineEmits('update:modelValue');

  function init() {
    if (modelValue && list && list.length > 0) {
    }
  }
  onMounted(init);
  watch([() => props.modelValue, () => list], init);
</script>

<template>
  <el-select v-model="props.modelValue">
    <el-option
      v-for="item in props.list"
      :key="value ? item[value] : item"
      :label="label ? item[label] : item"
      :value="value ? item[value] : value"
    />
  </el-select>
</template>

<style scoped></style>

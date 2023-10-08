<script setup>
  import { onMounted, ref, watch } from 'vue';
  import { dictStore as useDictStore } from '@/stores/dict';
  const dictStore = useDictStore();

  const props = defineProps(['type', 'modelValue', 'disabled']);
  const emits = defineEmits(['update:modelValue']);
  function change(value) {
    emits('update:modelValue', value);
  }
  const dateValue = ref('');
  function initDateValue() {
    dateValue.value = props.modelValue;
  }
  onMounted(initDateValue);
  watch(() => props.modelValue, initDateValue);
</script>

<template>
  <el-color-picker
    :modelValue="props.modelValue"
    :disabled="props.disabled"
    @change="change"
    v-if="props.type === 'color'"
  />
  <el-date-picker
    :disabled="props.disabled"
    v-model="dateValue"
    :format="$date.date_pattern"
    :value-format="$date.date_pattern"
    @change="change"
    v-else-if="props.type === 'date'"
  />

  <el-input-number
    :disabled="props.disabled"
    :modelValue="+props.modelValue"
    @change="change"
    v-else-if="props.type === 'number'"
  />
  <el-select
    :disabled="props.disabled"
    :modelValue="props.modelValue"
    @change="change"
    v-else-if="props.type === 'unit'"
  >
    <el-option
      v-for="item in dictStore.per_unit().all"
      :key="item.dictValue"
      :label="item.dictLabel"
      :value="item.dictValue"
    />
  </el-select>
  <el-input
    :disabled="props.disabled"
    :modelValue="props.modelValue"
    @input="change"
    v-else
  />
</template>

<style scoped></style>

<script setup>
  import { nextTick, onMounted, ref, watch } from 'vue';

  const menuTree = ref(null);
  const props1 = defineProps(['nodeKey', 'modelValue', 'treeData', 'props']);
  const emit = defineEmits(['update:modelValue']);
  function init() {
    console.log(props1.modelValue);
    nextTick(() => {
      menuTree.value.setCheckedKeys(props1.modelValue);
    });
  }
  onMounted(init);
  watch(() => props1.modelValue, init);
  function checkChange() {
    emit('update:modelValue', menuTree.value.getCheckedKeys());
  }
</script>

<template>
  <el-tree
    @check-change="checkChange"
    ref="menuTree"
    :data="treeData"
    :node-key="nodeKey"
    show-checkbox
    :props="props"
  />
</template>

<style scoped lang="scss"></style>

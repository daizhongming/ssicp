<script setup>
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
</script>

<template>
  <LeftRightContainer>
    <template #left>
      <PipelineSelecter></PipelineSelecter>
    </template>
    <template #right>全生命周期管理</template>
  </LeftRightContainer>
</template>

<style scoped></style>

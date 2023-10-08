<script setup>
  const props = defineProps(['width']);
</script>

<template>
  <div class="leftRightContainer">
    <div class="left" :style="{ width: props.width || '20rem' }">
      <slot name="left"></slot>
    </div>
    <div class="right">
      <slot name="right"></slot>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .leftRightContainer {
    display: flex;
    flex-direction: row;
    height: 100%;

    .left {
      width: 20rem;
      height: 100%;
    }

    .right {
      flex: 1 0 0;
      width: 0;
      margin-left: 1rem;
    }
  }
</style>

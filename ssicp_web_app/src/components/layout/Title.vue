<script setup>
  const props = defineProps(['title']);
</script>

<template>
  <div class="title">
    <slot>{{ props.title }}</slot>
  </div>
</template>

<style scoped lang="scss">
  .title {
    height: 2rem;
    line-height: 2rem;
    background-color: rgba(255, 255, 255, 0.05);
    padding-left: 1rem;
  }
</style>

<script setup>
  const props = defineProps(['type', 'value']);
</script>

<template>
  <div :style="{ color: props.value || 'gray' }" v-if="props.type === 'color'">
    颜色
  </div>
  <div v-else>{{ props.value }}</div>
</template>

<style scoped></style>

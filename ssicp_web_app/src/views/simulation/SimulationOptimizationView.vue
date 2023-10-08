<script setup>
  import { useConfigStore } from '@/stores/config.js';
  const configStore = useConfigStore();
  import { useUserStore } from '@/stores/user.js';
  const userStore = useUserStore();
  import { computed } from 'vue';
  const url = computed(() => {
    return `${configStore.threeDimensionUrl}?username=${userStore.usernameFor3D}`;
  });
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
</script>

<template>
  <LeftRightContainer>
    <template #left>
      <PipelineSelecter></PipelineSelecter>
    </template>
    <template #right>
      <iframe
        allow="fullscreen"
        :src="url"
        frameborder="0"
        width="100%"
        height="100%"
      ></iframe>
    </template>
  </LeftRightContainer>
</template>

<style scoped></style>

<script setup>
  import { onMounted } from 'vue';
  import { RouterView } from 'vue-router';
  import { dictStore as useDictStore } from '@/stores/dict';
  const { queryAllDicts } = useDictStore();
  import { useConfigStore } from '@/stores/config';
  const { queryAllConfigs } = useConfigStore();
  import { useMenuStore } from '@/stores/menu';
  const { queryAllMenus } = useMenuStore();
  import { useButtonStore } from '@/stores/button';
  const { queryAllButtons } = useButtonStore();
  import { useDeviceStore } from '@/stores/device.js';
  const { queryAllDevices } = useDeviceStore();
  async function init() {
    queryAllDicts();
    queryAllButtons();
    queryAllConfigs();
    queryAllMenus();
    queryAllDevices();
  }

  onMounted(init);
</script>

<template>
  <RouterView />
</template>

<style scoped></style>

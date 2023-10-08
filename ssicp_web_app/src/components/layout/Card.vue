<script setup>
  const props = defineProps(['title']);
</script>

<template>
  <el-card class="card">
    <template #header v-if="props.title">
      <div class="card-title">{{ props.title || '标题' }}</div>
      <div class="card-menus"></div>
      <!-- <el-button>Operation button</el-button> -->
    </template>
    <slot>card content</slot>
    <!-- <div v-for="o in 4" :key="o">{{ 'List item ' + o }}</div> -->
  </el-card>
</template>

<style scoped lang="scss">
  .card {
    height: 100%;
    display: flex;
    flex-direction: column;
    // --el-card-border-color: var(--el-border-color);

    &:deep() > .el-card__header {
      width: 100%;
      padding: 0;

      background-image: url('@/assets/images/header_bg/bg_01.png'),
        url('@/assets/images/header_bg/bg_02.png'),
        url('@/assets/images/header_bg/bg_03.png');
      background-position: left top, 18rem top, right top;
      background-repeat: no-repeat, no-repeat, no-repeat;
      background-size: 18rem 100%, calc(100% - 18.5rem) 100%, 0.5rem 100%;
      width: 100%;
      height: 3rem;

      display: flex;
      /* justify-content: center; */
      align-items: center;
      border-bottom: none;

      & > .card-title {
        flex: 1 0 0;
        width: 0;
        margin-left: 3.5rem;
        font-size: 1.5rem;
        font-family: 'YouSheBiaoTiHei';
      }
    }

    &:deep() > .el-card__body {
      padding: 0.5rem;
      width: 100%;
      box-sizing: border-box;
      flex: 1 0 0;
      height: 0;
    }
  }
</style>

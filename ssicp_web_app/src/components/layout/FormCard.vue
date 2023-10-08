<script setup>
  const props = defineProps(['title']);
</script>

<template>
  <el-card class="formCard">
    <template #header v-if="props.title">
      <div class="card-title">
        <slot name="title">{{ props.title }}</slot>
      </div>
    </template>
    <slot>card content</slot>
  </el-card>
</template>

<style scoped lang="scss">
  .formCard {
    & + & {
      margin-top: 0.5rem;
    }
  }
  .formCard {
    border: none;
    box-shadow: none;
    background-color: transparent;
    & + .formCard {
      margin-top: 0rem;
    }
    &:deep() > .el-card__header {
      width: 100%;
      padding: 0;
      margin: 0;
      margin-bottom: 0.5rem;

      background-image: url('@/assets/images/dialog/formcardheader.png');
      background-repeat: no-repeat;
      background-size: auto 100%;
      width: 100%;
      height: 3rem;

      display: flex;
      align-items: center;
      border-bottom: none;

      & > .card-title {
        flex: 1 0 0;
        width: 0;
        margin-left: 5rem;
        font-size: 1.5rem;
        font-family: 'YouSheBiaoTiHei';
        &:empty {
          display: none;
        }
      }
    }

    &:deep() > .el-card__body {
      padding: 0;
      width: 100%;
      box-sizing: border-box;
    }
    &:deep() .el-select {
      width: 100%;
    }
    &:deep() .el-date-editor.el-input {
      width: 100%;
    }
    &:deep() .el-input-number {
      width: 100%;
    }
  }
</style>

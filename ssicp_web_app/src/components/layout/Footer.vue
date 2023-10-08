<script setup></script>

<template>
  <div class="footer">
    <slot></slot>
  </div>
</template>

<style scoped lang="scss">
  .footer {
    border-top: 1px solid var(--el-border-color);
    margin-top: 0.5rem;
    padding-top: 0.5rem;
    text-align: right;
  }
</style>

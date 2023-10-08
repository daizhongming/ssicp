<script setup></script>

<template>
  <!-- 默认左侧20rem宽度 -->
  <LeftRightContainer>
    <template #left> </template>
    <template #right></template>
  </LeftRightContainer>

  <!-- 动态修改左侧宽度 -->
  <LeftRightContainer width="30rem">
    <template #left> </template>
    <template #right></template>
  </LeftRightContainer>
</template>

<style scoped></style>

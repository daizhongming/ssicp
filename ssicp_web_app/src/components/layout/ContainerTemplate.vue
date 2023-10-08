<script setup></script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form> </template>
    <template #commands> </template>
    <template #table> </template>
  </Container>
</template>

<style scoped></style>

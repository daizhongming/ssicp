<script setup>
  import AlarmTodo from './components/todocenter/AlarmTodo.vue';
  import AlarmPie from './components/todocenter/AlarmPie.vue';
  import AlarmBar from './components/todocenter/AlarmBar.vue';
  import ErrorTodo from './components/todocenter/ErrorTodo.vue';
  import ErrorPie from './components/todocenter/ErrorPie.vue';
  import ErrorBar from './components/todocenter/ErrorBar.vue';
</script>

<template>
  <div class="todo">
    <Card title="指标检测报警分析">
      <div class="container">
        <div class="left">
          <AlarmPie />
        </div>
        <div class="right">
          <AlarmBar />
        </div>
      </div>
    </Card>
    <Card title="指标检测异常分析">
      <div class="container">
        <div class="left">
          <ErrorPie />
        </div>
        <div class="right">
          <ErrorBar />
        </div>
      </div>
    </Card>
    <Card title="报警处理代办（20/条）">
      <AlarmTodo />
    </Card>
    <Card title="异常处理代办（20/条）">
      <ErrorTodo />
    </Card>
  </div>
</template>

<style scoped lang="scss">
  .todo {
    height: 100%;
    display: grid;
    height: 100%;
    grid-template-rows: 1fr 1fr;
    grid-template-columns: 1fr 1fr;
    grid-gap: 1rem;
    .container {
      height: 100%;
      display: flex;
      & > div {
        flex: 1 0 0;
      }
    }
    .table {
      height: 100%;
    }
  }
</style>

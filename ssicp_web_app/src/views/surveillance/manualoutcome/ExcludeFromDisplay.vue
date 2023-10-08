<script setup>
  import { pageListOutcome as pageList } from '@/api/surveillance/manualInspection.js';
  import { watch, reactive, ref, onMounted } from 'vue';
  //自定义属性
  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    planCode: '',
    queryFlag: 'deputy',
    outcomeType: '3',
    prop: '',
    order: '',
  });
  const props = defineProps(['outcome']);

  const outcomes = ref([]);

  function outcomeTypeing(row) {
    if (row.state == 0) {
      //正常
      return '正常';
    } else if (row.state == 3) {
      const dueDate = new Date(row.dueDate);
      const currentDate = new Date();
      if (dueDate < currentDate) {
        return `<span style="color: red">逾期<span/>`;
      } else {
        return `<span style="color: #8BEBF2">未做<span/>`;
      }
    } else {
      //异常
      return '发现异常';
    }
  }

  async function sreach() {
    params.planCode = props.outcome.planCode;
    let data = await pageList(params);
    outcomes.value = data.data.data;
  }

  onMounted(async () => {
    sreach();
  });

  watch(
    () => props.outcome,
    (newValue, oldValue) => {
      sreach();
    }
  );
</script>
<template>
  <el-table
    :data="outcomes"
    height="30rem"
    class="el-table_withoutBottomBorder"
  >
    <el-table-column align="center" width="55px" type="index" label="序号" />
    <el-table-column
      align="center"
      min-width="130px"
      prop="planName"
      label="计划名称"
    >
      <template #default="scope">
        <span v-html="scope.row.planName"></span>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      width="90px"
      prop="nickName"
      label="负责人"
    />
    <el-table-column
      align="center"
      width="90px"
      prop="iterationNumber"
      label="巡检次数"
    />
    <el-table-column
      align="center"
      width="150px"
      prop="createTime"
      :formatter="$formatter.datetime"
      label="巡检开始时间"
    />
    <el-table-column
      align="center"
      width="150px"
      prop="dueDate"
      :formatter="$formatter.datetime"
      label="巡检截止时间"
    />
    <el-table-column align="center" width="90px" prop="state" label="巡检结果">
      <template #default="scope">
        <span v-html="outcomeTypeing(scope.row)"></span>
      </template>
    </el-table-column>
  </el-table>
</template>

<style scoped></style>

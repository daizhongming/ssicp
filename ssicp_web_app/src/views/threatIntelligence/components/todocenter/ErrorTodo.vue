<script setup>
  import InspectionoutcomeManage from '@/views/surveillance/inspectionoutcome/InspectionoutcomeManage.vue';
  import { queryExceptionToDos as pageList } from '@/api/surveillance/intelligentInspection.js';
  import { reactive, ref, onMounted } from 'vue';
  // import { listAllData } from '@/api/dict.js';
  import manualoutcomeManage from '@/views/surveillance/manualoutcome/ManualoutcomeManage.vue';

  const tableData = ref([]);

  async function search() {
    let response = await pageList();
    tableData.value = response.data;
  }
  onMounted(enumerate);

  const lookorupdate = ref();
  const visible = ref(false);
  const exception = ref({});
  const dialogVisible = ref(false);
  const dialogVisible1 = ref(false);
  const outcome = ref({});
  async function dispose(row) {
    if (row.outcomeType == '1') {
      console.log('智能巡检');
      outcome.value = row;
      dialogVisible.value = true;
    } else if (row.outcomeType == '0') {
      console.log('人工巡检');
      outcome.value = row;
      dialogVisible1.value = true;
    }
    // let data = await findByid(id);
    // exception.value = data.data;
    // visible.value = !visible.value;
    // lookorupdate.value = false;
  }

  const enums = reactive({});
  const dicttypes = ref([
    'inspection_type',
    'patrol_exception_type',
    'inspection_processing_type',
  ]);
  //枚举检索
  async function enumerate() {
    //获取压力等级
    // let data = await listAllData(dicttypes.value);
    // Object.assign(enums, data.data);
    search();
  }
</script>

<template>
  <el-table :data="tableData" border class="table">
    <el-table-column align="center" width="55px" type="index" label="序号" />
    <el-table-column
      align="center"
      min-width="110px"
      prop="planName"
      label="巡检名称"
    />
    <el-table-column
      align="center"
      width="185px"
      prop="outcomeType"
      label="巡检类型"
      :formatter="(...p) => $formatter.dict(enums.inspection_type, p[2])"
    />
    <el-table-column
      align="center"
      width="90px"
      prop="indexCount"
      label="巡检项"
    />
    <el-table-column
      align="center"
      width="185px"
      prop="exceptionCount"
      label="异常发现数量（项）"
    />
    <el-table-column
      align="center"
      width="165px"
      prop="createTime"
      label="异常发现时间"
      :formatter="$formatter.datetime"
    />
    <el-table-column
      label="操作处理"
      width="130px"
      fixed="right"
      align="center"
    >
      <template #default="scope">
        <el-button
          icon="Edit"
          type="primary"
          @click="dispose(scope.row)"
          text
          v-permission="'inspectionException.handle'"
        >
          处理
        </el-button>
        <!-- 
          <el-button
            icon="Edit"
            type="primary"
            v-if="scope.row.outcomeType == 1"
            @click="dispose(scope.row.id)"
            text
            v-permission="'inspectionException.handle'"
          >
            忽略
          </el-button> -->
      </template>
    </el-table-column>
  </el-table>

  <el-dialog
    v-model="dialogVisible1"
    title="查看智能巡检结果"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <manualoutcomeManage
      :outcome="outcome"
      :dispose="true"
      @save="
        search();
        dialogVisible1 = false;
      "
    />
  </el-dialog>

  <el-dialog
    v-model="dialogVisible"
    title="查看智能巡检结果"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <InspectionoutcomeManage
      :outcome="outcome"
      :dispose="true"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>

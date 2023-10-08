<script setup>
  import { ref, onMounted } from 'vue';
  import { search as searchLog } from '@/api/deviceAlarmLog.js';
  import EarlyWarningHandle from '@/views/threatIntelligence/components/EarlyWarningHandle.vue';
  const tableData = ref([]);

  const params = {
    page: 1,
    size: 1000,
    handleStatus: 0,
    order: '',
    prop: '',
  };
  async function search() {
    let response = await searchLog(params);
    tableData.value = response.data.data;
  }
  onMounted(search);
  //排序
  function sortChange({ order, prop }) {
    params.order = order;
    params.prop = prop;
    search();
  }

  import { dictStore } from '@/stores/dict';
  const { alarm_levels } = dictStore();

  const dialogVisible = ref(false);
  const warning = ref({});
  function handle(row) {
    warning.value = { ...row };
    dialogVisible.value = true;
  }

  import { useUserStore } from '@/stores/user.js';
  const userStore = useUserStore();
</script>

<template>
  <el-table :data="tableData" border class="table" @sort-change="sortChange">
    <el-table-column type="index" label="序号" width="50" align="center" />
    <el-table-column
      prop="deviceTypeName"
      label="报警对象"
      width="80"
      align="center"
    />
    <el-table-column
      prop="deviceName"
      label="名称/型号"
      min-width="100"
      align="center"
    />
    <el-table-column
      prop="indexName"
      label="检测指标"
      width="100"
      align="center"
    />
    <el-table-column
      prop="alarmValue"
      label="报警值"
      width="120"
      align="center"
    >
      <template #default="{ row }">
        {{ row.alarmValue + row.unit }}
      </template>
    </el-table-column>
    <el-table-column
      label="报警级别"
      sortable="custorm"
      prop="alarmLevel"
      width="120"
      align="center"
    >
      <template #default="scope">
        <el-tag
          :style="{
            color: alarm_levels().color(scope.row.alarmLevel),
          }"
        >
          {{ alarm_levels().label(scope.row.alarmLevel) }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column
      prop="startTime"
      :formatter="$formatter.datetime"
      label="报警时间"
      width="160"
      sortable="custorm"
      align="center"
    />
    <el-table-column label="报警次数" width="80" align="center">
      <template #default="scope">
        {{ scope.row.alarmTimes }}
      </template>
    </el-table-column>
    <el-table-column label="操作处理" width="80" fixed="right" align="center">
      <template #default="scope">
        <div>
          <el-button
            :disabled="!userStore.isInspector"
            icon="Edit"
            type="primary"
            text
            @click="handle(scope.row)"
          >
            处理
          </el-button>
        </div>
      </template>
    </el-table-column>
  </el-table>

  <!-- 处理弹出框 -->
  <el-dialog
    v-model="dialogVisible"
    title="报警处理与跟踪"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <EarlyWarningHandle
      :oldWarning="warning"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>

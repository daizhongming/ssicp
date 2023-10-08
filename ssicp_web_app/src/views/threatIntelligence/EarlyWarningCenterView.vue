<script setup>
  import EarlyWarningHandle from './components/EarlyWarningHandle.vue';
  import PipelineSelecter from '@/views/common/DeviceTreeSelecter.vue';
  import { ref, onMounted, reactive, getCurrentInstance } from 'vue';
  import { search as searchLog } from '@/api/deviceAlarmLog.js';

  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;
  import { dictStore } from '@/stores/dict';
  const { alarm_levels } = dictStore();

  import { useUserStore } from '@/stores/user.js';
  const userStore = useUserStore();

  const warnings = ref([]);
  const params = reactive({
    page: 1,
    size: 10,
    total: 0,
    keyword: null,
    dateRange: null,
    handleStatus: 0,
    alarmLevel: null,
    order: '',
    prop: '',
  });

  const handleStatuses = [
    { dictValue: 0, dictLabel: '未处理' },
    { dictValue: 1, dictLabel: '已处理' },
    { dictValue: 2, dictLabel: '已忽略' },
  ];

  async function search() {
    const dateRange = params.dateRange;
    const paramsNew = { ...params };
    delete paramsNew.dateRange;
    if (dateRange && dateRange.length == 2) {
      console.log(paramsNew);
      paramsNew.dateRangeStart = dateRange[0];
      paramsNew.dateRangeEnd = dateRange[1];
    }
    let response = await searchLog(paramsNew);

    params.total = response.data.total;
    $utils.highlight(
      response.data.data,
      ['deviceTypeName', 'deviceName', 'indexName'],
      params.keyword
    );
    warnings.value = response.data.data;
  }

  onMounted(() => {
    search();
  });

  const dialogVisible = ref(false);
  const warning = ref({});
  function handle(row) {
    warning.value = { ...row };
    dialogVisible.value = true;
  }

  //排序
  function sortChange({ order, prop }) {
    params.order = order;
    params.prop = prop;
    search();
  }
</script>

<template name="earlyWarningCenter">
  <!-- <LeftRightContainer>
    <template #left>
      <PipelineSelecter></PipelineSelecter>
    </template>
    <template #right> -->
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params">
        <el-form-item label="关键字">
          <el-input
            style="width: 400px"
            v-model="params.keyword"
            placeholder="按照报警对象、名称/型号、检测指标进行搜索"
            clearable
          />
        </el-form-item>
        <el-form-item label="报警时间">
          <el-date-picker
            value-format="YYYY-MM-DD"
            v-model="params.dateRange"
            type="daterange"
            range-separator="到"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          />
        </el-form-item>
        <el-form-item label="处理状态">
          <el-select v-model="params.handleStatus" placeholder="处理状态">
            <el-option
              :label="handleStatus.dictLabel"
              :key="handleStatus.dictValue"
              :value="handleStatus.dictValue"
              v-for="handleStatus in handleStatuses"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #table>
      <el-table border :data="warnings" @sort-change="sortChange">
        <el-table-column type="index" label="序号" align="center" width="50" />
        <el-table-column
          prop="deviceTypeName"
          label="报警对象"
          min-width="200"
          align="center"
        >
          <template #default="scope">
            <span v-html="scope.row.deviceTypeName_highlight"></span>
          </template>
        </el-table-column>
        <el-table-column
          prop="deviceName"
          min-width="200"
          label="名称/型号"
          align="center"
        >
          <template #default="scope">
            <span v-html="scope.row.deviceName_highlight"></span>
          </template>
        </el-table-column>
        <el-table-column prop="indexName" label="检测指标" align="center">
          <template #default="scope">
            <span v-html="scope.row.indexName_highlight"></span>
          </template>
        </el-table-column>
        <el-table-column
          prop="alarmValue"
          label="报警值"
          align="center"
          width="120"
        >
          <template #default="{ row }">
            {{ row.alarmValue + row.unit }}
          </template>
        </el-table-column>

        <el-table-column
          align="center"
          label="报警级别"
          sortable="custorm"
          prop="alarmLevel"
          width="120"
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
          align="center"
          prop="startTime"
          label="报警时间"
          width="160"
          sortable="custorm"
          :formatter="$formatter.datetime"
        />
        <el-table-column
          prop="alarmTimes"
          label="报警次数"
          align="center"
          width="150"
        />
        <el-table-column
          align="center"
          prop="handleStatus"
          label="处理状态"
          :formatter="(...a) => $formatter.dict(handleStatuses, a[2])"
        />
        <el-table-column
          align="center"
          prop="handleTime"
          label="处理时间"
          width="160"
          :formatter="$formatter.datetime"
        />
        <el-table-column
          prop="handleUserNickName"
          label="处理人"
          align="center"
        />
        <el-table-column
          prop="handleCostTime"
          label="处理用时"
          align="left"
          width="200"
          :formatter="$formatter.duration"
        />
        <el-table-column label="操作" align="center" fixed="right">
          <template #default="scope">
            <el-button
              v-if="scope.row.handleStatus === 0"
              text
              :disabled="!userStore.isInspector"
              type="primary"
              icon="Edit"
              @click="handle(scope.row)"
            >
              处理
            </el-button>
            <el-button
              type="primary"
              v-else
              text
              icon="View"
              @click="handle(scope.row)"
            >
              查看
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- </template>
  </LeftRightContainer> -->

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

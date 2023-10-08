<script setup>
  import {
    watch,
    ref,
    computed,
    onMounted,
    getCurrentInstance,
    reactive,
  } from 'vue';
  import {
    handle as handleLog,
    alarmDetails as alarmDetailsQuery,
    handleDetails as handleDetailsQuery,
  } from '@/api/deviceAlarmLog.js';
  import ImageUpload from '@/components/upload/ImageUpload.vue';
  import IndexLine from '@/views/common/IndexLine.vue';
  const props = defineProps(['oldWarning']);
  const emits = defineEmits(['save']);
  const requestParams = ref({});
  const { $date, $utils } =
    getCurrentInstance().appContext.config.globalProperties;

  const card1 = ref(null);
  const card2 = ref(null);
  onMounted(init);
  watch(() => props.oldWarning, init);
  function init() {
    const oldWarning = props.oldWarning;
    requestParams.value = {
      ignoreTime: oldWarning.ignoreTime,
      deviceId: oldWarning.deviceId,
      indexId: oldWarning.indexId,
      handleStatus: oldWarning.handleStatus ? oldWarning.handleStatus : 1,
      handleDescription: oldWarning.handleStatus
        ? oldWarning.handleDescription
        : null,
      alarmReason: oldWarning.handleStatus ? oldWarning.alarmReason : null,
      fileList: oldWarning.fileList ? oldWarning.fileList.split(';') : [],
      fileListBefore: oldWarning.fileListBefore
        ? oldWarning.fileListBefore.split(';')
        : [],
    };
    queryAlarmDetails();
    queryHandleDetails();
    card1.value = 'first';
    card2.value = 'first';
  }
  const alarmDetails = ref([{}, {}, {}]);

  async function queryAlarmDetails() {
    alarmDetails.value = [];
    const res = await alarmDetailsQuery(
      props.oldWarning.deviceId,
      props.oldWarning.indexId,
      $date.format(props.oldWarning.handleTime, 'YYYY-MM-DD HH:mm:ss')
    );
    alarmDetails.value = res.data;
  }

  const handleDetails = ref([{}, {}, {}]);
  async function queryHandleDetails() {
    handleDetails.value = [];
    const res = await handleDetailsQuery(
      props.oldWarning.deviceId,
      props.oldWarning.indexId,
      $date.format(props.oldWarning.handleTime, 'YYYY-MM-DD HH:mm:ss')
    );
    handleDetails.value = res.data;
  }

  const loading = ref(false);
  const proxyFunc = $utils.createLoadingProxy(save, loading);

  async function save() {
    let requestParamsNew = { ...requestParams.value };
    requestParamsNew.fileList = requestParamsNew.fileList.join(';');
    requestParamsNew.fileListBefore = requestParamsNew.fileListBefore.join(';');
    console.log(requestParamsNew);
    await handleLog(requestParamsNew);
    emits('save');
  }

  function rangeFormat() {
    const { lowerAlarm, seniorAlarm, seniorSeniorAlarm, unit } = arguments[0];
    return (
      lowerAlarm +
      unit +
      '/' +
      seniorAlarm +
      unit +
      '/' +
      seniorSeniorAlarm +
      unit
    );
  }

  const handleStatuses = [
    { dictValue: 1, dictLabel: '报警处理' },
    { dictValue: 2, dictLabel: '报警忽略' },
  ];
  import { dictStore } from '@/stores/dict';
  const { alarm_levels } = dictStore();
</script>

<template>
  <el-tabs type="card" v-model="card1">
    <el-tab-pane label="报警基本信息" name="first">
      <el-scrollbar height="25vh">
        <FormCard>
          <el-form label-width="80px">
            <el-row :gutter="16">
              <el-col :span="12">
                <el-form-item label="对象名称">
                  <el-input :value="oldWarning.deviceName" disabled />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="报警指标">
                  <el-input :value="oldWarning.indexName" disabled />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="报警指">
                  <el-input
                    :value="oldWarning.alarmValue + oldWarning.unit"
                    disabled
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="报警等级">
                  <el-input
                    :value="alarm_levels().label(oldWarning.alarmLevel)"
                    disabled
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="阈值参考">
                  <el-input
                    :value="`${oldWarning.lowerAlarm + oldWarning.unit}/${
                      oldWarning.seniorAlarm + oldWarning.unit
                    }/${oldWarning.seniorSeniorAlarm + oldWarning.unit}`"
                    disabled
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="处理方式">
                  <el-select
                    v-model="requestParams.handleStatus"
                    :disabled="oldWarning.handleStatus !== 0"
                  >
                    <el-option label="处理" :value="1">处理</el-option>
                    <el-option label="忽略" :value="2">忽略</el-option>
                  </el-select>
                </el-form-item>
              </el-col>

              <el-col :span="12" v-if="requestParams.handleStatus === 1">
                <el-form-item label="报警原因" required>
                  <el-input
                    :disabled="oldWarning.handleStatus !== 0"
                    :rows="3"
                    type="textarea"
                    v-model="requestParams.alarmReason"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12" v-if="requestParams.handleStatus === 1">
                <el-form-item label="处理简述" required>
                  <el-input
                    :disabled="oldWarning.handleStatus !== 0"
                    :rows="3"
                    type="textarea"
                    v-model="requestParams.handleDescription"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12" v-if="requestParams.handleStatus !== 1">
                <el-form-item label="忽略原因" required>
                  <el-input
                    :disabled="oldWarning.handleStatus !== 0"
                    :rows="3"
                    type="textarea"
                    v-model="requestParams.alarmReason"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12" v-if="requestParams.handleStatus !== 1">
                <el-form-item label="忽略时间">
                  <el-date-picker
                    :disabled="oldWarning.handleStatus !== 0"
                    v-model="requestParams.ignoreTime"
                    type="date"
                  />
                </el-form-item>
              </el-col>

              <el-col :span="12" v-if="requestParams.handleStatus === 1">
                <el-form-item label="处理前" required>
                  <ImageUpload
                    v-model="requestParams.fileListBefore"
                    :disabled="oldWarning.handleStatus !== 0"
                  />
                </el-form-item>
              </el-col>
              <el-col :span="12" v-if="requestParams.handleStatus === 1">
                <el-form-item label="处理后" required>
                  <ImageUpload
                    v-model="requestParams.fileList"
                    :disabled="oldWarning.handleStatus !== 0"
                  />
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </FormCard>
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="报警处理基本信息" name="second">
      <el-scrollbar height="25vh">
        <el-empty
          description="无处理信息"
          v-if="handleDetails.length === 0"
        ></el-empty>
        <FormCard v-else>
          <el-timeline>
            <el-timeline-item
              v-for="handleDetail in handleDetails"
              :key="handleDetail.handleTime"
              :timestamp="$date.datetime(handleDetail.handleTime)"
            >
              处理人：{{
                handleDetail.handleUserNickName
              }}&nbsp;&nbsp;&nbsp;&nbsp; 处理方式：{{
                $formatter.dict(handleStatuses, handleDetail.handleStatus)
              }}
            </el-timeline-item>
          </el-timeline>
        </FormCard>
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>

  <el-tabs type="card" v-model="card2">
    <el-tab-pane :label="`历史报警记录(${alarmDetails.length})条`" name="first">
      <el-scrollbar height="15vh">
        <FormCard>
          <el-table border :data="alarmDetails" height="15vh">
            <el-table-column type="index" label="序号" width="60" />
            <el-table-column label="检测指标" width="90">
              <template #default>{{ oldWarning.indexName }}</template>
            </el-table-column>
            <el-table-column prop="alarmValue" label="报警值" width="100">
              <template #default="{ row }">
                {{ row.alarmValue + row.unit }}
              </template>
            </el-table-column>
            <el-table-column label="报警级别" width="90">
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
              label="阈值范围"
              :formatter="rangeFormat"
              width="200"
            />
            <el-table-column
              prop="startTime"
              label="发生时间（倒序）"
              :formatter="$formatter.datetime"
            />
            <!-- <el-table-column prop="" label="预警频率" /> -->
          </el-table>
        </FormCard>
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="7天指标趋势分析" name="second">
      <div style="height: 15vh">
        <IndexLine :data="props.oldWarning" />
      </div>
    </el-tab-pane>
  </el-tabs>

  <Footer>
    <el-button size="large" @click="proxyFunc" v-loading="loading">
      保存
    </el-button>
  </Footer>
</template>

<style scoped lang="scss">
  .timelinewrap {
    .timeline {
      & + .timeline {
        margin-top: 1rem;
      }
      display: flex;

      .left {
        font-size: 1rem;
        width: 2.5rem;
        background-color: gray;
        text-align: center;
      }
      .center {
        position: relative;
        width: 3rem;
        display: flex;
        align-items: center; /* 垂直居中子元素 */
        justify-content: center; /* 水平居中子元素 */
        .circle {
          position: absolute;
          top: 1rem;
          width: 1rem;
          height: 1rem;
          background-color: gray;
          border-radius: 100%;
        }
        .bg {
          position: absolute;
          top: 0;
          bottom: 0;
          border-left: 1px dotted gray;
          left: 50%;
        }
      }
      .right {
        border: 1px solid gray;
        padding: 1rem;
        flex: 1 0 0;
        width: 0;
      }
    }
  }
</style>

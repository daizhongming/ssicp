<script setup>
  import { ref, onMounted, watch, reactive } from 'vue';
  import {
    getdispose,
    disposeOutcome,
    load,
  } from '@/api/surveillance/manualInspection.js';
  import { dictStore } from '@/stores/dict';
  import ImageUpload from '@/components/upload/ImageUpload.vue';
  import HistoryMetricsLineGraph from './HistoryMetricsLineGraph.vue';
  import _ from 'lodash';
  import { ElMessage } from 'element-plus';
  //自定义属性
  const props = defineProps(['outcome', 'dispose']);
  //自定义事件
  const emits = defineEmits(['save']);
  const { alarm_levels } = dictStore();
  const detailstab = ref({});

  const deviceType = ref({
    1: 'instrument',
    2: 'steam_traps',
    3: 'valve',
    4: 'pipe',
    5: 'steam_production_point',
    6: 'with_steam_spots',
    7: 'compensator',
  });

  const indexType = ref({
    0: 'first',
    1: 'second',
    2: 'third',
    3: 'fourth',
    4: 'fifth',
    5: 'sixth',
    6: 'seventh',
    7: 'eighth',
  });

  const decidetype = ref({
    0: {
      color: '',
      read: '否',
    },
    1: {
      color: 'red',
      read: '是',
    },
  });

  const graph = ref({});

  const disposedto = ref({});

  function outcomeTypeing(state) {
    if (state == 0) {
      //正常
      return '正常';
    } else {
      //异常
      return '发现异常';
    }
  }

  const details = ref({});
  const outcome = ref({});
  const activeName = ref('');
  const isEchoed = ref(false);
  async function echo() {
    _.each(props.outcome.loads, (type) => {
      _.each(type, (device) => {
        device.manualOutcomeImages.forEach((image) => {
          if (image.fileList) {
            image.fileList = image.fileList.split(';');
          }
        });
      });
    });
    if (props.outcome.loads) {
      details.value = props.outcome.loads;
    } else {
      let data = await load(outcome.value.outcomeCode);
      details.value = data.data;
    }
    activeName.value = Object.keys(details.value)[0];

    //判断是否有异常已处理
    isEchoed.value = props.outcome.state == 2;
    if (isEchoed.value) {
      let data = await getdispose(props.outcome.outcomeCode);
      data.data.forEach((item) => {
        const { deviceName, ...rest } = item;
        if (!disposedto.value[deviceName]) {
          disposedto.value[deviceName] = {};
        }
        disposedto.value[deviceName] = rest;
      });
    }
  }

  function getTableHeaderStyle(key, value2) {
    if (disposedto.value[key] == null && isEchoed) {
      disposedto.value[key] = {
        resolutionReason: '',
        summary: '',
        fileListBefore: [],
        fileList: [],
        remarks: '',
        detailsId:
          value2.manualOutcomeDetails == null
            ? []
            : value2.manualOutcomeDetails.map((item) => item.id),
        observesId:
          value2.manualOutcomeObserves == null
            ? []
            : value2.manualOutcomeObserves.map((item) => item.id),
        imagesId:
          value2.manualOutcomeImages == null
            ? []
            : value2.manualOutcomeImages.map((item) => item.id),
      };
    }
    return { color: 'red', details: true }; // 如果有不等于3的 alarmLevel，设置颜色为红色
  }
  const chushihua = ref(true);
  function faxian(value, key) {
    if (key) {
      if (!graph.value[key]) {
        graph.value[key] = 'first';
      }
      if (!detailstab.value[key]) {
        detailstab.value[key] = 'first';
      }
    }
    let abnormalCount = 0;
    let normalCount = 0;
    let group = value.manualOutcomeDetails;
    let countAbnormal = group.filter(
      (item) => item.alarmLevel != 3 && item.alarmLevel !== null
    ).length;
    abnormalCount += countAbnormal;
    normalCount += group.length;
    let group1 = value.manualOutcomeObserves;
    let countAbnormal1 = 0;
    try {
      countAbnormal1 = group1.filter((item) => item.decide != 0).length;
    } catch {}

    abnormalCount += countAbnormal1;
    normalCount += group1 ? group1.length : 0;
    if (abnormalCount > 0) {
      chushihua.value = false;
      return {
        span: `<span style="color:red;">发现异常</span>`,
        abnormalCount: abnormalCount,
        normalCount: normalCount,
        color: `red`,
      };
    } else {
      return {
        span: `<span style="color:green;">全部正常</span>`,
        abnormalCount: abnormalCount,
        normalCount: normalCount,
        color: ``,
      };
    }
  }

  async function save() {
    if (props.dispose) {
      await disposeOutcome(
        Object.values(disposedto.value).flatMap((innerObj) => innerObj),
        props.outcome.outcomeCode
      );
      ElMessage({
        message: '确认成功',
        type: 'success',
      });
    }
    emits('save');
  }

  function getdeviceId(value2) {
    let deviceId = '';
    let outcomeType = [
      'manualOutcomeDetails',
      'manualOutcomeObserves',
      'manualOutcomeImages',
    ];
    outcomeType.forEach((prop) => {
      // 使用 ?. 运算符来安全地访问属性并获取 id 值
      try {
        deviceId = value2.manualOutcomeDetails?.map((item) => item.deviceId);
      } catch {}
    });
    return deviceId[0];
  }

  onMounted(() => {
    outcome.value = props.outcome;
    //初始化数据
    echo();
  });

  watch(
    () => props.outcome,
    (newValue, oldValue) => {
      outcome.value = newValue;
      echo();
    }
  );
</script>

<template>
  <el-form label-width="80px">
    <FormCard title="人工巡检基本信息">
      <el-form label-width="80px">
        <el-row :gutter="16">
          <el-col :span="12">
            <el-form-item label="计划名称">
              <el-input v-model="outcome.planName" disabled />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item clearable label="完成时间">
              <el-date-picker
                disabled
                v-model="outcome.createTime"
                type="date"
              />
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="负责人">
              <el-input
                disabled
                v-model="outcome.nickName"
                placeholder="请选择(单选)"
              ></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="巡检结果">
              <el-input
                :value="outcomeTypeing(outcome.state)"
                type="text"
                disabled
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </FormCard>
  </el-form>
  <FormCard title="人工巡检明细">
    <el-tabs v-model="activeName" type="card">
      <el-tab-pane
        v-for="(value, key) in details"
        :key="key"
        :label="dictStore().dictData('device_type').label(key)"
        :name="key"
      >
        <el-scrollbar height="500px" class="global-hide-scrollbar">
          <el-tabs
            class="bottom15"
            v-model="detailstab[key2]"
            v-for="(value2, key2) in value"
          >
            <div class="bottom15">
              {{ dictStore().dictData('device_type').label(key) }}
              <span v-html="faxian(value2, key2).span"></span>
              | 监测指标共({{ faxian(value2).normalCount }})项
              <span v-if="faxian(value2).abnormalCount > 0">
                | 其中({{ faxian(value2).abnormalCount }})项指标异常;
              </span>
            </div>

            <el-tab-pane disabled>
              <template #label>
                <span :style="{ color: faxian(value2, key2).color }">
                  {{ key2 }}
                </span>
              </template>
            </el-tab-pane>
            <el-tab-pane label="巡检结果" name="first">
              <el-row :gutter="5">
                <el-col :span="12">
                  <Title>
                    <span>1)数据收集项</span>
                  </Title>
                  <el-card>
                    <el-scrollbar height="40px">
                      <el-row>
                        <el-col
                          :span="12"
                          v-for="manualOutcomeDetail in value2.manualOutcomeDetails"
                        >
                          <span>
                            {{ manualOutcomeDetail.index }}: [{{
                              manualOutcomeDetail.indexValue
                            }}{{ manualOutcomeDetail.unit }}],&nbsp;
                          </span>
                          <el-tag
                            :style="{
                              color: alarm_levels().color(
                                manualOutcomeDetail.alarmLevel
                              ),
                            }"
                          >
                            {{
                              alarm_levels().label(
                                manualOutcomeDetail.alarmLevel
                              )
                            }}
                          </el-tag>
                        </el-col>
                      </el-row>
                    </el-scrollbar>
                  </el-card>
                </el-col>
                <el-col :span="12">
                  <Title>
                    <span>2)观察判定项</span>
                  </Title>
                  <el-card>
                    <el-scrollbar height="40px">
                      <el-row>
                        <el-col
                          :span="12"
                          v-for="manualOutcomeDetail in value2.manualOutcomeObserves"
                        >
                          <span>
                            {{ manualOutcomeDetail.decideDescription }}:

                            <el-tag
                              :style="{
                                color:
                                  decidetype[manualOutcomeDetail.decide].color,
                              }"
                            >
                              {{ decidetype[manualOutcomeDetail.decide].read }}
                            </el-tag>
                          </span>
                        </el-col>
                      </el-row>
                    </el-scrollbar>
                  </el-card>
                </el-col>
                <el-col :span="24" class="top15">
                  <Title>
                    <span>3)巡检图片</span>
                  </Title>
                  <el-card>
                    <el-row>
                      <el-col
                        :span="12"
                        v-for="manualOutcomeDetail in value2.manualOutcomeImages"
                      >
                        <ImageUpload
                          v-model="manualOutcomeDetail.fileList"
                          :disabled="true"
                        />
                      </el-col>
                    </el-row>
                  </el-card>
                </el-col>
              </el-row>
              <el-form
                label-width="90px"
                v-if="
                  (props.dispose &&
                    faxian(value2).abnormalCount > 0 &&
                    getTableHeaderStyle(key2, value2).details) ||
                  (isEchoed && disposedto[key2] != null)
                "
              >
                <el-row style="padding-top: 15px" :gutter="16">
                  <el-col :span="12">
                    <el-form-item label="异常原因" required>
                      <el-input
                        v-model="disposedto[key2].resolutionReason"
                        type="textarea"
                        minlength="30"
                        maxlength="300"
                        :disabled="isEchoed"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="处理简述" required>
                      <el-input
                        v-model="disposedto[key2].summary"
                        :disabled="isEchoed"
                        type="textarea"
                        minlength="30"
                        maxlength="300"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="处理前" required>
                      <ImageUpload
                        v-model="disposedto[key2].fileListBefore"
                        :disabled="isEchoed"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="12">
                    <el-form-item label="处理后" required>
                      <ImageUpload
                        v-model="disposedto[key2].fileList"
                        :disabled="isEchoed"
                      />
                    </el-form-item>
                  </el-col>
                  <el-col :span="24">
                    <el-form-item label="备注">
                      <el-input
                        type="textarea"
                        minlength="3"
                        maxlength="300"
                        show-word-limit
                        v-model="disposedto[key2].remarks"
                        :disabled="isEchoed"
                      />
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-form>
            </el-tab-pane>

            <el-tab-pane label="历史趋势" name="second">
              <el-tabs v-model="graph[key2]" type="card" class="demo-tabs">
                <el-tab-pane
                  :label="dict.dictLabel"
                  :name="indexType[$index]"
                  v-for="(dict, $index) in dictStore().dictData(
                    deviceType[key] + '_index'
                  ).all"
                >
                  <div style="height: 300px">
                    <HistoryMetricsLineGraph
                      :deviceId="getdeviceId(value2)"
                      :index="dict.dictValue"
                    />
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
          </el-tabs>
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
  </FormCard>
  <Footer>
    <el-button size="large" @click="save()">确认</el-button>
  </Footer>
</template>

<style lang="scss">
  .bottom15 {
    padding-bottom: 15px;
  }

  .top15 {
    padding-top: 15px;
  }
</style>

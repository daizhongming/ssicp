<script setup>
  import { watch, ref, onMounted } from 'vue';
  import {
    disposeOutcome,
    loadOutcome,
    getdispose,
  } from '@/api/surveillance/intelligentInspection.js';
  import { ElMessage } from 'element-plus';
  import { dictStore } from '@/stores/dict';
  import ImageUpload from '@/components/upload/ImageUpload.vue';
  const { device_type, alarm_levels } = dictStore();
  const props = defineProps(['outcome', 'dispose']);
  const emits = defineEmits(['save']);
  const activeName = ref();

  onMounted(() => {
    search();
  });

  watch(
    () => props.outcome,
    (newValue, oldValue) => {
      search();
    }
  );

  async function chongzhi() {
    aaa.value = false;
    disposedto.value = {};
  }

  const outcome = ref({});
  const details = ref({});
  const aaa = ref(false);

  async function search() {
    outcome.value = props.outcome;
    if (props.outcome.loads) {
      details.value = props.outcome.loads;
    } else {
      let data = await loadOutcome(props.outcome.outcomeCode);
      details.value = data.data;
    }
    //查询处理结果
    await chongzhi();
    let data2 = await getdispose(props.outcome.outcomeCode);
    console.log('内容', data2);
    data2.data.forEach((item) => {
      const { deviceId, ...rest } = item;
      if (!disposedto.value[deviceId]) {
        disposedto.value[deviceId] = {};
      }
      disposedto.value[deviceId] = rest;
      aaa.value = true;
    });
    activeName.value = Object.keys(details.value)[0];
  }

  const disposedto = ref({});
  // 计算属性，根据 alarmLevel 判断表头颜色
  const getTableHeaderStyle = (key, value2) => {
    const hasNon3Alarm = value2.some(
      (item) => item.alarmLevel !== '3' && item.alarmLevel != null
    );
    if (hasNon3Alarm) {
      console.log(key, disposedto.value[key] == null && aaa && key != null);
      if (disposedto.value[key] == null && aaa && key != null) {
        disposedto.value[key] = {
          resolutionReason: '',
          summary: '',
          fileListBefore: [],
          fileList: [],
          remarks: '',
          detailsId: value2
            .filter((item) => item.alarmLevel !== '3')
            .map((item) => item.detailsId),
        };
      }
      return { color: 'color:red', details: true }; // 如果有不等于3的 alarmLevel，设置颜色为红色
    } else {
      return {
        color: '',
        details: false,
      }; // 如果都等于3，保持默认颜色
    }
  };

  function faxian(value) {
    let abnormalCount = 0;
    let normalCount = 0;
    for (const key in value) {
      if (value.hasOwnProperty(key)) {
        const group = value[key];
        const hasAbnormal = group.filter(
          (item) => item.alarmLevel != '3' && item.alarmLevel != null
        ).length;
        if (hasAbnormal) {
          abnormalCount += hasAbnormal;
        }
        normalCount += group.length;
      }
    }
    if (abnormalCount > 0) {
      return {
        span: `<span style="color:red;">发现异常</span>`,
        abnormalCount: abnormalCount,
        normalCount: normalCount,
      };
    } else {
      return {
        span: `<span style="color:green;">全部正常</span>`,
        abnormalCount: abnormalCount,
        normalCount: normalCount,
      };
    }
  }

  async function confirm() {
    if (props.dispose) {
      console.log(disposedto.value);
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
  function outcomeTypeing(row) {
    if (row.state == 0) {
      //正常
      return '正常';
    } else {
      //异常
      return '发现异常';
    }
  }
</script>

<template>
  <FormCard title="巡检计划基本信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="计划名称">
            <el-input :value="outcome.planName" disabled />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="完成时间">
            <el-input
              :value="$date.datetime(outcome.createTime)"
              disabled
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="负责人">
            <el-input :value="outcome.nickName" disabled />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="巡检结果">
            <el-input :value="outcomeTypeing(outcome)" disabled />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </FormCard>
  <FormCard title="计划巡检内容">
    <el-tabs
      v-model="activeName"
      v-if="details && Object.keys(details).length > 0"
    >
      <el-tab-pane
        v-for="(value, key) in details"
        :key="key"
        :label="device_type().label(key)"
        :name="key"
      >
        <el-scrollbar
          height="500px"
          class="global-disable-horizontal-scroll global-hide-scrollbar"
        >
          <div class="miaoshu">
            {{ device_type().label(key) }}
            <span v-html="faxian(value).span"></span>
            | 监测指标共({{ faxian(value).normalCount }})项
            <span v-if="faxian(value).abnormalCount > 0">
              | 其中({{ faxian(value).abnormalCount }})项指标异常;
            </span>
          </div>
          <div v-for="(value2, key2) in value" :key="key2" class="xiangxi">
            <Title>
              <span :style="getTableHeaderStyle(key2, value2).color">
                {{ value2[0].deviceName }}
              </span>
            </Title>

            <el-table :data="value2" class="el-table_withoutBottomBorder">
              <el-table-column
                type="index"
                label="序号"
                width="50px"
                align="center"
              />
              <el-table-column prop="index" label="检测指标" align="center" />
              <el-table-column label="指标值" align="center">
                <template #default="scope">
                  {{ scope.row.indexValue }} {{ scope.row.unit }}
                </template>
              </el-table-column>
              <el-table-column prop="alarmLevel" label="状态" align="center">
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
                prop="lowerAlarm"
                label="低报参照"
                align="center"
              >
                <template #default="scope">
                  <spam v-if="scope.row.lowerAlarm != null">
                    &lt; {{ scope.row.lowerAlarm }} {{ scope.row.unit }}
                  </spam>
                </template>
              </el-table-column>
              <el-table-column
                prop="seniorAlarm"
                label="高报参照"
                align="center"
              >
                <template #default="scope">
                  <spam v-if="scope.row.seniorAlarm != null">
                    &gt; {{ scope.row.seniorAlarm }} {{ scope.row.unit }}
                  </spam>
                </template>
              </el-table-column>
              <el-table-column
                prop="seniorSeniorAlarm"
                label="高高报参照"
                align="center"
              >
                <template #default="scope">
                  <spam v-if="scope.row.seniorSeniorAlarm != null">
                    &gt; {{ scope.row.seniorSeniorAlarm }} {{ scope.row.unit }}
                  </spam>
                </template>
              </el-table-column>
            </el-table>
            <el-form
              label-width="90px"
              v-if="
                (props.dispose && getTableHeaderStyle(null, value2).details) ||
                (aaa && disposedto[key2] != null)
              "
            >
              <el-row style="padding-top: 15px" :gutter="16">
                <el-col :span="12">
                  <el-form-item label="异常原因" required>
                    <el-input
                      type="textarea"
                      v-model="disposedto[key2].resolutionReason"
                      :disabled="!props.dispose"
                      minlength="30"
                      maxlength="300"
                      show-word-limit
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="处理简述" required>
                    <el-input
                      type="textarea"
                      v-model="disposedto[key2].summary"
                      :disabled="!props.dispose"
                      minlength="30"
                      maxlength="300"
                      show-word-limit
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="处理前" required>
                    <ImageUpload
                      v-model="disposedto[key2].fileListBefore"
                      :disabled="!props.dispose"
                    />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="处理后" required>
                    <ImageUpload
                      v-model="disposedto[key2].fileList"
                      :disabled="!props.dispose"
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
                      :disabled="!props.dispose"
                    />
                  </el-form-item>
                </el-col>
              </el-row>
            </el-form>
          </div>
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
    <div v-else>没有巡检内容</div>
  </FormCard>
  <Footer>
    <el-button size="large" @click="confirm()">确认</el-button>
  </Footer>
</template>

<style lang="scss">
  .miaoshu {
    padding-bottom: 15px;
  }
  .xiangxi {
    padding-bottom: 15px;
  }
</style>

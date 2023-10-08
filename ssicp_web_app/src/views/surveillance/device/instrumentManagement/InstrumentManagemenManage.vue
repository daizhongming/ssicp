<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import { dictStore } from '@/stores/dict';
  import { add, getByid, update } from '@/api/surveillance/device.js';
  import PipeSectionRadio from '../PipeSectionRadio.vue';
  import { pageList } from '@/api/surveillance/Pipework.js';
  import ParametersettingsManage from '@/views/surveillance/ParametersettingsManage.vue';
  import { ElMessage } from 'element-plus';
  const props = defineProps(['did', 'nowdata']);
  const emits = defineEmits(['okok']);
  const instrument = reactive({
    device: {
      id: '',
      deviceName: '',
      parentDeviceType: '1',
      deviceType: '',
      deviceCode: '',
    },
    deviceInstrument: {
      workYears: '',
      useTime: '',
      usageDuration: '',
      checkPeriod: '',
      pipeInlet: '',
      qcDate: '',
      pipeExit: '',
    },
    deviceAlarmList: [],
  });

  watch(
    () => props.did,
    (newValue, oldValue) => {
      echo();
    }
  );

  watch(
    () => props.nowdata,
    (newValue, oldValue) => {
      echo();
    }
  );

  async function chongzhi() {
    Object.assign(instrument, {
      device: {
        id: '',
        deviceName: '',
        parentDeviceType: '1',
        deviceType: '',
        deviceCode: '',
      },
      deviceInstrument: {
        workYears: '',
        useTime: '',
        usageDuration: '',
        checkPeriod: '',
        pipeInlet: '',
        qcDate: '',
        pipeExit: '',
      },
      deviceAlarmList: [],
    });
  }

  async function echo(did) {
    await chongzhi();
    if (props.did) {
      let data = await getByid(props.did);
      Object.assign(instrument, data.data);
    }
  }

  const pipes = ref([]);

  onMounted(async () => {
    let data = await pageList({
      keyword: '',
      region: '',
      total: 0,
      page: 1,
      size: 1000,
    });
    pipes.value = data.data.data;
    echo(props.did);
  });

  const dialogVisible = ref(false);
  const inletOrexit = ref('');
  const pid = ref();
  const notpid = ref();
  async function dadio(i, id) {
    pid.value = id;
    if (i == 1) {
      //出口
      notpid.value = instrument.deviceInstrument.pipeInlet;
      inletOrexit.value = 'exit';
    } else {
      //进口
      notpid.value = instrument.deviceInstrument.pipeExit;
      inletOrexit.value = 'inlet';
    }
    dialogVisible.value = true;
  }

  async function radiook(pid) {
    // console.log('选完了',pid);
    dialogVisible.value = false;
    if (inletOrexit.value === 'exit') {
      //出口
      console.log('出口');
      instrument.deviceInstrument.pipeExit = pid;
    } else if (inletOrexit.value === 'inlet') {
      //进口
      console.log('进口');
      instrument.deviceInstrument.pipeInlet = pid;
    } else {
      return;
    }
  }

  const inlet = (pipecode) => {
    let code = pipes.value
      .filter((item) => item.id == pipecode)
      .map((item) => item.pipeCode);
    if (code) {
      return code[0];
    }
    return '';
  };

  async function submitForm() {
    if (instrument.device.id) {
      //修改
      await update(instrument);
    } else {
      //添加
      await add(instrument);
      chongzhi();
    }
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    emits('okok');
  }
</script>

<template>
  <FormCard title="仪表基本信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="仪表编号" required>
            <el-input
              v-model="instrument.device.deviceCode"
              clearable
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="仪表名称" required>
            <el-input
              v-model="instrument.device.deviceName"
              clearable
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="仪表类型" required>
            <el-select
              clearable
              v-model="instrument.device.deviceType"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="yali in dictStore().pipeline_yibiao_type().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工作年限">
            <el-input
              v-model="instrument.deviceInstrument.workYears"
              placeholder="请输入"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d]/g,'')"
              clearable
            >
              <template #append>年</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="投用日期">
            <el-date-picker
              clearable
              v-model="instrument.deviceInstrument.useTime"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="使用年限">
            <el-input
              v-model="instrument.deviceInstrument.usageDuration"
              placeholder="请输入"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d]/g,'')"
              clearable
            >
              <template #append>年</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查周期">
            <el-input
              v-model="instrument.deviceInstrument.checkPeriod"
              placeholder="请输入"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d]/g,'')"
              clearable
            >
              <template #append>月</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="质检日期">
            <el-date-picker
              clearable
              v-model="instrument.deviceInstrument.qcDate"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </FormCard>
  <FormCard title="连接管段信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="管段入口" required>
            <el-input
              :value="inlet(instrument.deviceInstrument.pipeInlet)"
              v-model="instrument.deviceInstrument.pipeInlet"
              clearable
              readonly="readonly"
              @click="dadio(0, instrument.deviceInstrument.pipeInlet)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段出口" required>
            <el-input
              readonly="readonly"
              :value="inlet(instrument.deviceInstrument.pipeExit)"
              v-model="instrument.deviceInstrument.pipeExit"
              clearable
              @click="dadio(1, instrument.deviceInstrument.pipeExit)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </FormCard>
  <FormCard title="监测指标报警设置">
    <ParametersettingsManage
      :deviceAlarms="instrument.deviceAlarmList"
      :dictData="dictStore().instrument_index().all"
    />
  </FormCard>

  <el-dialog
    v-model="dialogVisible"
    title="选择管段（单选）"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <PipeSectionRadio @okok="radiook" :pid="pid" :notpid="notpid" />
  </el-dialog>
  <Footer>
    <el-button size="large" @click="submitForm()">确定</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

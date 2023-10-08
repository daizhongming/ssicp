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
  const devicedto = reactive({
    device: {
      id: '',
      deviceName: '',
      parentDeviceType: '7',
      deviceType: '',
      deviceCode: '',
    },
    deviceCompensator: {
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
    () => props.nowdata,
    (newValue, oldValue) => {
      echo();
    }
  );

  watch(
    () => props.did,
    (newValue, oldValue) => {
      echo(newValue);
    }
  );

  async function chongzhi() {
    Object.assign(devicedto, {
      device: {
        id: '',
        deviceName: '',
        parentDeviceType: '7',
        deviceType: '',
        deviceCode: '',
      },
      deviceCompensator: {
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
    if (did) {
      let data = await getByid(did);
      Object.assign(devicedto, data.data);
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
      inletOrexit.value = 'exit';
      notpid.value = devicedto.deviceCompensator.pipeInlet;
    } else {
      //进口
      inletOrexit.value = 'inlet';
      notpid.value = devicedto.deviceCompensator.pipeExit;
    }
    dialogVisible.value = true;
  }

  async function radiook(pid) {
    dialogVisible.value = false;
    if (inletOrexit.value === 'exit') {
      //出口
      console.log('出口');
      devicedto.deviceCompensator.pipeExit = pid;
    } else if (inletOrexit.value === 'inlet') {
      //进口
      console.log('进口');
      devicedto.deviceCompensator.pipeInlet = pid;
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
    if (devicedto.device.id) {
      //修改
      await update(devicedto);
    } else {
      //添加
      await add(devicedto);
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
  <FormCard title="补偿器基本信息">
    <el-form label-width="100px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="补偿器编号" required>
            <el-input
              v-model="devicedto.device.deviceCode"
              clearable
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="补偿器名称" required>
            <el-input
              v-model="devicedto.device.deviceName"
              clearable
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="补偿器类型" required>
            <el-select
              clearable
              v-model="devicedto.device.deviceType"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="yali in dictStore().pipeline_compensator_type().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工作年限">
            <el-input
              v-model="devicedto.deviceCompensator.workYears"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d.]/g,'')"
              placeholder="请输入"
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
              v-model="devicedto.deviceCompensator.useTime"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="使用年限">
            <el-input
              v-model="devicedto.deviceCompensator.usageDuration"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d]/g,'')"
              placeholder="请输入"
              clearable
            >
              <template #append>年</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检查周期">
            <el-input
              v-model="devicedto.deviceCompensator.checkPeriod"
              minlength="3"
              maxlength="10"
              show-word-limit
              oninput="value=value.replace(/[^\d]/g,'')"
              placeholder="请输入"
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
              v-model="devicedto.deviceCompensator.qcDate"
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
              :value="inlet(devicedto.deviceCompensator.pipeInlet)"
              v-model="devicedto.deviceCompensator.pipeInlet"
              clearable
              readonly="readonly"
              @click="dadio(0, devicedto.deviceCompensator.pipeInlet)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段出口" required>
            <el-input
              :value="inlet(devicedto.deviceCompensator.pipeExit)"
              v-model="devicedto.deviceCompensator.pipeExit"
              clearable
              readonly="readonly"
              @click="dadio(1, devicedto.deviceCompensator.pipeExit)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </FormCard>
  <FormCard title="监测指标报警设置">
    <ParametersettingsManage
      :deviceAlarms="devicedto.deviceAlarmList"
      :dictData="dictStore().compensator_index().all"
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

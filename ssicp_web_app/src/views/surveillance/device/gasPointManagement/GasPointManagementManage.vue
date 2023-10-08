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
      parentDeviceType: '5',
      deviceType: '5',
      deviceCode: '',
    },
    deviceGasPoint: {
      region: '',
      pid: '',
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
    Object.assign(devicedto, {
      device: {
        id: '',
        deviceName: '',
        parentDeviceType: '5',
        deviceType: '5',
        deviceCode: '',
      },
      deviceGasPoint: {
        region: '',
        pid: '',
      },
      deviceAlarmList: [],
    });
  }

  async function echo() {
    await chongzhi();
    if (props.did) {
      let data = await getByid(props.did);
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
  const notpid = ref('');
  async function dadio(i, id) {
    pid.value = id;
    if (i == 1) {
      //出口
      inletOrexit.value = 'exit';
    } else {
      //进口
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
      devicedto.deviceGasPoint.pipeExit = pid;
    } else if (inletOrexit.value === 'inlet') {
      //进口
      console.log('进口');
      devicedto.deviceGasPoint.pid = pid;
    } else {
      return;
    }
  }

  const devicename = ref('入口');
  watch(
    () => devicedto.device.parentDeviceType,
    (newValue, oldValue) => {
      console.log(newValue);
      if (newValue == 5) {
        //产汽点
        devicename.value = '入口';
      }
      if (newValue == 6) {
        //用汽点
        devicename.value = '出口';
      }
    }
  );

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

  watch(
    () => devicedto.device.parentDeviceType,
    (newValue, oldValue) => {
      devicedto.device.deviceType = newValue;
    }
  );
</script>

<template>
  <FormCard title="仪表基本信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="对象类型" required>
            <el-select
              clearable
              v-model="devicedto.device.parentDeviceType"
              placeholder="请选择(单选)"
            >
              <el-option label="产汽点" value="5" />
              <el-option label="用汽点" value="6" />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="" required>
            <template #label>对象编号</template>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.device.deviceCode"
              clearable
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="对象名称" required>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.device.deviceName"
              clearable
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="所属区域" required>
            <el-select
              clearable
              v-model="devicedto.deviceGasPoint.region"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="yali in dictStore().regional_division().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="" required>
            <template #label>管段{{ devicename }}</template>
            <el-input
              readonly="readonly"
              :value="inlet(devicedto.deviceGasPoint.pid)"
              v-model="devicedto.deviceGasPoint.pid"
              clearable
              @click="dadio(0, devicedto.deviceGasPoint.pid)"
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
      :dictData="dictStore().with_steam_spots_index().all"
    />
  </FormCard>

  <el-dialog
    v-model="dialogVisible"
    title="选择管段（单选）"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <PipeSectionRadio @okok="radiook" :notpid="notpid" :pid="pid" />
  </el-dialog>
  <Footer>
    <el-button size="large" @click="submitForm()">确定</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

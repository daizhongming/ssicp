<script setup>
  import { reactive, ref, onMounted, watch } from 'vue';
  import { dictStore } from '@/stores/dict';
  import { add, getByid, update } from '@/api/surveillance/device.js';
  import PipeSectionRadio from '../PipeSectionRadio.vue';
  import { pageList } from '@/api/surveillance/Pipework.js';
  import { ElMessage } from 'element-plus';
  import { isArray } from 'lodash';
  const props = defineProps(['did', 'nowdata']);
  const emits = defineEmits(['okok']);
  const instrument = reactive({
    device: {
      id: '',
      deviceName: '',
      parentDeviceType: '9',
      deviceType: '',
      deviceCode: '',
    },
    deviceElbowTree: {
      workYears: '',
      useTime: '',
      usageDuration: '',
      checkPeriod: '',
      pipeInlet: '',
      qcDate: '',
      pipeExit: '',
    },
  });

  watch(
    () => props.did,
    (newValue, oldValue) => {
      echo(newValue);
    }
  );

  watch(() => {
    prop.nowdata,
      (newValue, oldValue) => {
        echo();
      };
  });

  const devicetitle = ref('弯头');
  const devicetype = ref('pipeline_elbow_type');
  watch(
    () => instrument.device.parentDeviceType,
    (newValue, oldValue) => {
      if (newValue == '9') {
        devicetitle.value = '弯头';
        devicetype.value = 'pipeline_elbow_type';
      } else if (newValue == '8') {
        devicetype.value = 'pipeline_tree_type';
        devicetitle.value = '三通';
      }
    }
  );

  async function chongzhi() {
    Object.assign(instrument, {
      device: {
        id: '',
        deviceName: '',
        parentDeviceType: '9',
        deviceType: '',
        deviceCode: '',
      },
      deviceElbowTree: {
        workYears: '',
        useTime: '',
        usageDuration: '',
        checkPeriod: '',
        pipeInlet: '',
        qcDate: '',
        pipeExit: '',
      },
    });
  }

  async function echo(did) {
    await chongzhi();
    if (did) {
      let data = await getByid(did);
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
  const count = ref(2);
  async function dadio(i, id) {
    pid.value = id;
    if (i == 1) {
      //出口
      notpid.value = instrument.deviceElbowTree.pipeInlet;
      inletOrexit.value = 'exits';
    } else {
      //进口
      notpid.value = instrument.deviceElbowTree.pipeExit;
      inletOrexit.value = 'inlet';
    }
    let le = 1;
    try {
      le = JSON.parse(notpid.value).length;
      // 你的逻辑代码
    } catch (e) {
      le = 1;
    }
    count.value = 3 - le;
    console.log('看看count', count.value);

    dialogVisible.value = true;
  }

  async function radiook(pid) {
    dialogVisible.value = false;
    let pids = [];
    if (isArray(pid)) {
      pid.forEach((id) => {
        pids.push(id);
      });
    } else {
      pids.push(pid);
    }
    pids = pids.filter(
      (value) => value !== null && value !== undefined && value !== ''
    );
    console.log('ok了昂', pids);
    if (inletOrexit.value == 'exits') {
      //出口
      instrument.deviceElbowTree.pipeExit = JSON.stringify(pids);
    } else if (inletOrexit.value == 'inlet') {
      //进口
      instrument.deviceElbowTree.pipeInlet = JSON.stringify(pids);
    } else {
      return;
    }
  }

  function inlet(pipecode) {
    if (!pipecode) {
      return '';
    }
    let pipecodes = JSON.parse(pipecode);
    if (Array.isArray(pipecodes)) {
      let code = [];
      pipecodes.forEach((c) => {
        let a = pipes.value
          .filter((item) => item.id == c)
          .map((item) => item.pipeCode);
        code.push(a);
      });
      if (code) {
        return code.join(',');
      }
      return '';
    } else {
      let code = pipes.value
        .filter((item) => item.id == pipecodes)
        .map((item) => item.pipeCode);

      if (code) {
        return code;
      }
      return '';
    }
  }

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
  <FormCard title="三通/弯头基本信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="24">
          <el-form-item label="对象类型" required>
            <el-select
              clearable
              v-model="instrument.device.parentDeviceType"
              placeholder="请选择(单选)"
            >
              <el-option label="弯头" value="9" />
              <el-option label="三通" value="8" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="" required>
            <template #label>{{ devicetitle }}编号</template>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="instrument.device.deviceCode"
              clearable
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="" required>
            <template #label>{{ devicetitle }}名称</template>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="instrument.device.deviceName"
              clearable
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="" required>
            <template #label>{{ devicetitle }}类型</template>
            <el-select
              clearable
              v-model="instrument.device.deviceType"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="yali in dictStore().dictData(devicetype).all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工作年限">
            <el-input
              v-model="instrument.deviceElbowTree.workYears"
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
          <el-form-item label="投用日期">
            <el-date-picker
              clearable
              v-model="instrument.deviceElbowTree.useTime"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="使用年限">
            <el-input
              v-model="instrument.deviceElbowTree.usageDuration"
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
              v-model="instrument.deviceElbowTree.checkPeriod"
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
              v-model="instrument.deviceElbowTree.qcDate"
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
              :value="inlet(instrument.deviceElbowTree.pipeInlet)"
              v-model="instrument.deviceElbowTree.pipeInlet"
              clearable
              readonly="readonly"
              @click="dadio(0, instrument.deviceElbowTree.pipeInlet)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段出口" required>
            <el-input
              :value="inlet(instrument.deviceElbowTree.pipeExit)"
              v-model="instrument.deviceElbowTree.pipeExit"
              clearable
              readonly="readonly"
              @click="dadio(1, instrument.deviceElbowTree.pipeExit)"
              placeholder="必填项"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </FormCard>
  <el-dialog
    v-model="dialogVisible"
    title="选择管段"
    width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
  >
    <PipeSectionRadio
      @okok="radiook"
      :count="count"
      :tt="devicetitle"
      :pid="pid"
      :notpid="notpid"
    />
  </el-dialog>
  <Footer>
    <el-button size="large" @click="submitForm()">确定</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

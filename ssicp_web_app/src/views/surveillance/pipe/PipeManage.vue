<script setup>
  import { reactive, ref, onMounted, watch, watchEffect } from 'vue';
  import { dictStore } from '@/stores/dict';
  import { add, getByid, update } from '@/api/surveillance/device.js';
  import { ElMessage } from 'element-plus';
  import ParametersettingsManage from '../ParametersettingsManage.vue';
  const props = defineProps(['pid', 'nowdata']);
  const emits = defineEmits(['okok']);

  const devicedto = reactive({
    device: {
      deviceName: '',
      parentDeviceType: '4',
      deviceType: '0',
      deviceCode: '',
    },
    pipeAdd: {
      pipe: {},
      pipeMaintenance: {},
      pipeDevise: {},
      pipeInsulation: {},
    },
    deviceAlarmList: [],
  });

  watchEffect(() => {
    devicedto.device.deviceName = devicedto.pipeAdd.pipe.pipeName;
    devicedto.device.deviceCode = devicedto.pipeAdd.pipe.pipeCode;
  });

  const pid = ref();

  onMounted(echo);

  watch(
    () => props.pid,
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

  //回显
  async function echo() {
    pid.value = props.pid;
    await chongzhi();
    if (!pid.value) {
      return;
    }
    let data = await getByid(pid.value);
    Object.assign(devicedto, data.data);
    console.log(devicedto);
  }

  async function submitForm() {
    if (devicedto.pipeAdd.pipe.id) {
      await update(devicedto);
    } else {
      await add(devicedto);
    }
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    emits('okok');
  }

  async function chongzhi() {
    Object.assign(devicedto, {
      device: {
        deviceName: '',
        parentDeviceType: '4',
        deviceType: '0',
        deviceCode: '',
      },
      pipeAdd: {
        pipe: {},
        pipeMaintenance: {},
        pipeDevise: {},
        pipeInsulation: {},
      },
      deviceAlarmList: [],
    });
  }
</script>

<template>
  <el-form label-width="100px">
    <FormCard title="基本信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="管段编号" required>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.pipeCode"
              placeholder="(必填项)"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段名称" required>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.pipeName"
              placeholder="(必填项)"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="图纸编号" required>
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.drawingCode"
              placeholder="(必填项)"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="安全等级" required>
            <el-select
              v-model="devicedto.pipeAdd.pipe.safeGrade"
              placeholder="请选择(单选)"
              width="100%"
            >
              <el-option
                v-for="yali in dictStore().security_level().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段长度" required>
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.pipeLength"
              placeholder="(必填项)"
              clearable
            >
              <template #append>m</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公顷外径" required>
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.nominalOd"
              placeholder="(必填项)"
              clearable
            >
              <template #append>mm</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公顷直径" required>
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.nominalDiameter"
              placeholder="(必填项)"
              clearable
            >
              <template #append>mm</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管道壁厚" required>
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.pipeThickness"
              placeholder="(必填项)"
              clearable
            >
              <template #append>mm</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="壁厚系列" required>
            <el-select
              v-model="devicedto.pipeAdd.pipe.thicknessSeries"
              placeholder="请选择(单选)"
              clearable
            >
              <el-option
                v-for="yali in dictStore().wall_thickness_series().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="管段材料">
            <el-select
              v-model="devicedto.pipeAdd.pipe.pipeMaterial"
              placeholder="请选择(单选)"
              clearable
            >
              <el-option
                v-for="yali in dictStore().pipe_segment_material().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="起始位置">
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              v-model="devicedto.pipeAdd.pipe.startPosition"
              placeholder="请输入"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束位置">
            <el-input
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              v-model="devicedto.pipeAdd.pipe.endPosition"
              clearable
            />
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>
    <FormCard title="维护信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="铺设方式">
            <el-select
              clearable
              v-model="devicedto.pipeAdd.pipeMaintenance.layingMethod"
              placeholder="请选择(单选)"
            >
              <el-option
                v-for="yali in dictStore().laying_method().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工作年限">
            <el-input
              v-model="devicedto.pipeAdd.pipeMaintenance.workingYears"
              oninput="value=value.replace(/[^\d]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
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
              v-model="devicedto.pipeAdd.pipeMaintenance.useTime"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="使用年限">
            <el-input
              v-model="devicedto.pipeAdd.pipeMaintenance.usageDuration"
              oninput="value=value.replace(/[^\d]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
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
              v-model="devicedto.pipeAdd.pipeMaintenance.checkPeriod"
              oninput="value=value.replace(/[^\d]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
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
              v-model="devicedto.pipeAdd.pipeMaintenance.qcDate"
              type="date"
              label="请选择日期"
              placeholder="请选择日期"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>

    <FormCard title="设计信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="设计压力">
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              v-model="devicedto.pipeAdd.pipeDevise.devisePressure"
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              clearable
            >
              <template #append>MPa</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设计流量">
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              v-model="devicedto.pipeAdd.pipeDevise.deviseFlux"
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              clearable
            >
              <template #append>t/h</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设计温度">
            <el-input
              oninput="value=value.replace(/[^0-9.]/g,'')"
              v-model="devicedto.pipeAdd.pipeDevise.deviseTemperature"
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              clearable
            >
              <template #append>℃</template>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="设计寿命">
            <el-input
              v-model="devicedto.pipeAdd.pipeDevise.deviseLife"
              oninput="value=value.replace(/[^\d]/g,'')"
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              clearable
            >
              <template #append>年</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>

    <FormCard title="保温层信息">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="保温层材料" required>
            <el-select
              v-model="devicedto.pipeAdd.pipeInsulation.insulationMaterial"
              placeholder="请选择(单选)"
              clearable
            >
              <el-option
                v-for="yali in dictStore().pipeline_Insulation().all"
                :label="yali.dictLabel"
                :value="yali.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="保温工艺" required>
            <el-input
              v-model="devicedto.pipeAdd.pipeInsulation.insulation"
              minlength="3"
              maxlength="10"
              show-word-limit
              placeholder="请输入"
              clearable
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="保温层厚度" required>
            <el-input
              v-model="
                devicedto.pipeAdd.pipeInsulation.insulationMaterialThickness
              "
              minlength="3"
              maxlength="10"
              oninput="value=value.replace(/[^0-9.]/g,'')"
              show-word-limit
              placeholder="请输入"
              clearable
            >
              <template #append>mm</template>
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="使用年限" required>
            <el-input
              v-model="devicedto.pipeAdd.pipeInsulation.ageLimit"
              minlength="3"
              maxlength="10"
              show-word-limit
              onkeyup="value=value.replace(/[^\d]/g,'')"
              placeholder="请输入"
              clearable
            >
              <template #append>年</template>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </FormCard>
  </el-form>

  <FormCard title="监测指标报警设置">
    <ParametersettingsManage
      :deviceAlarms="devicedto.deviceAlarmList"
      :dictData="dictStore().pipe_index().all"
    />
  </FormCard>
  <Footer>
    <el-button size="large" type="primary" @click="submitForm()">
      确定
    </el-button>
  </Footer>
</template>

<style scoped></style>

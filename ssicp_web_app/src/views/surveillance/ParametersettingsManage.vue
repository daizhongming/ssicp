<script setup>
  import { reactive, ref, onMounted, watch, computed } from 'vue';
  import { dictStore } from '@/stores/dict';
  const emits = defineEmits(['addIndicator']);
  const props = defineProps(['devicetype', 'deviceAlarms', 'dictData']);
  const devicetype = ref();
  const sensorAlarms = ref([]);
  const notDict = computed(() => {
    return sensorAlarms.value.map((item) => item.index);
  });
  onMounted(async () => {
    if (props.deviceAlarms) {
      sensorAlarms.value = props.deviceAlarms;
    }
    devicetype.value = props.devicetype;
  });

  async function deleteRow(row) {
    const index = sensorAlarms.value.indexOf(row);
    if (index > -1) {
      sensorAlarms.value.splice(index, 1);
    }
  }
  async function addRow() {
    sensorAlarms.value.push({
      id: '',
      deviceId: '',
      index: '',
      unit: '',
      lowerAlarm: '',
      seniorAlarm: '',
      seniorSeniorAlarm: '',
      state: 1,
      createTime: '',
      updateTime: '',
      createdById: '',
      updatedById: '',
      deleteFlag: '',
      comments: '',
    });
  }

  watch(
    () => props.devicetype,
    (newValue, oldValue) => {
      devicetype.value = { ...newValue };
    }
  );

  watch(
    () => props.deviceAlarms,
    (newValue, oldValue) => {
      sensorAlarms.value = newValue;
    }
  );

  watch(
    () => props.devicetype,
    (newValue, oldValue) => {
      devicetype.value = newValue;
    }
  );

  async function handlePipeCodeChange(value, index) {
    let data = props.dictData.find((item) => item.dictValue == value);
    if (data) {
      sensorAlarms.value[index].unit = data.expand1;
    } else {
      sensorAlarms.value[index].unit = '未定义';
    }
  }
</script>

<template>
  <el-table
    :data="sensorAlarms"
    height="20vh"
    class="el-table_withoutBottomBorder"
  >
    <template #empty>
      <el-button icon="Plus" @click="addRow()" />
    </template>
    <el-table-column type="index" label="序号" width="50px" align="center" />

    <el-table-column prop="index" label="指标名称" align="center">
      <template #default="scope">
        <el-select
          clearable
          v-model="scope.row.index"
          placeholder="请选择(单选)"
          @change="(parameter) => handlePipeCodeChange(parameter, scope.$index)"
        >
          <el-option
            v-for="yali in props.dictData"
            :key="yali.dictValue"
            :label="yali.dictLabel"
            :value="yali.dictValue"
            :disabled="notDict.map((item) => item).includes(yali.dictValue)"
          ></el-option>
        </el-select>
      </template>
    </el-table-column>

    <!-- <el-table-column prop="unit" label="单位">
      <template #default="scope">
        <el-input v-model="scope.row.unit" readonly="readonly"></el-input>
      </template>
    </el-table-column> -->

    <el-table-column prop="lowerAlarm" label="低报" align="center">
      <template #default="scope">
        <el-input v-model="scope.row.lowerAlarm" placeholder="" clearable>
          <template #append>{{ scope.row.unit }}</template>
        </el-input>
      </template>
    </el-table-column>

    <el-table-column prop="seniorAlarm" label="高报" align="center">
      <template #default="scope">
        <el-input v-model="scope.row.seniorAlarm" placeholder="" clearable>
          <template #append>{{ scope.row.unit }}</template>
        </el-input>
      </template>
    </el-table-column>

    <el-table-column prop="seniorSeniorAlarm" label="高高报" align="center">
      <template #default="scope">
        <el-input
          v-model="scope.row.seniorSeniorAlarm"
          placeholder=""
          clearable
        >
          <template #append>{{ scope.row.unit }}</template>
        </el-input>
      </template>
    </el-table-column>

    <el-table-column prop="state" label="启停状态" width="80px" align="center">
      <template #default="scope">
        <el-switch
          :active-value="1"
          :inactive-value="0"
          active-text="启用"
          inactive-text="停用"
          inline-prompt
          v-model="scope.row.state"
        />
      </template>
    </el-table-column>

    <el-table-column prop="" label="操作" header-align="center" width="140px">
      <template #default="{ row, $index }">
        <el-button icon="Delete" type="danger" @click="deleteRow(row)" text>
          删除
        </el-button>
        <el-button
          icon="Plus"
          v-if="$index === sensorAlarms.length - 1"
          text
          type="primary"
          @click="addRow()"
        >
          添加
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<style lang="scss">
  .my-custom-table .el-table__body-wrapper::after {
    display: none;
  }
</style>
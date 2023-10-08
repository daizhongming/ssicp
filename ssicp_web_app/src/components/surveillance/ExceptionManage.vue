<script setup>
import { onMounted, ref, watch } from 'vue';
import { ElMessage } from "element-plus";
import {
  updateState,
} from "@/api/surveillance/InspectionException.js";
import { dictStore } from '@/stores/dict';
const { alarm_levels } = dictStore();

const props = defineProps(["exception","lookorupdate"])
const emits = defineEmits("save")

const enums = ref({
  disposeState:[
    {
      dictValue:1,
      dictLabel:"完成处理"
    },
    {
      dictValue:2,
      dictLabel:"忽略处理"
    }
  ]
})

const exception = ref({})
const lookorupdate = ref('')

onMounted( () => {
  exception.value = {...props.exception}
  exception.value.disposeState = 1
  lookorupdate.value = props.lookorupdate
  console.log("异常渲染",lookorupdate.value);
})

watch(
  () => props.exception,
  (newValue,oldValue) => {
    exception.value = {...newValue}
    exception.value.disposeState = 1
  }
)

watch(
  () => props.lookorupdate,
  (newValue,oldValue) => {
    lookorupdate.value = newValue
    console.log("动了动了",lookorupdate.value);
  }
)

async function okok(){
  if (!lookorupdate) {
    emits("save");
  }
  else{
    await updateState(exception.value)
    emits("save");
      ElMessage({
        message: "保存成功",
        type: "success",
      });
      visible.value = false;
  }
}

</script>

<template>
  <FormCard title="巡检计划基本信息">
    <el-form label-width="80px">
      <el-row :gutter="16">
        <el-col :span="12">
          <el-form-item label="计划名称">
            <el-input :value="exception.planName" disabled /></el-form-item
        ></el-col>

        <el-col :span="12">
          <el-form-item label="管线名称">
            <el-input :value="exception.pipelineName" disabled /></el-form-item
        ></el-col>

        <el-col :span="12">
          <el-form-item label="异常类型">
            <el-input :value="exception.inspectionTypes" disabled /></el-form-item
        ></el-col>

        <el-col :span="12">
          <el-form-item label="异常时间">
            <el-input :value="exception.createTime" disabled /></el-form-item
        ></el-col>

        <el-col :span="12">
          <el-form-item label="异常位置">
            <el-input :value="exception.abnormalLocation" disabled /></el-form-item
        ></el-col>

        <el-col :span="12">
          <el-form-item label="异常次数">
            <el-input :value="exception.abnormalNumber" disabled /></el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="处理方式">
            <el-select style="width: 15rem" v-model="exception.disposeState" :disabled="lookorupdate"   placeholder="请选择(单选)">
                <el-option v-for="item in enums.disposeState" :key="item.dictValue" :label="item.dictLabel"
                  :value="item.dictValue" />
              </el-select>
            </el-form-item>
        </el-col>

        <el-col :span="12">

        </el-col>

        <el-col :span="12">
            <el-form-item label="异常原因" required v-if="exception.disposeState==1">
            <el-input :disabled="lookorupdate" type="textarea" v-model="exception.cause" /></el-form-item>

            <el-form-item label="忽略原因" required v-if="exception.disposeState==2" >
            <el-input type="textarea" v-model="exception.cause" /></el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="处理简述" required v-if="exception.disposeState==1" >
            <el-input :disabled="lookorupdate" type="textarea" v-model="exception.comments" /></el-form-item>
        </el-col>

      </el-row>
    </el-form>
  </FormCard>

  <FormCard title="计划巡检内容">
    <el-table :data="exception.deviceAbnormalLogVO" :header-cell-class-name="cellClass" class="table">
        <el-table-column prop="deviceName" label="巡检对象" align="center" />
        <el-table-column type="index" label="序号" align="center" />
        <el-table-column prop="index" label="异常指标" align="center" />
        <el-table-column prop="abnormalType" label="异常类型" align="center" />
        <el-table-column prop="indexValue" label="异常值" align="center" />
        <el-table-column prop="alarmLevel" label="报警级别" align="center" >
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
        <el-table-column prop="correction" :disabled="lookorupdate" label="处理结果" align="center" >
          <template #default="scope">
            <el-input v-if="!lookorupdate" v-model="scope.row.correction"></el-input>
          </template>
        </el-table-column>
        <el-table-column prop="comments" label="巡检备注" align="center" >
          <template #default="scope">
            <el-input v-if="!lookorupdate" v-model="scope.row.comments"></el-input>
          </template>
        </el-table-column>
    </el-table>
  </FormCard>
  <el-button @click="okok()">确认</el-button>
</template>

<style scoped lang="scss"></style>
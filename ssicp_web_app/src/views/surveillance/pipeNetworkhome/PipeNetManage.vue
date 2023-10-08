<script setup>
  import { dictStore } from '@/stores/dict';
  const { pipeline, enums } = defineProps(['pipeline']);
  import Selecter from '../../common/PressureRatingSelecter.vue';
</script>

<template>
  <el-form label-width="80px">
    <el-row :gutter="16">
      <el-col :span="12">
        <el-form-item label="管线名称" required>
          <el-input
            minlength="3"
            maxlength="10"
            show-word-limit
            v-model="pipeline.pipelineName"
            placeholder="(必填项)"
            clearable
          />
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="压力等级" required>
          <Selecter v-model:pressureRating="pipeline.pressureRating" />
          <!--<el-select
            v-model="pipeline.pressureRating"
            clearable
            placeholder="请选择(单选)"
          >
            <el-option
              v-for="yali in dictStore().dictData('pipeline_pressure').all"
              :label="yali.dictLabel"
              :value="yali.dictValue"
            />
          </el-select> -->
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="所属区域" required>
          <el-select
            v-model="pipeline.region"
            clearable
            placeholder="请选择(单选)"
          >
            <el-option
              v-for="yali in dictStore().dictData('regional_division').all"
              :label="yali.dictLabel"
              :value="yali.dictValue"
            />
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="管线类型" required>
          <el-select
            v-model="pipeline.pipelineType"
            clearable
            placeholder="请选择(单选)"
          >
            <el-option
              v-for="yali in dictStore().dictData('pipeline_type').all"
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
            v-model="pipeline.startPosition"
            placeholder="(必填项)"
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
            v-model="pipeline.endPosition"
            placeholder="(必填项)"
            clearable
          />
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<style scoped lang="scss"></style>

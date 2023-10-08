<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { add as addJob, update as updateJob } from '@/api/job.js';
  import { ElMessage } from 'element-plus';

  const props = defineProps(['oldJob']);
  const job = ref({});
  function initJob() {
    job.value = { ...props.oldJob };
  }
  onMounted(initJob);
  watch(() => props.oldJob, initJob);

  const emits = defineEmits(['save']);

  async function save() {
    if (props.oldJob.jobName) {
      await updateJob(job.value);
    } else {
      await addJob(job.value);
    }
    ElMessage({
      message: '操作成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <FormCard>
    <el-form label-width="100px">
      <el-form-item label="任务id" required>
        <el-input v-model="job.jobName" :disabled="props.oldJob.jobName" />
      </el-form-item>
      <el-form-item label="任务组id" required>
        <el-input v-model="job.jobGroupName" :disabled="props.oldJob.jobName" />
      </el-form-item>
      <el-form-item label="实现类" required>
        <el-input v-model="job.jobClass" placeholder="com.xxx" />
      </el-form-item>
      <el-form-item label="cron表达式">
        <el-input
          v-model="job.cronExpressions"
          placeholder="0 0 0 * * ?;0 0 0 * * ?;0 0 0 * * ?"
        />
      </el-form-item>
      <el-form-item label="时间间隔">
        <el-input
          v-model="job.timeIntervalString"
          placeholder="1;DAY 可以选择MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR"
        />
      </el-form-item>
      <el-form-item label="任务描述">
        <el-input v-model="job.description" />
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="job.startTime"
          value-format="YYYY-MM-DD"
          type="date"
        />
      </el-form-item>
      <el-form-item label="是否开启">
        <el-switch v-model="job.status" :active-value="1" :inactive-value="0" />
      </el-form-item>
      <el-form-item label="附加数据">
        <el-input v-model="job.jobDataMap" type="textarea" placeholder="" />
      </el-form-item>
    </el-form>
  </FormCard>
  <Footer>
    <el-button size="large" @click="save">保存</el-button>
  </Footer>
</template>

<style scoped></style>

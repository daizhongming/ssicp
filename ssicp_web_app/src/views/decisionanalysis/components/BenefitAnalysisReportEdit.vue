<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { ElMessage } from 'element-plus';
  import {
    add as addReport,
    update as updateReport,
  } from '@/api/benefitAnalysisReport.js';
  import FileUpload from '@/components/upload/FileUpload.vue';

  const props = defineProps(['oldReport']);

  const report = ref({});
  const emits = defineEmits(['save']);
  function initReport() {
    report.value = { ...props.oldReport };
  }
  onMounted(initReport);
  watch(() => props.oldReport, initReport);

  async function add() {
    await addReport(report.value);
    ElMessage({
      message: '添加成功',
      type: 'success',
    });
    emits('save');
  }
  async function update() {
    await updateReport(report.value);
    ElMessage({
      message: '修改成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <div>
    <el-form :model="report" label-width="60px">
      <el-form-item label="名称" required>
        <el-input v-model="report.name" />
      </el-form-item>
      <el-form-item label="文件" required>
        <FileUpload v-model="report.fileCode" />
      </el-form-item>
    </el-form>
  </div>
  <Footer>
    <el-button size="large" @click="update" v-if="oldReport.id">修改</el-button>
    <el-button size="large" @click="add" v-else>新增</el-button>
  </Footer>
</template>

<style scoped></style>

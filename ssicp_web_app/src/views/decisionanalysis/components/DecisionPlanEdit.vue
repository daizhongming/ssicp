<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { ElMessage } from 'element-plus';
  import { add as addPlan, update as updatePlan } from '@/api/decisionPlan.js';
  import FileUpload from '@/components/upload/FileUpload.vue';

  const props = defineProps(['oldPlan']);

  const plan = ref({});
  const emits = defineEmits(['save']);

  function initPlan() {
    plan.value = { ...props.oldPlan };
  }
  onMounted(initPlan);
  watch(() => props.oldPlan, initPlan);

  async function add() {
    await addPlan(plan.value);
    ElMessage({
      message: '添加成功',
      type: 'success',
    });
    emits('save');
  }
  async function update() {
    await updatePlan(plan.value);
    ElMessage({
      message: '修改成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <div class="planedit">
    <el-form :model="plan" label-width="60px">
      <el-form-item label="名称" required>
        <el-input v-model="plan.name" />
      </el-form-item>
      <el-form-item label="文件" required>
        <FileUpload v-model="plan.fileCode" />
      </el-form-item>
    </el-form>
  </div>
  <Footer>
    <el-button size="large" @click="update" v-if="oldPlan.id">修改</el-button>
    <el-button size="large" @click="add" v-else>新增</el-button>
  </Footer>
</template>

<style scoped></style>

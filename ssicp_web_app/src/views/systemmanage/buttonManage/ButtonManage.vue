<script setup>
  import { add, update } from '@/api/button.js';
  import { watch, ref, onMounted } from 'vue';
  import { ElMessage } from 'element-plus';
  import { assign } from 'lodash';
  const props = defineProps(['data']);
  const emits = defineEmits(['save']);

  const button = ref({});
  function init() {
    button.value = assign({}, props.data);
  }
  watch(() => props.data, init);
  onMounted(init);

  async function okok() {
    if (!props.data.id) {
      await add(button.value);
    } else {
      await update(button.value);
    }
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <el-form-item label="按钮类型" required>
    <el-input v-model="button.type" placeholder="按钮类型" />
  </el-form-item>
  <el-form-item label="按钮描述" required>
    <el-input v-model="button.description" placeholder="按钮描述" />
  </el-form-item>
  <el-form-item label="按钮编码" required>
    <el-input
      v-model="button.id"
      :disabled="props.data.id ? true : false"
      placeholder="按钮编码"
    />
  </el-form-item>
  <Footer>
    <el-button size="large" @click="okok">保存</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { ElMessage } from 'element-plus';
  import { add as addMenu, update as updateMenu } from '@/api/menu.js';

  const props = defineProps(['oldMenu']);

  const menu = ref({});
  const emits = defineEmits(['save']);

  onMounted(() => {
    menu.value = { ...props.oldMenu };
  });
  watch(
    () => props.oldMenu,
    (newValue, oldValue) => {
      menu.value = { ...newValue };
    }
  );

  async function add() {
    await addMenu(menu.value);
    ElMessage({
      message: '添加成功',
      type: 'success',
    });
    emits('save');
  }
  async function update() {
    await updateMenu(menu.value);
    ElMessage({
      message: '修改成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <div class="menuedit">
    <el-form :model="menu" label-width="80px">
      <el-form-item label="标题" required>
        <el-input v-model="menu.title" />
      </el-form-item>
      <el-form-item label="编码" required v-if="!oldMenu.code">
        <el-input v-model="menu.code" />
      </el-form-item>

      <el-form-item label="排序" required>
        <el-input-number v-model="menu.sort" :min="1" />
      </el-form-item>
    </el-form>
    <Footer>
      <el-button size="large" @click="update" v-if="oldMenu.code">
        修改
      </el-button>
      <el-button size="large" @click="add" v-else>新增</el-button>
    </Footer>
  </div>
</template>

<style scoped></style>

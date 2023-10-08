<script setup>
  import { ref, onMounted, watch, reactive } from 'vue';
  import { ElMessage } from 'element-plus';
  import { findButtons, addButton, deleteButton } from '@/api/menu.js';
  const props = defineProps(['menuCode']);
  const button = reactive({
    id: '',
    description: '',
  });
  const buttons = ref([{}, {}]);

  function initButton() {
    button.id = '';
    button.description = '';
  }
  async function search() {
    const response = await findButtons(props.menuCode);
    buttons.value = response.data;
  }

  onMounted(() => {
    initButton();
    search();
  });
  watch(
    () => props.menuCode,
    () => {
      initButton();
      search();
    }
  );

  async function add() {
    await addButton(Object.assign({ menuCode: props.menuCode }, button));
    search();
    ElMessage({
      message: '添加成功',
      type: 'success',
    });
    initButton();
  }
  async function deleteCommand(row) {
    await deleteButton(row);
    search();
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
  }
</script>

<template>
  <el-form :model="button" inline>
    <el-form-item>
      <el-input v-model="button.id" placeholder="按钮编码" />
    </el-form-item>
    <el-form-item>
      <el-input v-model="button.description" placeholder="按钮描述" />
    </el-form-item>
    <el-form-item>
      <el-button @click="add">保存</el-button>
    </el-form-item>
  </el-form>
  <el-table :data="buttons" border height="30vh">
    <el-table-column type="index" label="序号" align="center" />
    <el-table-column prop="id" label="编码" align="left" />
    <el-table-column prop="description" label="描述信息" align="left" />
    <el-table-column label="操作" align="left" width="300">
      <template #default="scope">
        <div>
          <el-button
            icon="Delete"
            type="danger"
            v-confirm="() => deleteCommand(scope.row)"
            text
          >
            删除
          </el-button>
        </div>
      </template>
    </el-table-column>
  </el-table>
</template>

<style scoped></style>

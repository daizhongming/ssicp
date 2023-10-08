<script setup>
  import { ref, onMounted, watch, reactive, computed } from 'vue';
  import { deleteById } from '@/api/button.js';
  import { ElMessage } from 'element-plus';
  import buttonManage from './ButtonManage.vue';
  import _ from 'lodash';
  import { useButtonStore } from '@/stores/button';
  const buttonStore = useButtonStore();
  const params = reactive({
    type: '',
    description: '',
  });
  const keyword = ref('');
  const buttons = ref([]);
  async function search() {
    buttonStore.queryAllButtons();
  }
  onMounted(doSearch);

  function doSearch() {
    let result = buttonStore.buttonAllList
      .map((button) => Object.assign({}, button))
      .filter((button) =>
        params.type ? button.type.includes(params.type) : true
      )
      .filter((button) =>
        params.description
          ? button.description.includes(params.description)
          : true
      );
    setSpanMethodData(result);
    buttons.value = result;
  }
  function setSpanMethodData(list) {
    const typeCountMap = _.countBy(list, 'type');
    list.forEach((row) => {
      const count = typeCountMap[row.type];
      if (count) {
        row.rowspan = count;
        row.colspan = 1;
        delete typeCountMap[row.type];
      } else {
        row.rowspan = 0;
        row.colspan = 0;
      }
    });
  }
  async function deleteCommand(id) {
    await deleteById(id);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }
  const editButton = ref({});
  const dialogVisible = ref(false);
  //添加框
  async function add() {
    editButton.value = {};
    dialogVisible.value = !dialogVisible.value;
  }

  //修改框
  async function openUpdate(row) {
    editButton.value = { ...row };
    dialogVisible.value = !dialogVisible.value;
  }

  // 合并type列
  function objectSpanMethod({
    row: { rowspan, colspan },
    column,
    rowIndex,
    columnIndex,
  }) {
    if (columnIndex === 0) {
      return {
        rowspan,
        colspan,
      };
    }
  }
</script>

<template>
  <Container>
    <template #form>
      <el-form :inline="true" @submit.prevent>
        <el-form-item label="类型">
          <el-input v-model="params.type" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="描述信息">
          <el-input v-model="params.description" placeholder="请输入描述信息" />
        </el-form-item>
        <el-form-item>
          <el-button @click="doSearch">搜索</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="add">添加按钮</el-button>
    </template>
    <template #table>
      <el-table :data="buttons" border :span-method="objectSpanMethod">
        <el-table-column prop="type" label="类型" align="center" />
        <el-table-column prop="description" label="描述信息" align="center" />
        <el-table-column prop="id" label="编码" align="center" />
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <div>
              <el-button
                v-permission="'menu.update'"
                icon="Edit"
                type="primary"
                text
                @click="openUpdate(scope.row)"
              >
                修改
              </el-button>
              <el-button
                icon="Delete"
                type="danger"
                v-confirm="() => deleteCommand(scope.row.id)"
                text
              >
                删除
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <el-dialog v-model="dialogVisible" width="30%" title="按钮编辑"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <buttonManage
      :data="editButton"
      @save="search(), (dialogVisible = !dialogVisible)"
    />
  </el-dialog>
</template>

<style lang="scss" scoped></style>

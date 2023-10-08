<script setup name="menuManage">
  import { ref, onMounted } from 'vue';
  import { deleteById as deleteMenu } from '@/api/menu.js';
  import { ElMessage } from 'element-plus';
  import MenuEdit from './components/MenuEdit.vue';
  import { useMenuStore } from '@/stores/menu.js';
  const menuStore = useMenuStore();
  import { useUserStore } from '@/stores/user.js';
  const { getUserData } = useUserStore();

  async function search() {
    menuStore.queryAllMenus();
    getUserData();
  }

  const menu = ref({});
  const dialogVisible = ref(false);

  function addMenu(row) {
    menu.value = {
      level: row ? row.level + 1 : 1,
      sort: 0,
      parentCode: row ? row.code : null,
    };
    dialogVisible.value = true;
  }

  function openUpdate(row) {
    menu.value = row;
    dialogVisible.value = true;
  }
  async function deleteCommand(row) {
    const response = await deleteMenu(row.code);
    console.log(response);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }
</script>

<template>
  <Container>
    <template #commands>
      <el-button @click="addMenu(null)" v-permission="'menu.add'">
        添加一级菜单
      </el-button>
    </template>
    <template #table>
      <el-table :data="menuStore.menusTree" row-key="code" border>
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="code" label="编码/路由" align="left" />
        <el-table-column prop="sort" label="排序" align="center" width="80" />

        <el-table-column label="操作" align="left" width="300">
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
                v-permission="'menu.delete'"
                icon="Delete"
                type="danger"
                v-confirm="() => deleteCommand(scope.row)"
                text
              >
                删除
              </el-button>

              <el-button
                v-permission="'menu.add'"
                text
                icon="Edit"
                type="primary"
                v-if="scope.row.level !== 3"
                @click="addMenu(scope.row)"
              >
                {{ scope.row.level === 1 ? '添加二级菜单' : '添加三级菜单' }}
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>

  <!-- 修改弹出框 -->
  <el-dialog
    v-model="dialogVisible"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    :title="
      (menu.code ? '修改' : '添加') +
      ['一', '二', '三'][menu.level - 1] +
      '级菜单'
    "
    width="30%"
  >
    <MenuEdit
      :oldMenu="menu"
      @save="
        search();
        dialogVisible = false;
      "
    />
  </el-dialog>
</template>

<style scoped lang="scss"></style>

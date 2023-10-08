<script setup name="UserCenter">
  import { reactive, ref, onMounted } from 'vue';
  import { pageList, del as deleteUser } from '@/api/role.js';
  import { addRolePrivileges, findRolePrivileges } from '@/api/privilege.js';
  import PrivilegeEdit from '@/views/systemmanage/components/PrivilegeEdit.vue';
  import RoleManage from './components/RoleManage.vue';
  import { ElMessage } from 'element-plus';
  import { datetime as date } from '@/utils/formatter.js';

  const users = ref([]);

  const params = reactive({
    keyword: '',
    page: 1,
    size: 10,
    total: 0,
  });

  const small = ref(false);

  async function search() {
    let response = await pageList(params);
    users.value = response.data.data;
    params.total = response.data.total;
  }
  onMounted(() => {
    search();
  });

  function onSubmit() {
    console.log('查询');
    search();
  }

  const dialogVisible = ref(false);
  const rolePrivileges = ref({
    userName: null,
    menuCodes: [],
    interfaceIds: [],
  });
  async function openPrivilege(row) {
    roleEdit.value = { ...row };
    let response = await findRolePrivileges(row.name);
    rolePrivileges.value = response.data;
    dialogVisible.value = true;
  }
  async function savePrivileges({ menuCodes, buttonIds }) {
    let response = await addRolePrivileges({
      roleName: roleEdit.value.name,
      menuCodes,
      buttonIds,
    });
    console.log(response);
    if (response.status === 200) {
      ElMessage({
        message: '保存成功',
        type: 'success',
      });
      dialogVisible.value = false;
    }
  }

  // 用户管理
  const roleDialogVisible = ref(false);
  const roleEdit = ref({});
  function addRole() {
    roleEdit.value = {};
    roleDialogVisible.value = true;
  }

  function openRoleUpdate(row) {
    roleEdit.value = { ...row };
    roleDialogVisible.value = true;
  }

  async function deleteCommand(userId) {
    const response = await deleteUser(userId);
    if (response.status === 200) {
      ElMessage({
        message: '删除成功',
        type: 'success',
      });
      search();
    }
  }
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form @submit.prevent :inline="true" :model="params" class="form">
        <el-form-item label="角色名称">
          <el-input v-model="params.keyword" placeholder="请输入角色名称" />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" @click="addRole" v-permission="'role.update'">
        添加角色
      </el-button>
    </template>
    <template #table>
      <el-table border :data="users">
        <el-table-column type="index" label="序号" align="center" width="80" />
        <el-table-column
          prop="name"
          label="角色编码"
          align="center"
          width="200"
        />
        <el-table-column prop="title" label="角色名称" align="center" />
        <el-table-column
          align="center"
          prop="createTime"
          label="创建时间"
          :formatter="date"
        />
        <el-table-column
          align="center"
          prop="updateTime"
          label="修改时间"
          :formatter="date"
        />
        <el-table-column label="操作" fixed="right" align="center" width="300">
          <template #default="scope">
            <div>
              <el-button
                text
                icon="Edit"
                type="primary"
                @click="openRoleUpdate(scope.row)"
                v-permission="'role.update'"
              >
                修改
              </el-button>

              <el-button
                v-if="scope.row.systemFlag === 0"
                icon="Delete"
                type="danger"
                v-confirm="() => deleteCommand(scope.row.name)"
                text
                v-permission="'role.delete'"
              >
                删除
              </el-button>

              <el-button
                v-if="scope.row.name !== 'system'"
                text
                icon="Edit"
                type="primary"
                @click="openPrivilege(scope.row)"
                v-permission="'role.update'"
              >
                权限
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <!-- 角色修改框 -->
  <el-dialog v-model="roleDialogVisible" title="角色管理" width="30%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <RoleManage
      :oldUser="roleEdit"
      @save="
        search();
        roleDialogVisible = false;
      "
    />
  </el-dialog>
  <!-- 权限框 -->
  <el-dialog v-model="dialogVisible" title="权限管理" width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
    <PrivilegeEdit :privileges="rolePrivileges" @save="savePrivileges" />
  </el-dialog>
</template>

<style scoped lang="scss">
  .userCenter {
    height: 100%;

    .commands {
      margin-bottom: 0.5rem;
    }

    .table {
      flex: 1 0 0;
      height: 0;
    }

    .pagination {
      margin-top: 1rem;
      justify-content: end;
    }
  }
</style>

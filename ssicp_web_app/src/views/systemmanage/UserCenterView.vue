<script setup name="UserCenter">
  import { reactive, ref, onMounted } from 'vue';

  import { search as searchUser, remove as deleteUser } from '@/api/user.js';

  import { addUserPrivileges, findUserPrivileges } from '@/api/privilege.js';
  import UserPrivilegeManage from './components/UserPrivilegeManage.vue';
  import UserManage from './components/UserManage.vue';
  import { ElMessage } from 'element-plus';

  const users = ref([]);

  const params = reactive({
    nickName: '',
    region: '',
    page: 1,
    size: 10,
    total: 0,
  });

  async function search() {
    let response = await searchUser(params);
    users.value = response.data.data;
    params.total = response.data.total;
  }
  onMounted(() => {
    search();
  });

  const dialogVisible = ref(false);
  const userPrivileges = ref({
    userName: null,
    menuCodes: [],
    interfaceIds: [],
  });
  async function openPrivilege(row) {
    userEdit.value = { ...row };
    let response = await findUserPrivileges(row.name);
    userPrivileges.value = response.data;
    dialogVisible.value = true;
  }
  async function savePrivileges({ menuCodes, buttonIds, roleName }) {
    await addUserPrivileges({
      userName: userEdit.value.name,
      menuCodes,
      buttonIds,
      roleName,
    });
    ElMessage({
      message: '保存成功',
      type: 'success',
    });
    search();
    dialogVisible.value = false;
  }

  // 用户管理
  const userDialogVisible = ref(false);
  const userEdit = ref({});
  function addUser() {
    userEdit.value = {};
    userDialogVisible.value = true;
  }
  function openUserUpdate(row) {
    userEdit.value = { ...row };
    userDialogVisible.value = true;
  }
  async function deleteCommand(userName) {
    await deleteUser(userName);
    ElMessage({
      message: '删除成功',
      type: 'success',
    });
    search();
  }
</script>

<template>
  <Container pagination :params="params" @search="search">
    <template #form>
      <el-form :inline="true" :model="params" @submit.prevent>
        <el-form-item label="昵称">
          <el-input v-model="params.nickName" placeholder="昵称" />
        </el-form-item>
        <el-form-item>
          <el-button icon="Search" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template #commands>
      <el-button icon="Plus" v-permission="'user.update'" @click="addUser">
        添加用户
      </el-button>
    </template>
    <template #table>
      <el-table border :data="users">
        <el-table-column type="index" label="序号" align="center" width="80" />
        <el-table-column prop="name" label="用户名" align="center" />
        <el-table-column prop="nickName" label="昵称" align="center" />
        <el-table-column prop="roleTitle" label="角色标题" align="center" />
        <el-table-column
          prop="createTime"
          label="创建时间"
          align="center"
          :formatter="$formatter.datetime"
        />
        <el-table-column
          align="center"
          prop="updateTime"
          label="修改时间"
          :formatter="$formatter.datetime"
        />
        <el-table-column label="操作" align="center" width="300">
          <template #default="scope">
            <div>
              <el-button
                text
                icon="Edit"
                type="primary"
                v-permission="'user.update'"
                @click="openUserUpdate(scope.row)"
              >
                修改
              </el-button>
              <el-button
                v-if="scope.row.systemFlag === 0"
                icon="Delete"
                type="danger"
                v-permission="'user.delete'"
                v-confirm="() => deleteCommand(scope.row.name)"
                text
              >
                删除
              </el-button>
              <el-button
                text
                icon="Edit"
                type="primary"
                v-permission="'user.permission'"
                @click="openPrivilege(scope.row)"
              >
                权限
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </template>
  </Container>
  <div class="userCenter">
    <!-- 用户管理框 -->
    <el-dialog v-model="userDialogVisible" title="用户管理" width="30%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
      <UserManage
        :oldUser="userEdit"
        @save="
          search();
          userDialogVisible = false;
        "
      />
    </el-dialog>
    <!-- 权限框 -->
    <el-dialog v-model="dialogVisible" title="用户权限配置" width="50%"
    :close-on-click-modal="false"
    :close-on-press-escape="false">
      <UserPrivilegeManage
        :userPrivileges="userPrivileges"
        :user="userEdit"
        @save="savePrivileges"
      ></UserPrivilegeManage>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss"></style>

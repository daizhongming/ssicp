<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { findAll as findAllRoles } from '@/api/role.js';
  import { findRolePrivileges } from '@/api/privilege.js';

  import PrivilegeEdit from './PrivilegeEdit.vue';

  const props = defineProps(['userPrivileges', 'user']);

  // 默认选中角色
  const checkRole = ref('');
  function initCheckRole() {
    checkRole.value = props.user.roleName;
  }
  onMounted(initCheckRole);
  watch(() => props.user, initCheckRole);

  // 初始化查询所有角色
  const roles = ref([]);
  async function searchRoles() {
    const response = await findAllRoles();
    roles.value = response.data;
  }
  onMounted(searchRoles);

  // 保存结果
  const emits = defineEmits(['save']);
  function save({ buttonIds, menuCodes }) {
    emits('save', { buttonIds, menuCodes, roleName: checkRole.value });
  }

  // 改变角色
  const checkPrivileges = ref({});
  function initCheckPrivileges() {
    checkPrivileges.value = props.userPrivileges;
  }
  onMounted(initCheckPrivileges);
  watch(() => props.userPrivileges, initCheckPrivileges);
  async function change(roleName) {
    const response = await findRolePrivileges(roleName);
    checkPrivileges.value = response.data;
  }
</script>

<template>
  <FormCard title="角色信息">
    <el-form label-width="150px">
      <el-form-item label="角色" class="userprivilegemanage">
        <el-radio-group
          v-model="checkRole"
          @change="change"
          :disabled="props.user.systemFlag === 1"
        >
          <el-radio
            :label="role.name"
            :key="role.name"
            v-for="role in roles"
            :disabled="role.name === 'system' && role.systemFlag === 1"
          >
            {{ role.title }}
          </el-radio>
        </el-radio-group>
      </el-form-item>
    </el-form>
  </FormCard>
  <FormCard title="权限信息">
    <PrivilegeEdit @save="save" :privileges="checkPrivileges" />
  </FormCard>
</template>

<style scoped lang="scss"></style>

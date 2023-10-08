<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { add as addUser, update as updateUser } from '@/api/user.js';
  import { ElMessage } from 'element-plus';

  const props = defineProps(['oldUser']);

  const user = ref({});
  onMounted(() => {
    user.value = { ...props.oldUser };
  });
  watch(
    () => props.oldUser,
    (newValue, oldValue) => {
      console.log(newValue);
      user.value = { ...newValue };
    }
  );

  const emits = defineEmits(['save']);
  async function add() {
    await addUser(user.value);
    ElMessage({
      message: '新增成功',
      type: 'success',
    });
    emits('save');
  }

  async function update() {
    await updateUser(user.value);
    ElMessage({
      message: '修改成功',
      type: 'success',
    });
    emits('save');
  }
</script>

<template>
  <div class="userManage">
    <el-form :model="user" label-width="8rem">
      <el-form-item label="用户名" required v-if="!oldUser.name">
        <el-input v-model="user.name" />
      </el-form-item>
      <el-form-item label="昵称" required>
        <el-input v-model="user.nickName" />
      </el-form-item>
    </el-form>
  </div>
  <Footer>
    <el-button size="large" @click="update" v-if="oldUser.name">修改</el-button>
    <el-button size="large" @click="add" v-else>新增</el-button>
  </Footer>
</template>

<style scoped></style>

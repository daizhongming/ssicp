<script setup>
  import { ref, onMounted, watch } from 'vue';
  import { changePassword } from '@/api/user.js';
  import { ElMessage } from 'element-plus';

  const props = defineProps(['userName']);

  const emits = defineEmits(['save']);

  const password = ref({
    oldpassword: '',
    newpasswprd: '',
    repeatpasswprd: '',
    userName: '',
  });

  async function okok() {
    emits('save');
    await changePassword(password.value);
    ElMessage({
      message: '密码修改成功',
      type: 'success',
    });
    chongzhi();
  }

  async function chongzhi() {
    password.value = {};
  }

  watch(
    () => props.userName,
    (newValue, oldValue) => {
      password.value.userName = newValue;
      console.log(password.value.userName);
    }
  );

  onMounted(() => {
    password.value.userName = props.userName;
    console.log(password.value.userName);
  });
</script>

<template>
  <el-form label-width="100px">
    <el-form-item label="旧密码" prop="oldpassword">
      <el-input
        v-model="password.oldpassword"
        placeholder="(必填项)"
        type="password"
        clearable
      />
    </el-form-item>
    <el-form-item label="新密码" prop="oldpassword">
      <el-input
        v-model="password.newpasswprd"
        placeholder="(必填项)"
        type="password"
        clearable
      />
    </el-form-item>
    <el-form-item label="重复新密码" prop="oldpassword">
      <el-input
        v-model="password.repeatpasswprd"
        placeholder="(必填项)"
        type="password"
        clearable
      />
    </el-form-item>
  </el-form>
  <Footer>
    <el-button size="large" @click="okok">确认</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

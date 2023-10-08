<script setup>
  import { reactive } from 'vue';
  import { useRouter } from 'vue-router';

  import image from '@/assets/images/logo.png';
  import { ElMessage } from 'element-plus';
  import { useUserStore } from '@/stores/user.js';
  const { login: loginReq } = useUserStore();

  const router = useRouter();
  const user = reactive({
    name: '',
    password: '',
  });
  async function login() {
    await loginReq(user);
    ElMessage({
      message: '登录成功',
      type: 'success',
    });
    if (router.currentRoute.value.query.redirect) {
      router.replace({ path: router.currentRoute.value.query.redirect });
    } else {
      router.replace({ name: 'index' });
    }
  }

  function anonymousLogin() {
    router.replace({ name: 'index' });
  }

  const titles = '蒸汽系统智能管控平台'.split('');
  console.log(titles);
</script>

<template>
  <div class="login">
    <el-form :model="user" class="form">
      <div class="logo">
        <input type="image" class="image" :src="image" alt="" />
        <span v-for="(title, index) in titles" :key="index">{{ title }}</span>
      </div>
      <el-input v-model="user.name" prefix-icon="User" class="user" />
      <el-input
        v-model="user.password"
        type="password"
        show-password
        prefix-icon="Lock"
        class="password"
      />
      <el-button class="button" type="primary" @click="login" plain>
        登录
      </el-button>
      <div class="text-right">
        <el-link @click="anonymousLogin">去首页</el-link>
      </div>
    </el-form>
  </div>
</template>

<style scoped lang="scss">
  .login {
    height: 100%;
    background-color: #0c2f59;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    align-items: center;
    .form {
      // border: 1px solid red;
      width: 30rem;
      .logo {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 2rem;
        font-family: 'YouSheBiaoTiHei';
        font-size: 2.5rem;
        color: #0980a5;
        .image {
          height: 2.5rem;
        }
      }
      .user {
        margin-bottom: 2rem;
      }
      .password {
        margin-bottom: 2rem;
      }
      .button {
        width: 100%;
        background-color: transparent;
        border: 1px solid #0980a5;
        color: white;
        background-image: none;
        height: 3rem;
        &:hover {
          background-color: rgba(255, 255, 255, 0.1);
          color: rgba($color: #0980a5, $alpha: 0.9);
        }
        &:deep() span {
          font-size: 1rem;
        }
      }
    }
  }
</style>

<script setup>
  import Header from './home/Header.vue';
  import Path from './home/Path.vue';
  import { useUserStore } from '@/stores/user.js';
  const { getUserData } = useUserStore();
  import { onMounted } from 'vue';

  onMounted(getUserData);
</script>

<template>
  <div class="home">
    <div class="header_wrap">
      <Header></Header>
    </div>
    <div class="router_wrap">
      <Path></Path>
    </div>
    <div class="content_wrap">
      <router-view v-slot="{ Component }">
        <component :is="Component" />
      </router-view>
    </div>
    <div class="footer">Copyright © 南京智联华科技有限公司所有</div>
    <div class="bgs">
      <div v-for="index in 9" :key="index"></div>
    </div>
    <div class="bg2"></div>
  </div>
</template>

<style scoped lang="scss">
  .home {
    display: flex;
    flex-direction: column;
    height: 100%;

    // background-image: url("@/assets/images/bg.jpg");
    // background-size: 100% 100%;
    position: relative;

    .bgs {
      position: absolute;
      top: 0;
      bottom: 0;
      right: 0;
      left: 0;

      z-index: -3;

      display: grid;
      grid-template-rows: 8rem 1fr 32rem;
      grid-template-columns: 90rem 1fr 21rem;

      @for $i from 1 through 9 {
        :nth-child(#{$i}) {
          // border: 1px solid red;
          background-size: 100% 100%;
          background-image: url('@/assets/images/bg/bg_0#{$i}.jpg');
        }
      }
    }

    .bg2 {
      position: absolute;
      top: 0;
      bottom: 0;
      right: 0;
      left: 0;
      z-index: -3;
      background-size: 100% 100%;
      background-image: url('@/assets/images/bg3.png');
      // filter: brightness(0.4);
    }

    .header_wrap {
      // border: 1px solid yellow;
      height: 6.5rem;
    }

    .router_wrap {
      height: 2rem;
      // border: 1px solid yellow;
      padding: 0 2rem;
    }

    .content_wrap {
      height: 0;
      flex: 1 0 0;
      padding: 0.5rem 2rem 0rem 2rem;
      box-sizing: border-box;
    }
    .footer {
      text-align: center;
      height: 2rem;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  }
</style>

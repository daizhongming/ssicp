<script setup>
  import { watch, ref, onMounted, computed } from 'vue';
  import { useRouter } from 'vue-router';
  import { useMenuStore } from '@/stores/menu.js';
  const menuStore = useMenuStore();
  import _ from 'lodash';
  const router = useRouter();

  const paths = computed(() => {
    let result = [];
    let matched;
    let matchedRouteList = [...router.currentRoute.value.matched].reverse();
    for (let i = 0; i < matchedRouteList.length; i++) {
      let route = matchedRouteList[i];
      if (menuStore.menusMap.has(route.name)) {
        matched = route;
        break;
      } else if (route.meta.title) {
        result.unshift({ name: route.meta.title });
      }
    }
    if (matched) {
      let temp = menuStore.menusMap.get(matched.name);

      while (temp) {
        result.unshift({ name: temp.title });
        temp = menuStore.menusMap.get(temp.parentCode);
      }
    }
    return result;
  });

  // const paths = ref([]);
  // function changeLevels() {
  //   const route = router.currentRoute.value;
  //   const pathsTemp = route.matched
  //     .map((e) => e.meta)
  //     .filter((e) => e)
  //     .map((e) => e.title)
  //     .filter((e) => e)
  //     .map((e) => {
  //       return { name: e };
  //     });
  //   paths.value = _.uniqBy(pathsTemp, (o) => o.name);
  // }

  const canBack = ref(false);
  function changeCanBack() {
    const route = router.currentRoute.value;
    canBack.value = route.meta && route.meta.back === true;
  }

  onMounted(async () => {
    // changeLevels();
    changeCanBack();
  });
  watch(
    () => router.currentRoute.value, // 监听路由对象的变化
    () => {
      // changeLevels();
      changeCanBack();
    }
  );

  function back() {
    router.back();
  }
  function home() {
    router.replace({ name: 'index' });
  }
</script>

<template>
  <div class="path">
    <span class="back" @click="back" v-if="canBack === true">
      <el-icon class="icon"><ArrowLeftBold /></el-icon>
      <span class="back_text">返回</span>
    </span>
    <span class="back" @click="home" v-else>
      <el-icon><HomeFilled /></el-icon>
      <span class="back_text">主页</span>
    </span>
    <span class="splitter"></span>
    <div v-for="(path, $index) in paths" :key="path" class="path_text">
      <img
        v-if="$index !== 0"
        src="@/assets/images/arrow.png"
        class="arrow img"
      />
      <span>{{ path.name }}</span>
    </div>
  </div>
</template>

<style scoped lang="scss">
  .path {
    display: flex;
    color: #05bcff;
    align-items: center;
    font-size: 1.2rem;
    height: 100%;
    font-family: 'YouSheBiaoTiHei';

    > .path_text:not(:last-child) > span {
      filter: brightness(70%);
    }
    > .path_text:not(:last-child) > img {
      filter: brightness(70%);
    }
    .back_text {
      color: white;
    }

    .splitter {
      margin: 0 2px;
      height: 0.8rem;
      width: 2px;
      margin: 0 0.5rem;
      background-color: white;
    }

    .img {
      height: 0.8rem;
      margin: 0 0.5rem;
    }

    .back {
      cursor: pointer;
      display: flex;
      align-items: center;
    }
  }
</style>

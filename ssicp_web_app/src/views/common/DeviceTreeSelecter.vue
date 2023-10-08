<script setup>
  import {
    ref,
    onMounted,
    toRaw,
    computed,
    reactive,
    watch,
    nextTick,
  } from 'vue';
  import _ from 'lodash';
  const emits = defineEmits(['select']);
  import { useDeviceStore } from '@/stores/device.js';
  const deviceStore = useDeviceStore();

  const menu = ref(null);

  function openDefault() {
    nextTick(() => {
      const pipeline = searchTree.value.find(
        (pipeline) => pipeline.children.length > 0
      );
      if (pipeline) {
        menu.value.open(pipeline.index);
        menu.value.open([pipeline.children[0].index]);
      }
    });
  }

  // 筛选部分
  const keyword = ref('');
  const searchTree = computed(() => {
    let result = _.cloneDeep(deviceStore.deviceTree);
    result = result.map((pipeline) => {
      pipeline.index = pipeline.id;
      pipeline.title = pipeline.name;
      pipeline.children = pipeline.children
        .map((type) => {
          type.index = pipeline.index + '_' + type.id;
          type.title = type.name;
          type.children = type.children
            .map((device) => {
              device.index = type.index + '_' + device.id;
              device.title = device.name;
              return device;
            })
            .filter((device) => device.title.includes(keyword.value));
          return type;
        })
        .filter((type) => type.children.length);
      return pipeline;
    });
    return result;
  });
  watch(() => searchTree.value, openDefault);
  onMounted(openDefault);

  function select(index, indexPath, item, routeResult) {
    const pipeline = searchTree.value.find(
      (pipeline) => pipeline.index === indexPath[0]
    );

    const type = pipeline.children.find((type) => type.index === indexPath[1]);

    const object = type.children.find(
      (object) => object.index === indexPath[2]
    );
    emits('select', { pipeline: pipeline, type: type, object: object });
  }
</script>

<template>
  <Card title="设备筛选">
    <div class="search">
      <el-input
        v-model="keyword"
        placeholder="搜索设备名称"
        suffix-icon="Search"
      ></el-input>
    </div>
    <el-scrollbar>
      <el-menu ref="menu" class="menu" @select="select">
        <el-sub-menu
          v-for="pipeline in searchTree"
          :index="pipeline.index"
          :key="pipeline.index"
          :disabled="pipeline.children.length === 0"
        >
          <template #title>
            <el-icon><Folder /></el-icon>
            <span>{{ pipeline.title }}</span>
            <span>（{{ pipeline.children.length }}）</span>
          </template>
          <el-sub-menu
            v-for="type1 in pipeline.children"
            :index="type1.index"
            :key="type1.index"
          >
            <template #title>
              <el-icon><Folder /></el-icon>
              <span>{{ type1.title }}</span>
              <span>（{{ type1.children.length }}）</span>
            </template>
            <el-menu-item
              :key="object.index"
              :index="object.index"
              v-for="object in type1.children"
            >
              <el-icon><Document /></el-icon>
              {{ object.title }}
            </el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </Card>
</template>

<style scoped lang="scss">
  .search {
    margin-bottom: 0.5rem;
  }
  :deep() .menu.el-menu {
    border-right: none;
    .el-menu-item,
    .el-sub-menu__title {
      border-bottom: 1px solid rgba(12, 65, 134, 1);
    }

    .el-sub-menu__title {
      border-width: 2px;
    }
  }

  .menu {
    .circle {
      height: 0.5rem;
      width: 0.5rem;
      border-radius: 50%;
      margin-right: 0.5rem;
      background-color: gray;
    }
  }
</style>

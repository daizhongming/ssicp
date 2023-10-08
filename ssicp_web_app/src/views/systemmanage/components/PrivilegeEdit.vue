<script setup>
  import { ref, onMounted, watch, nextTick, computed } from 'vue';

  import { useMenuStore } from '@/stores/menu.js';
  import TreeSelector from '@/views/common/TreeSelector.vue';
  import { useButtonStore } from '@/stores/button';
  import _ from 'lodash';
  const menuStore = useMenuStore();
  const buttonStore = useButtonStore();
  const activeName = ref('first');

  // 默认选中
  const checkedMenus = ref([]);
  const checkedButtons = ref([]);
  const props = defineProps(['privileges']);
  function initPrivileges() {
    checkedMenus.value = props.privileges.menuCodes;
    checkedButtons.value = props.privileges.buttonIds;
  }
  onMounted(initPrivileges);
  watch(() => props.privileges, initPrivileges);

  // 保存结果
  const emits = defineEmits(['save']);
  function save() {
    emits('save', getExists());
  }
  function getExists() {
    return {
      menuCodes: checkedMenus.value,
      buttonIds: checkedButtons.value,
    };
  }

  const buttonTypes = computed(() => {
    return _.groupBy(buttonStore.buttonAllList, 'type');
  });
</script>

<template>
  <el-tabs v-model="activeName">
    <el-tab-pane label="勾选菜单" name="first">
      <el-scrollbar height="600px">
        <TreeSelector
          nodeKey="code"
          v-model="checkedMenus"
          :treeData="menuStore.menusTree"
          :props="{ children: 'children', label: 'title' }"
        />
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane label="勾选按钮" name="second">
      <el-scrollbar height="600px">
        <el-checkbox-group v-model="checkedButtons">
          <el-form label-width="120px">
            <el-form-item
              :label="type"
              v-for="(buttons, type) in buttonTypes"
              :key="type"
            >
              <el-checkbox
                :label="button.id"
                v-for="button in buttons"
                :key="button.id"
              >
                {{ button.description }}
              </el-checkbox>
            </el-form-item>
          </el-form>
        </el-checkbox-group>
      </el-scrollbar>
    </el-tab-pane>
  </el-tabs>

  <Footer>
    <el-button size="large" @click="save">保存</el-button>
  </Footer>
</template>

<style scoped lang="scss"></style>

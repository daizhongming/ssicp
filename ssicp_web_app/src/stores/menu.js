import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { findAll } from '@/api/menu.js';

export const useMenuStore = defineStore('menu', () => {
  const menusList = ref([]);
  const menusMap = ref(new Map());
  const menusTree = ref([]);
  const myMenus = ref([]);
  function setMyMenus(list) {
    myMenus.value = list;
  }

  async function queryAllMenus() {
    const { data: menuAllList } = await findAll();
    // menusList
    menusList.value = [...menuAllList];

    // menusMap
    let map = new Map();
    menusList.value.forEach((menu) => {
      map.set(menu.code, menu);
    });
    menusMap.value = map;
    // menusTree
    const level1Menus = menusList.value.filter((menu) => menu.level === 1);
    const level2Menus = menusList.value.filter((menu) => menu.level === 2);
    const level3Menus = menusList.value.filter((menu) => menu.level === 3);

    level2Menus.forEach((level2Menu) => {
      const level1Menu = level1Menus.find(
        (menu) => menu.code === level2Menu.parentCode
      );
      if (level1Menu) {
        level1Menu.children = level1Menu.children || [];
        level1Menu.children.push(level2Menu);
      }
    });

    level3Menus.forEach((level3Menu) => {
      const level2Menu = level2Menus.find(
        (menu) => menu.code === level3Menu.parentCode
      );
      if (level2Menu) {
        level2Menu.children = level2Menu.children || [];
        level2Menu.children.push(level3Menu);
      }
    });
    menusTree.value = level1Menus;
  }

  const headMenuTree = computed(() => {
    const myMenuCodeSet = new Set(myMenus.value);
    const all = menusList.value.map(
      ({ title, code, level, sort, parentCode }) => {
        return { title, code, level, sort, parentCode };
      }
    );
    const level3Menus = all.filter((menu) => {
      if (menu.level === 3 && myMenuCodeSet.has(menu.code)) {
        myMenuCodeSet.add(menu.parentCode);
        return true;
      }
      return false;
    });
    const level2Menus = all.filter((menu) => {
      if (menu.level === 2 && myMenuCodeSet.has(menu.code)) {
        myMenuCodeSet.add(menu.parentCode);
        return true;
      }
      return false;
    });
    const level1Menus = all.filter((menu) => {
      if (menu.level === 1 && myMenuCodeSet.has(menu.code)) {
        return true;
      }
      return false;
    });
    level2Menus.forEach((level2Menu) => {
      const level1Menu = level1Menus.find(
        (menu) => menu.code === level2Menu.parentCode
      );
      if (level1Menu) {
        level1Menu.children = level1Menu.children || [];
        level1Menu.children.push(level2Menu);
      }
    });

    level3Menus.forEach((level3Menu) => {
      const level2Menu = level2Menus.find(
        (menu) => menu.code === level3Menu.parentCode
      );
      if (level2Menu) {
        level2Menu.children = level2Menu.children || [];
        level2Menu.children.push(level3Menu);
      }
    });
    return level1Menus;
  });

  return {
    menusMap,
    menusList,
    menusTree,
    queryAllMenus,
    myMenus,
    setMyMenus,
    headMenuTree,
  };
});

import { computed, ref } from 'vue';
import { defineStore } from 'pinia';
import { login as loginReq, getMessage } from '@/api/index.js';
import { useButtonStore } from '@/stores/button';
const { setMyButtons } = useButtonStore();
import { useMenuStore } from '@/stores/menu.js';
const { setMyMenus } = useMenuStore();

export const useUserStore = defineStore('user', () => {
  const user = ref(null);

  async function login(userData) {
    // 登录页登录后能设置token
    const { data } = await loginReq(userData);
    localStorage.setItem('token', data);
  }

  async function getUserData() {
    // home页首次进入会获取用户信息，当登出以后需要清空这些用户数据
    let {
      data: { userVo, menuCodeList, buttonIdList },
    } = await getMessage();
    setMyButtons(buttonIdList);
    setMyMenus(menuCodeList);
    user.value = userVo;
  }
  const isRoot = computed(() => {
    return user.value && user.value.root === true;
  });
  const isInspector = computed(() => {
    return user.value && user.value.inspector === true;
  });
  const usernameFor3D = computed(() => {
    return user.value ? user.value.name : 'anonymous';
  });

  async function logout() {
    localStorage.removeItem('token');
    getUserData();
    // 删除token，
    // 删除用户数据
    // 删除按钮，菜单
  }
  return {
    user,
    login,
    getUserData,
    logout,
    isRoot,
    isInspector,
    usernameFor3D,
  };
});

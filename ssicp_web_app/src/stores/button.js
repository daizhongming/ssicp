import { ref } from 'vue';
import { defineStore } from 'pinia';
import { findAll } from '@/api/button.js';

export const useButtonStore = defineStore('button', () => {
  const buttonAllList = ref([]);
  const buttonIdList = ref([]);
  const setMyButtons = function (list) {
    buttonIdList.value = list;
  };
  async function queryAllButtons() {
    const { data } = await findAll();
    buttonAllList.value = data;
  }
  return { buttonAllList, buttonIdList, queryAllButtons, setMyButtons };
});

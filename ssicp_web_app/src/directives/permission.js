/**
<el-button
            icon="Edit"
            type="primary"
            text
            v-permission="'alarm.handle'"
            @click="handle(scope.row)"
          >
            处理
          </el-button>
<el-button
            icon="Edit"
            type="primary"
            text
            v-permission="{code:'alarm.handle',disabled:XXX}"
            @click="handle(scope.row)"
          >
            处理
          </el-button>
 * 
 * 
 */

import { useButtonStore } from '@/stores/button';
import { watch } from 'vue';
export default function directivePermission(app) {
  function updateDisabled(el, binding) {
    const buttonId =
      typeof binding.value === 'string' ? binding.value : binding.value.code;
    const isDisabled1 =
      typeof binding.value === 'string' ? false : binding.value.disabled;
    if (isDisabled1) {
      el.disabled = true;
      el.classList.add('is-disabled');
      return;
    }
    const store = useButtonStore();
    const isInSystemButtonList = store.buttonAllList.some(
      (button) => button.id === buttonId
    );
    const isInMyButtonList = store.buttonIdList.includes(buttonId);
    if (isInSystemButtonList && !isInMyButtonList) {
      //在按钮集合里面，但是不在我的按钮里面的需要禁用
      el.disabled = true;
      el.classList.add('is-disabled');
    } else {
      el.disabled = false;
      el.classList.remove('is-disabled');
    }
  }
  app.directive('permission', {
    mounted: (el, binding) => {
      const store = useButtonStore();
      updateDisabled(el, binding);
      el._stopWatcher = watch(
        [() => store.buttonAllList, () => store.buttonIdList],
        () => {
          updateDisabled(el, binding);
        }
      );
    },
    updated: (el, binding) => {
      updateDisabled(el, binding);
    },
    unmounted(el) {
      if (el._stopWatcher) {
        el._stopWatcher();
        delete el._stopWatcher;
      }
    },
  });
}

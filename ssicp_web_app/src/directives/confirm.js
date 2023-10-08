import { ElMessageBox } from 'element-plus';
import { bind } from 'lodash';
export default function directiveConfirm(app) {
  const addClick = (el, binding) => {
    const clickHandler = () => {
      ElMessageBox.confirm('确认删除吗？', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(() => {
          binding.value();
        })
        .catch(() => {});
    };
    removeClick(el);
    el.addEventListener('click', clickHandler);
    el.__confirmClick__ = clickHandler;
  };
  const removeClick = (el) => {
    el.removeEventListener('click', el.__confirmClick__);
    delete el.__confirmClick__;
  };
  app.directive('confirm', {
    mounted: addClick,
    updated: addClick,
    unmounted: removeClick,
  });
}

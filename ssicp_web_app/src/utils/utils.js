import { ElMessage } from 'element-plus';
import _ from 'lodash';

export function createLoadingProxy(target, loading) {
  console.log('createLoadingProxy');
  return new Proxy(target, {
    apply: async function (target, thisArg, argumentsList) {
      try {
        if (loading.value === true) return;
        loading.value = true;
        return await Reflect.apply(target, thisArg, argumentsList);
      } finally {
        loading.value = false;
      }
    },
  });
}

function highlightText(value, keyword) {
  if (!keyword) return value;
  const regex = new RegExp(keyword, 'gi'); // 使用全局不区分大小写的正则表达式

  // 使用 replace 方法将匹配的文本用带有高亮样式的 <span> 标签包裹
  return value.replace(
    regex,
    (match) => `<span style="background-color:red">${match}</span>`
  );
}
export function highlight(list, fieldList, keyword) {
  list.forEach((row) => {
    fieldList.forEach((field) => {
      row[field + '_highlight'] = highlightText(row[field], keyword);
    });
  });
  // if (!keyword) return originalText;
  // return `<span style='color:red'>${originalText}</span>`;
}
export function formValidation(formRef) {
  if (!formRef) Promise.reject('ref对象为空');
  return new Promise(async (resolve, reject) => {
    await formRef.validate((valid, fields) => {
      if (valid) {
        resolve();
      } else {
        ElMessage({
          dangerouslyUseHTMLString: true,
          message: _.map(fields, (array) => {
            return array[0].message;
          })
            .map((message, index) => {
              return index + 1 + '. ' + message;
            })
            .join('<br/>'),
          type: 'warning',
        });
        reject(fields);
      }
    });
  });
}

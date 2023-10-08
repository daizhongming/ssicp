import { createApp } from 'vue';
import { createPinia } from 'pinia';

import App from './App.vue';
import router from './router';

import ElementPlus from 'element-plus';
// import locale from 'element-plus/lib/locale/lang/zh-cn';
import locale from 'element-plus/es/locale/lang/zh-cn';
// import "./assets/element-vars.scss";
import 'element-plus/dist/index.css';
import 'element-plus/theme-chalk/dark/css-vars.css';
import './assets/keji.scss';
import './assets/base.scss';

const app = createApp(App);
// 全局异常处理
app.config.errorHandler = (err, vm, info) => {
  // 处理错误
  console.error('globalErrorHandler', err, vm, info);
};

// 全局注册图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}

// 全局注册布局容器
import layoutContainers from '@/components/layout';
for (const [key, component] of Object.entries(layoutContainers)) {
  app.component(key, component);
}

// 全局注册工具类
import utilsPlugin from '@/utils';
app.use(utilsPlugin);

app.use(createPinia());
app.use(router);
app.use(ElementPlus, { locale });

// 全局指令
import directives from './directives';
app.use(directives);

app.mount('#app');

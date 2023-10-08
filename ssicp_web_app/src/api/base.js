import axios from 'axios';
import { ElMessage } from 'element-plus';

export const baseURL = '/api'; // 设置基本的 API 地址
const api = axios.create({
  baseURL,
  timeout: 5000, // 设置请求超时时间
});

// 请求拦截器
api.interceptors.request.use(
  (config) => {
    // 在发送请求之前做一些处理，例如添加请求头等
    // 可以修改请求配置(config)并返回，也可以返回原始配置(config)不做修改
    config.headers['token'] = localStorage.getItem('token');
    return config;
  },
  (error) => {
    console.error('request error');
    throw error;
  }
);

// 响应拦截器
api.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    await handleError(error);
  }
);

async function handleError(error) {
  let message;
  if (error.response) {
    if (error.response.data instanceof Blob) {
      message = await readBlobMessage(error.response.data);
    } else {
      message = error.response.data;
    }
  } else {
    message = error.message;
  }
  ElMessage({
    message: message,
    type: 'error',
  });
  throw error;
}

function readBlobMessage(blob) {
  return new Promise((resolve) => {
    const reader = new FileReader();
    reader.onload = () => {
      resolve(reader.result);
    };
    reader.readAsText(blob);
  });
}

// 定义通用的请求方法
const request = (config) => {
  return api.request(config);
};
export default request;

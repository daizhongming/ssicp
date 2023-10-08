import request, { baseURL } from './base.js';
import { saveAs } from 'file-saver';

import getMime from '@/utils/mime.js';
export function upload(file) {
  const formData = new FormData();
  // 将文件添加到 FormData 中
  formData.append('file', file);
  return request({
    method: 'post',
    url: '/files',
    data: formData,
    headers: {
      'Content-Type': 'multipart/form-data', // 设置请求头
    },
  });
}

export function downloadUrl(fileCode, fileName) {
  return request({
    url: '/files/' + fileCode, // 下载文件的 URL
    method: 'GET',
    responseType: 'blob',
  }).then((response) => {
    console.log(response);
    const blob = new Blob([response.data], { type: getMime(fileName) });
    return URL.createObjectURL(blob);
  });
}

export function download(fileCode, fileName) {
  return request({
    url: '/files/' + fileCode, // 下载文件的 URL
    method: 'GET',
    responseType: 'blob',
  }).then((response) => {
    saveAs(response.data, fileName);
  });
}
export function getUrl(fileCode) {
  return baseURL + '/files/' + fileCode;
}

export function getMessage(fileCode) {
  return request({
    method: 'get',
    url: '/files/' + fileCode + '/message',
  });
}

import request from './base.js';

export function pageList(params) {
  return request({
    method: 'get',
    url: '/configs',
    params: params,
  });
}
export function findAll() {
  return request({
    method: 'get',
    url: '/configs/all',
  });
}

export function add(data) {
  return request({
    method: 'post',
    url: '/configs',
    data: data,
  });
}

export function update(data) {
  return request({
    method: 'put',
    url: '/configs/' + data.configId,
    data: data,
  });
}

export function remove(id) {
  return request({
    method: 'delete',
    url: '/configs/' + id,
  });
}

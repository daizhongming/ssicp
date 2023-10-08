import request from './base.js';

export function findAll() {
  return request({
    method: 'get',
    url: '/roles',
  });
}

export function pageList(data) {
  return request({
    method: 'get',
    url: 'roles/pageList',
    params: data,
  });
}

export function insert(data) {
  return request({
    method: 'post',
    url: 'roles',
    data: data,
  });
}

export function update(data) {
  return request({
    method: 'put',
    url: 'roles/' + data.name,
    data: data,
  });
}

export function del(name) {
  return request({
    method: 'delete',
    url: 'roles/' + name,
  });
}

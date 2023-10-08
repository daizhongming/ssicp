import request from './base.js';

export function pageList(params) {
  return request({
    method: 'get',
    url: '/buttons',
    params: params,
  });
}
export function findAll() {
  return request({
    method: 'get',
    url: '/buttons/all',
  });
}

export function deleteById(id) {
  return request({
    method: 'delete',
    url: '/buttons/' + id,
  });
}

export function findById(id) {
  return request({
    method: 'get',
    url: '/buttons/' + id,
  });
}

export function add(data) {
  return request({
    method: 'post',
    url: '/buttons',
    data: data,
  });
}

export function update(data) {
  return request({
    method: 'put',
    url: '/buttons/' + data.id,
    data: data,
  });
}

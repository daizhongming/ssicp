import request from '../base.js';

export function pageList(data) {
  return request({
    method: 'get',
    url: '/devices',
    params: data,
  });
}

export function getByid(id) {
  return request({
    method: 'get',
    url: '/devices/getByid/' + id,
  });
}

export function getDevicesBypId(pid) {
  return request({
    method: 'get',
    url: '/devices/getDevicesBypId/' + pid,
  });
}

export function del(id) {
  return request({
    method: 'delete',
    url: '/devices/' + id,
  });
}

export function add(data) {
  return request({
    method: 'post',
    url: '/devices',
    data: data,
  });
}

export function update(data) {
  return request({
    method: 'put',
    url: '/devices',
    data: data,
  });
}
export function tree() {
  return request({
    method: 'get',
    url: '/devices/tree',
  });
}

export function pipeTree() {
  return request({
    method: 'get',
    url: '/devices/pipeTree',
  });
}

export function findAllDeviceData() {
  return request({
    method: 'get',
    url: '/devices/allDeviceData',
  });
}

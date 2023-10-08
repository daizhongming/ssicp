import request from './base.js';

export function search(params) {
  return request({
    method: 'get',
    url: '/users',
    params,
  });
}

export function add(user) {
  return request({
    method: 'post',
    url: '/users',
    data: user,
  });
}

export function update(user) {
  return request({
    method: 'put',
    url: '/users/' + user.name,
    data: user,
  });
}

export function remove(name) {
  return request({
    method: 'delete',
    url: '/users/' + name,
  });
}

export function login(user) {
  return request({
    method: 'post',
    url: '/login',
    data: user,
  });
}

export function getMessage() {
  return request({
    method: 'get',
    url: '/login/messages',
  });
}

export function findAllInspector() {
  return request({
    method: 'get',
    url: '/users/inspectors',
  });
}

export function changePassword(data) {
  return request({
    method: 'put',
    url: '/users/changePassword',
    params: data,
  });
}

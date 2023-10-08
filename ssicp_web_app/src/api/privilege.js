import request from './base.js';

export function addUserPrivileges(privilege) {
  return request({
    method: 'post',
    url: 'privileges/users/' + privilege.userName,
    data: privilege,
  });
}

export function findUserPrivileges(userName) {
  return request({
    method: 'get',
    url: 'privileges/users/' + userName,
  });
}

export function addRolePrivileges(rolePrivileges) {
  return request({
    method: 'post',
    url: 'privileges/roles/' + rolePrivileges.roleName,
    data: rolePrivileges,
  });
}

export function findRolePrivileges(roleName) {
  return request({
    method: 'get',
    url: 'privileges/roles/' + roleName,
  });
}

import request from './base.js';

export function findAll() {
  return request({
    method: 'get',
    url: '/menus',
  });
}

export function findsubsetAll() {
  return request({
    method: 'get',
    url: '/menus/subset',
  });
}

export function findUnPublic() {
  return request({
    method: 'get',
    url: '/menus/unPublic',
  });
}

export function add(menu) {
  return request({
    method: 'post',
    url: '/menus',
    data: menu,
  });
}

export function deleteById(code) {
  return request({
    method: 'delete',
    url: '/menus/' + code,
  });
}

export function update(menu) {
  return request({
    method: 'put',
    url: '/menus/' + menu.code,
    data: menu,
  });
}

export function findButtons(code) {
  return request({
    method: 'get',
    url: `/menus/${code}/buttons`,
  });
}
export function addButton(button) {
  return request({
    method: 'post',
    url: `/menus/${button.menuCode}/buttons`,
    data: button,
  });
}
export function deleteButton(button) {
  return request({
    method: 'delete',
    url: `/menus/${button.menuCode}/buttons/${button.id}`,
  });
}

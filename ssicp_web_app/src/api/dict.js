import request from './base.js';

export function pageListType(data) {
  return request({
    method: 'get',
    url: 'dictTypes/pageList',
    params: data,
  });
}

export function findAll() {
  return request({
    method: 'get',
    url: '/dictTypes/all',
  });
}

export function pageListData(data) {
  return request({
    method: 'get',
    url: 'dictDatas/pageList',
    params: data,
  });
}

export function loadType(code) {
  return request({
    method: 'get',
    url: 'dictTypes/' + code,
  });
}

export function loadData(id) {
  return request({
    method: 'get',
    url: 'dictDatas/' + id,
  });
}

export function delType(code) {
  return request({
    method: 'delete',
    url: 'dictTypes/' + code,
  });
}

export function delData(id) {
  return request({
    method: 'delete',
    url: 'dictDatas/' + id,
  });
}

export function insertType(data) {
  return request({
    method: 'post',
    url: 'dictTypes',
    data: Object.assign({}, data, {
      privileges: JSON.stringify(data.privileges),
    }),
  });
}

export function insertData(data) {
  return request({
    method: 'post',
    url: 'dictDatas',
    data: data,
  });
}

export function updateType(data) {
  return request({
    method: 'put',
    url: 'dictTypes/' + data.code,
    data: Object.assign({}, data, {
      privileges: JSON.stringify(data.privileges),
    }),
  });
}

export function updateData(data) {
  return request({
    method: 'put',
    url: 'dictDatas/' + data.id,
    data: data,
  });
}

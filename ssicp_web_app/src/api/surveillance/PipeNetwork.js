import request from '../base.js';

export function findAll() {
  return request({
    method: 'get',
    url: '/users',
  });
}

export function pageList(data) {
  return request({
    method: 'get',
    url: 'pipelines',
    params: data,
  });
}

export function insert(data) {
  return request({
    method: 'post',
    url: 'pipelines',
    data: data,
  });
}

export function update(data) {
  return request({
    method: 'put',
    url: 'pipelines/' + data.id,
    data: data,
  });
}

export function load(id) {
  return request({
    method: 'get',
    url: 'pipelines/' + id,
  });
}

export function del(id) {
  return request({
    method: 'delete',
    url: 'pipelines/' + id,
  });
}

export function pressurePipelineGrouping() {
  return request({
    method: 'get',
    url: 'pipelines/pressurePipelineGrouping',
  });
}

export function pressurePipelineGroupingV2(regions) {
  return request({
    method: 'get',
    url: 'pipelines/pressurePipelineGrouping',
    params: { regions: regions.join(',') },
  });
}

export function getPressureLevelTree() {
  return request({
    method: 'GET',
    url: 'pipelines/pressureLevelTree',
  });
}

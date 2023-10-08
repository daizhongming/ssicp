import request from '../base.js';

export function pagePlansList(data) {
    return request({
      method: 'get',
      url: '/knowledgeBases',
      params: data,
    });
  }

  export function getdevice(data) {
    return request({
      method: 'get',
      url: '/knowledgeBases/getdevice',
    });
  }

  export function add(data) {
    return request({
      method: 'post',
      url: '/knowledgeBases',
      data: data
    });
  }
  
  export function update(data) {
    return request({
      method: 'put',
      url: '/knowledgeBases',
      data: data
    });
  }
  
  export function echo(id) {
    return request({
      method: 'get',
      url: '/knowledgeBases/echo/'+id,
    });
  }
    
  export function getPushRecords(id) {
    return request({
      method: 'get',
      url: '/knowledgeBases/getPushRecords/'+id,
    });
  }

  export function readIncreasing(id) {
    return request({
      method: 'put',
      url: '/knowledgeBases/readIncreasing/'+id,
    });
  }

  export function del(id) {
    return request({
      method: 'delete',
      url: '/knowledgeBases/'+id,
    });
  }
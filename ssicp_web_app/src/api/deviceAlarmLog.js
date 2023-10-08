import request from './base.js';

export function search(params) {
  return request({
    method: 'get',
    url: '/deviceAlarmLogs',
    params,
  });
}

export function handle(data) {
  return request({
    method: 'put',
    url: `/deviceAlarmLogs/handleStatus`,
    data: data,
  });
}

export function alarmDetails(deviceId, indexId, handleTime) {
  return request({
    method: 'get',
    url: '/deviceAlarmLogs/alarmDetails',
    params: { deviceId, indexId, handleTime },
  });
}

export function handleDetails(deviceId, indexId, handleTime) {
  return request({
    method: 'get',
    url: '/deviceAlarmLogs/handleDetails',
    params: { deviceId, indexId, handleTime },
  });
}

export function unHandleAnalyse() {
  return request({
    method: 'get',
    url: '/deviceAlarmLogs/analyse/unHandle',
  });
}

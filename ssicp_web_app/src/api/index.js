import request from './base.js';

export function login(user) {
  return request({
    method: 'post',
    url: '/index/login',
    data: user,
  });
}

export function initMessage() {
  return request({
    method: 'get',
    url: '/index/initMessage',
  });
}

export function getMessage() {
  return request({
    method: 'get',
    url: '/index/messages',
  });
}

export function analyseAlarmByIndexName() {
  return request({
    method: 'get',
    url: '/index/analyseAlarmByIndexName',
  });
}

export function analyseAlarmByMonth() {
  return request({
    method: 'get',
    url: '/index/analyseAlarmByMonth',
  });
}

export function countDeviceAnomalies() {
  return request({
    method: 'get',
    url: '/index/countDeviceAnomalies',
  });
}

export function analyseAnomaliesByMonth() {
  return request({
    method: 'get',
    url: '/index/analyseAnomaliesByMonth',
  });
}

import request from './base.js';

export function findIndexLogsHistory(deviceId, indexId, startTime) {
  return request({
    method: 'get',
    url: '/deviceIndexLogs/indexLogsHistory',
    params: { deviceId, indexId, startTime },
  });
}

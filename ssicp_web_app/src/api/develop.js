import request from './base.js';

export function aiModelTest(params) {
  return request({
    method: 'post',
    url: '/develop/aiModel',
    data: JSON.parse(params),
  }).then((response) => {
    response.data = JSON.stringify(response.data, null, 2);
    return response;
  });
}

export function listThreeDimensionClients() {
  return request({
    method: 'get',
    url: '/develop/3d',
  });
}

export function threeDimensionTest(clientId, body) {
  return request({
    method: 'post',
    url: '/develop/3d',
    params: {
      clientId,
    },
    data: JSON.parse(body),
  });
}

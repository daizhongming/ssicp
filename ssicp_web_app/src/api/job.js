import request from './base.js';

export function search() {
  return request({
    method: 'get',
    url: '/jobs',
  });
}

export function add(job) {
  return request({
    method: 'post',
    url: '/jobs',
    data: job,
  });
}

export function update(job) {
  return request({
    method: 'put',
    url: `/jobs/${job.jobName}/${job.jobGroupName}`,
    data: job,
  });
}

export function remove(job) {
  return request({
    method: 'delete',
    url: `/jobs/${job.jobName}/${job.jobGroupName}`,
  });
}

export function updateStatus(job, status) {
  return request({
    method: 'put',
    url: `/jobs/${job.jobName}/${job.jobGroupName}/status`,
    params: {
      status,
    },
  });
}

export function runOne(job) {
  return request({
    method: 'post',
    url: `/jobs/${job.jobName}/${job.jobGroupName}`,
    data: job,
  });
}

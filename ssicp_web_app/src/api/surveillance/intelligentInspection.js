import request from '../base.js';

export function pagePlansList(data) {
  return request({
    method: 'get',
    url: '/intelligentInspectionPlans',
    params: data,
  });
}

export function getIndicatortemplate(planCode) {
  return request({
    method: 'get',
    url: '/intelligentInspectionPlans/indicatortemplate?planCode=' + planCode,
  });
}

export function addPlans(data) {
  return request({
    method: 'post',
    url: '/intelligentInspectionPlans',
    data: data,
  });
}

export function upordown(data) {
  return request({
    method: 'put',
    url: '/intelligentInspectionPlans/upordown',
    data: data,
  });
}

export function updatePlans(data) {
  return request({
    method: 'put',
    url: '/intelligentInspectionPlans',
    data: data,
  });
}

export function pageOutcomesList(data) {
  return request({
    method: 'get',
    url: '/intelligentInspectionOutcomes',
    params: data,
  });
}

export function removePlan(planCode) {
  return request({
    method: 'delete',
    url: '/intelligentInspectionPlans/' + planCode,
  });
}

export function loadOutcome(outcomeCode) {
  return request({
    method: 'get',
    url: '/intelligentInspectionOutcomes/' + outcomeCode,
  });
}

export function queryExceptionToDos() {
  return request({
    method: 'get',
    url: '/intelligentInspectionOutcomes/queryExceptionToDos',
  });
}

export function confirmOutcome(outcomeCode) {
  return request({
    method: 'put',
    url: '/intelligentInspectionOutcomes/confirm/' + outcomeCode,
  });
}

export function disposeOutcome(data, outcomeCode) {
  return request({
    method: 'put',
    url: '/intelligentInspectionOutcomes/dispose/' + outcomeCode,
    data: { outcomeDisposes: data },
  });
}

export function getdispose(outcomeCode) {
  return request({
    method: 'put',
    url: '/intelligentInspectionOutcomes/getdispose/' + outcomeCode,
  });
}

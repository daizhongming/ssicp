import request from "../base.js";

export function pageListPlan(data) {
  return request({
    method: "get",
    url: "/manualInspectionPlans",
    params:data
  });
}

export function pageListOutcome(data) {
  return request({
    method: "get",
    url: "/manualInspectionOutcomes",
    params:data
  });
}

export function addPlan(data) {
  return request({
    method: "post",
    url: "/manualInspectionPlans",
    data:data
  });
}

export function updatePlan(data) {
  return request({
    method: "put",
    url: "/manualInspectionPlans",
    data:data
  });
}

export function upordown(data) {
  return request({
    method: "put",
    url: "/manualInspectionPlans/upordown",
    data:data
  });
}

export function removePlan(planCode) {
  return request({
    method: "delete",
    url: "/manualInspectionPlans/"+planCode,
  });
}

export function load(outcomeCode) {
  return request({
    method: "get",
    url: "/manualInspectionOutcomes/"+outcomeCode,
  });
}

export function getdispose(outcomeCode) {
  return request({
    method: "get",
    url: "/manualInspectionOutcomes/getdispose/"+outcomeCode,
  });
}

export function disposeOutcome(data,outcomeCode) {
  return request({
    method: "put",
    url: "/manualInspectionOutcomes/dispose/"+outcomeCode,
    data:data
  });
}

export function findIndexLogsHistory(deviceId, index) {
  return request({
    method: 'get',
    url: '/manualInspectionOutcomes/indexLogsHistory',
    params: { deviceId, index },
  });
}
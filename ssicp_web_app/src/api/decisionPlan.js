import request from "./base.js";

export function findPage(params) {
  return request({
    method: "get",
    url: "/decisionPlans",
    params,
  });
}

export function add(decisionPlan) {
  return request({
    method: "post",
    url: "/decisionPlans",
    data: decisionPlan,
  });
}

export function deleteById(id) {
  return request({
    method: "delete",
    url: "/decisionPlans/" + id,
  });
}

export function update(decisionPlan) {
  return request({
    method: "put",
    url: "/decisionPlans/" + decisionPlan.id,
    data: decisionPlan,
  });
}

export function updateReadCount(id) {
  return request({
    method: "put",
    url: "/decisionPlans/" + id + "/readCount",
  });
}

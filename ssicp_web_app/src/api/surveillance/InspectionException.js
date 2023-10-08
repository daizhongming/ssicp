import request from "../base.js";

export function pageList(data) {
  return request({
    method: "get",
    url: "/inspectionExceptions",
    params:data
  });
}

export function updateState(data) {
  return request({
    method: "put",
    url: "/inspectionExceptions/updateState",
    data:data
  });
}

export function findByid(id) {
  return request({
    method: "get",
    url: "/inspectionExceptions/"+id,
  });
}
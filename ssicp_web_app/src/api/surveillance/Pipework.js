import request from "../base.js";

export function pageList(data) {
  return request({
    method: "get",
    url: "pipes",
    params: data,
  });
}

export function del(id) {
  return request({
    method: "delete",
    url: "pipes/" + id,
  });
}

export function insert(data) {
  return request({
    method: "post",
    url: "pipes",
    data: data,
  });
}

export function insertSensor(data) {
  return request({
    method: "post",
    url: "sensors",
    data: data,
  });
}

export function update(data) {
  return request({
    method: "put",
    url: "pipes/" + data.id,
    data: data,
  });
}

export function load(id) {
  return request({
    method: "get",
    url: "pipes/" + id,
  });
}


export function devicesandAlarm(id) {
  return request({
    method: "get",
    url: "pipes/devicesandAlarm/"+id,
  });
}

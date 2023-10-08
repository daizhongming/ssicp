import request from "./base.js";

export function findAll(data) {
    return request({
      method: "get",
      url: "/logs",
      params:data,
    });
  }
import request from "./base.js";

export function findPage(params) {
  return request({
    method: "get",
    url: "/benefitAnalysisReports",
    params,
  });
}

export function add(benefitAnalysisReport) {
  return request({
    method: "post",
    url: "/benefitAnalysisReports",
    data: benefitAnalysisReport,
  });
}

export function deleteById(id) {
  return request({
    method: "delete",
    url: "/benefitAnalysisReports/" + id,
  });
}

export function update(benefitAnalysisReport) {
  return request({
    method: "put",
    url: "/benefitAnalysisReports/" + benefitAnalysisReport.id,
    data: benefitAnalysisReport,
  });
}

export function updateReadCount(id) {
  return request({
    method: "put",
    url: "/benefitAnalysisReports/" + id + "/readCount",
  });
}

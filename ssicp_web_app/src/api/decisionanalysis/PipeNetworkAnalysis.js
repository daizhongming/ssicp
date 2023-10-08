import request from "../base.js";

export function pageList(data) {
  return request({
    method: "post",
    url: "/pipelineAnalyse/pageList",
    data: data,
  });
}

export function findOperationalAll(data) {
    return request({
        method: 'get',
        url: "/operationalAnalytics",
        params:data
    })
}

export function weatherInfo(city) {
  const url = new URL("https://restapi.amap.com/v3/weather/weatherInfo");
  
  // 添加查询参数
  const params = { city: city, key: '257bf1b2367a9a939afd1c1805dd2f25', extensions: 'base' };
  Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));

  return fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();  // 这会返回一个 Promise
    })
    .catch(error => {
      console.error('Error occurred:', error);
    });
}

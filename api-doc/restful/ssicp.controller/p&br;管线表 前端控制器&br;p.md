# 查询 分页查询

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/?size=992&page=513&order=牌烤花郧敞辽抨嘻雨牙膘&prop=筷队泞咬己纬眼扼雨吠乐情叫驮忌寝&propsMap.key1=耸域聘网乍&propsMap.key2=题钧殿曲坍窃诫舟镐桶鄙终&keyword=关键字28&parentDeviceType=设备类型60&pipelineId=管线id43&pipeIds[0]=临时字段，用于将pipelineId转成pipeIds92&pipeIds[1]=临时字段，用于将pipelineId转成pipeIds52
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//size:992
//page:513
//order:牌烤花郧敞辽抨嘻雨牙膘
//prop:筷队泞咬己纬眼扼雨吠乐情叫驮忌寝
//propsMap.key1:耸域聘网乍
//propsMap.key2:题钧殿曲坍窃诫舟镐桶鄙终
//keyword:关键字28
//parentDeviceType:设备类型60
//pipelineId:管线id43
//pipeIds[0]:临时字段，用于将pipelineId转成pipeIds92
//pipeIds[1]:临时字段，用于将pipelineId转成pipeIds52

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| size     | **int**     | 否  |   |   |
| page     | **int**     | 否  |   |   |
| order     | **String**     | 否  |   |   |
| prop     | **String**     | 否  |   |   |
| propsMap     | **Map\<String, String\>**     | 否  |   |   |
| keyword     | **String**     | 否  |  关键字 |   |
| parentDeviceType     | **String**     | 否  |  设备类型 |   |
| pipelineId     | **String**     | 否  |  管线id |   |
| pipeIds     | **List\<String\>**     | 否  |  临时字段，用于将pipelineId转成pipeIds |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 513,
    "data": [
      {
        "id": "主键98",
        "pipelineName": "管线名称114",
        "pipelineType": "管线类型79",
        "pressureRating": "压力等级（mpa）1",
        "region": "区域划分6",
        "startPosition": "起始位置53",
        "endPosition": "结束位置5",
        "pipeIds": "管段id集合3",
        "createTime": "2023-12-08T01:11:16.280+0000",
        "updateTime": "2023-12-08T04:53:05.234+0000",
        "createdById": 611,
        "updatedById": 107,
        "deleteFlag": 614,
        "comments": "备注91",
        "pipelineLength": 743.8378680092546,
        "pipeCount": 214,
        "elbowCount": 574,
        "compensatorCount": 983,
        "valveCount": 338,
        "instrumentCount": 525
      },
      {
        "id": "主键98",
        "pipelineName": "管线名称114",
        "pipelineType": "管线类型79",
        "pressureRating": "压力等级（mpa）1",
        "region": "区域划分6",
        "startPosition": "起始位置53",
        "endPosition": "结束位置5",
        "pipeIds": "管段id集合3",
        "createTime": "2023-12-08T01:11:16.280+0000",
        "updateTime": "2023-12-08T04:53:05.234+0000",
        "createdById": 611,
        "updatedById": 107,
        "deleteFlag": 614,
        "comments": "备注91",
        "pipelineLength": 743.8378680092546,
        "pipeCount": 214,
        "elbowCount": 574,
        "compensatorCount": 983,
        "valveCount": 338,
        "instrumentCount": 525
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |



# 新增

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/
```

### 请求方法
```
POST
```

### 请求体类型
```
applicatin/json
```

## 入参
### 入参示例 (RequestBody)
```json
{
  "id": "主键89",
  "pipelineName": "管线名称62",
  "pipelineType": "管线类型47",
  "pressureRating": "压力等级（mpa）66",
  "region": "区域划分91",
  "startPosition": "起始位置119",
  "endPosition": "结束位置28",
  "pipeIds": "管段id集合32",
  "createTime": "2023-12-08T09:40:16.344+0000",
  "updateTime": "2023-12-07T23:36:45.995+0000",
  "createdById": 168,
  "updatedById": 915,
  "deleteFlag": 966,
  "comments": "备注10"
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| id     | **String**     | 否  |  主键 |   |
| pipelineName     | **String**     | **是**  |  管线名称 |   |
| pipelineType     | **String**     | **是**  |  管线类型 |   |
| pressureRating     | **String**     | **是**  |  压力等级（mpa） |   |
| region     | **String**     | **是**  |  区域划分 |   |
| startPosition     | **String**     | 否  |  起始位置 |   |
| endPosition     | **String**     | 否  |  结束位置 |   |
| pipeIds     | **String**     | 否  |  管段id集合 |   |
| createTime     | **Date**     | 否  |  创建时间 |   |
| updateTime     | **Date**     | 否  |  修改时间 |   |
| createdById     | **Long**     | 否  |  创建人 |   |
| updatedById     | **Long**     | 否  |  修改人 |   |
| deleteFlag     | **Integer**     | 否  |  删除标记（0.未删除 1.删除） |   |
| comments     | **String**     | 否  |  备注 |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 593,
    "data": [
      {
        "id": "主键125",
        "pipelineName": "管线名称126",
        "pipelineType": "管线类型61",
        "pressureRating": "压力等级（mpa）55",
        "region": "区域划分47",
        "startPosition": "起始位置92",
        "endPosition": "结束位置22",
        "pipeIds": "管段id集合122",
        "createTime": "2023-12-08T09:38:06.740+0000",
        "updateTime": "2023-12-08T04:20:53.246+0000",
        "createdById": 406,
        "updatedById": 643,
        "deleteFlag": 911,
        "comments": "备注110",
        "pipelineLength": 287.6245263501221,
        "pipeCount": 634,
        "elbowCount": 162,
        "compensatorCount": 894,
        "valveCount": 901,
        "instrumentCount": 350
      },
      {
        "id": "主键125",
        "pipelineName": "管线名称126",
        "pipelineType": "管线类型61",
        "pressureRating": "压力等级（mpa）55",
        "region": "区域划分47",
        "startPosition": "起始位置92",
        "endPosition": "结束位置22",
        "pipeIds": "管段id集合122",
        "createTime": "2023-12-08T09:38:06.740+0000",
        "updateTime": "2023-12-08T04:20:53.246+0000",
        "createdById": 406,
        "updatedById": 643,
        "deleteFlag": 911,
        "comments": "备注110",
        "pipelineLength": 287.6245263501221,
        "pipeCount": 634,
        "elbowCount": 162,
        "compensatorCount": 894,
        "valveCount": 901,
        "instrumentCount": 350
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |



# 刪除

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/{id}
```

### 请求方法
```
DELETE
```

### 请求体类型
```
application/x-www-form-urlencoded
```

## 入参
### 入参示例 (Postman Bulk Edit)
```json
//id:敲架钉眶降镣陇

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| id     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 19,
    "data": [
      {
        "id": "主键19",
        "pipelineName": "管线名称104",
        "pipelineType": "管线类型59",
        "pressureRating": "压力等级（mpa）77",
        "region": "区域划分107",
        "startPosition": "起始位置87",
        "endPosition": "结束位置75",
        "pipeIds": "管段id集合114",
        "createTime": "2023-12-08T09:19:14.045+0000",
        "updateTime": "2023-12-07T18:50:56.305+0000",
        "createdById": 475,
        "updatedById": 434,
        "deleteFlag": 839,
        "comments": "备注59",
        "pipelineLength": 824.055132456778,
        "pipeCount": 172,
        "elbowCount": 269,
        "compensatorCount": 340,
        "valveCount": 981,
        "instrumentCount": 564
      },
      {
        "id": "主键19",
        "pipelineName": "管线名称104",
        "pipelineType": "管线类型59",
        "pressureRating": "压力等级（mpa）77",
        "region": "区域划分107",
        "startPosition": "起始位置87",
        "endPosition": "结束位置75",
        "pipeIds": "管段id集合114",
        "createTime": "2023-12-08T09:19:14.045+0000",
        "updateTime": "2023-12-07T18:50:56.305+0000",
        "createdById": 475,
        "updatedById": 434,
        "deleteFlag": 839,
        "comments": "备注59",
        "pipelineLength": 824.055132456778,
        "pipeCount": 172,
        "elbowCount": 269,
        "compensatorCount": 340,
        "valveCount": 981,
        "instrumentCount": 564
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |



# 更新

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/{id}
```

### 请求方法
```
PUT
```

### 请求体类型
```
applicatin/json
```

## 入参
### 入参示例 (RequestBody)
```json
{
  "id": "主键118",
  "pipelineName": "管线名称2",
  "pipelineType": "管线类型71",
  "pressureRating": "压力等级（mpa）78",
  "region": "区域划分32",
  "startPosition": "起始位置2",
  "endPosition": "结束位置6",
  "pipeIds": "管段id集合26",
  "createTime": "2023-12-07T22:15:44.082+0000",
  "updateTime": "2023-12-07T19:44:19.683+0000",
  "createdById": 840,
  "updatedById": 246,
  "deleteFlag": 357,
  "comments": "备注86"
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| id     | **String**     | 否  |  主键 |   |
| pipelineName     | **String**     | **是**  |  管线名称 |   |
| pipelineType     | **String**     | **是**  |  管线类型 |   |
| pressureRating     | **String**     | **是**  |  压力等级（mpa） |   |
| region     | **String**     | **是**  |  区域划分 |   |
| startPosition     | **String**     | 否  |  起始位置 |   |
| endPosition     | **String**     | 否  |  结束位置 |   |
| pipeIds     | **String**     | 否  |  管段id集合 |   |
| createTime     | **Date**     | 否  |  创建时间 |   |
| updateTime     | **Date**     | 否  |  修改时间 |   |
| createdById     | **Long**     | 否  |  创建人 |   |
| updatedById     | **Long**     | 否  |  修改人 |   |
| deleteFlag     | **Integer**     | 否  |  删除标记（0.未删除 1.删除） |   |
| comments     | **String**     | 否  |  备注 |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 24,
    "data": [
      {
        "id": "主键53",
        "pipelineName": "管线名称71",
        "pipelineType": "管线类型49",
        "pressureRating": "压力等级（mpa）47",
        "region": "区域划分100",
        "startPosition": "起始位置97",
        "endPosition": "结束位置102",
        "pipeIds": "管段id集合51",
        "createTime": "2023-12-08T13:34:46.110+0000",
        "updateTime": "2023-12-08T09:15:42.416+0000",
        "createdById": 119,
        "updatedById": 200,
        "deleteFlag": 725,
        "comments": "备注1",
        "pipelineLength": 195.5634951501461,
        "pipeCount": 305,
        "elbowCount": 323,
        "compensatorCount": 441,
        "valveCount": 506,
        "instrumentCount": 499
      },
      {
        "id": "主键53",
        "pipelineName": "管线名称71",
        "pipelineType": "管线类型49",
        "pressureRating": "压力等级（mpa）47",
        "region": "区域划分100",
        "startPosition": "起始位置97",
        "endPosition": "结束位置102",
        "pipeIds": "管段id集合51",
        "createTime": "2023-12-08T13:34:46.110+0000",
        "updateTime": "2023-12-08T09:15:42.416+0000",
        "createdById": 119,
        "updatedById": 200,
        "deleteFlag": 725,
        "comments": "备注1",
        "pipelineLength": 195.5634951501461,
        "pipeCount": 305,
        "elbowCount": 323,
        "compensatorCount": 441,
        "valveCount": 506,
        "instrumentCount": 499
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |



# 更新

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/pipes/deleteByPipeId
```

### 请求方法
```
PUT
```

### 请求体类型
```
application/x-www-form-urlencoded
```

## 入参
### 入参示例 (Postman Bulk Edit)
```json
//pipeId:舅于嘎为猿营捷讹愤

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| pipeId     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 864,
    "data": [
      {
        "id": "主键37",
        "pipelineName": "管线名称35",
        "pipelineType": "管线类型93",
        "pressureRating": "压力等级（mpa）115",
        "region": "区域划分101",
        "startPosition": "起始位置38",
        "endPosition": "结束位置24",
        "pipeIds": "管段id集合39",
        "createTime": "2023-12-08T04:38:10.473+0000",
        "updateTime": "2023-12-07T22:10:04.855+0000",
        "createdById": 35,
        "updatedById": 108,
        "deleteFlag": 270,
        "comments": "备注25",
        "pipelineLength": 69.01646669184507,
        "pipeCount": 786,
        "elbowCount": 624,
        "compensatorCount": 243,
        "valveCount": 875,
        "instrumentCount": 578
      },
      {
        "id": "主键37",
        "pipelineName": "管线名称35",
        "pipelineType": "管线类型93",
        "pressureRating": "压力等级（mpa）115",
        "region": "区域划分101",
        "startPosition": "起始位置38",
        "endPosition": "结束位置24",
        "pipeIds": "管段id集合39",
        "createTime": "2023-12-08T04:38:10.473+0000",
        "updateTime": "2023-12-07T22:10:04.855+0000",
        "createdById": 35,
        "updatedById": 108,
        "deleteFlag": 270,
        "comments": "备注25",
        "pipelineLength": 69.01646669184507,
        "pipeCount": 786,
        "elbowCount": 624,
        "compensatorCount": 243,
        "valveCount": 875,
        "instrumentCount": 578
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |



# 查询 根据主键 id 查询

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/pipelines/{id}?id=毒停妇涤怯
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//id:毒停妇涤怯

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| id     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 415,
    "data": [
      {
        "id": "主键30",
        "pipelineName": "管线名称92",
        "pipelineType": "管线类型16",
        "pressureRating": "压力等级（mpa）99",
        "region": "区域划分75",
        "startPosition": "起始位置98",
        "endPosition": "结束位置68",
        "pipeIds": "管段id集合91",
        "createTime": "2023-12-08T10:20:54.559+0000",
        "updateTime": "2023-12-07T23:38:29.048+0000",
        "createdById": 566,
        "updatedById": 292,
        "deleteFlag": 278,
        "comments": "备注127",
        "pipelineLength": 742.136718227534,
        "pipeCount": 763,
        "elbowCount": 1021,
        "compensatorCount": 866,
        "valveCount": 22,
        "instrumentCount": 401
      },
      {
        "id": "主键30",
        "pipelineName": "管线名称92",
        "pipelineType": "管线类型16",
        "pressureRating": "压力等级（mpa）99",
        "region": "区域划分75",
        "startPosition": "起始位置98",
        "endPosition": "结束位置68",
        "pipeIds": "管段id集合91",
        "createTime": "2023-12-08T10:20:54.559+0000",
        "updateTime": "2023-12-07T23:38:29.048+0000",
        "createdById": 566,
        "updatedById": 292,
        "deleteFlag": 278,
        "comments": "备注127",
        "pipelineLength": 742.136718227534,
        "pipeCount": 763,
        "elbowCount": 1021,
        "compensatorCount": 866,
        "valveCount": 22,
        "instrumentCount": 401
      }
    ]
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **PageResultEntity\<PipelineSearchVo\>**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<PipelineSearchVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineName     | **String**    |  管线名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineType     | **String**    |  管线类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pressureRating     | **String**    |  压力等级（mpa） |   |
|&ensp;&ensp;&ensp;&ensp;└─ region     | **String**    |  区域划分 |   |
|&ensp;&ensp;&ensp;&ensp;└─ startPosition     | **String**    |  起始位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ endPosition     | **String**    |  结束位置 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeIds     | **String**    |  管段id集合 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdById     | **Long**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedById     | **Long**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipelineLength     | **double**    |  长度 |   |
|&ensp;&ensp;&ensp;&ensp;└─ pipeCount     | **int**    |  管段数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ elbowCount     | **int**    |  弯头数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ compensatorCount     | **int**    |  补偿器数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ valveCount     | **int**    |  阀门数量 |   |
|&ensp;&ensp;&ensp;&ensp;└─ instrumentCount     | **int**    |  仪表数量 |   |
| status     | **Object**    |   |   |




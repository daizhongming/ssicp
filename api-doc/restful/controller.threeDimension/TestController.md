# position

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/3d/test/position
```

### 请求方法
```
GET
```


## 入参
> 此接口无任何入参



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": "主键18",
    "deviceId": "设备id42",
    "index": "指标11",
    "lowerAlarm": 605.0916563734786,
    "seniorAlarm": 243.38511808773634,
    "seniorSeniorAlarm": 639.8707534958505,
    "state": 494,
    "createTime": {
      "date": {
        "year": 834,
        "month": 92,
        "day": 10
      },
      "time": {
        "hour": -52,
        "minute": -26,
        "second": -54,
        "nano": 632
      }
    },
    "updateTime": {
      "date": {
        "year": 116,
        "month": 70,
        "day": 46
      },
      "time": {
        "hour": -65,
        "minute": 90,
        "second": 115,
        "nano": 382
      }
    },
    "createdByName": "创建人39",
    "updatedByName": "修改人93",
    "deleteFlag": 17,
    "comments": "备注39",
    "ignoreTime": "2023-12-07T21:11:29.759+0000"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceAlarm**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceId     | **String**    |  设备id |   |
|└─ index     | **String**    |  指标 |   |
|└─ lowerAlarm     | **Double**    |  低报 |   |
|└─ seniorAlarm     | **Double**    |  高报 |   |
|└─ seniorSeniorAlarm     | **Double**    |  高高报 |   |
|└─ state     | **Integer**    |  启停状态 |   |
|└─ createTime     | **LocalDateTime**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ updateTime     | **LocalDateTime**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# alarm

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/3d/test/alarm
```

### 请求方法
```
GET
```


## 入参
> 此接口无任何入参



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": "主键79",
    "deviceId": "设备id61",
    "index": "指标75",
    "lowerAlarm": 918.5817766984612,
    "seniorAlarm": 721.1409587466673,
    "seniorSeniorAlarm": 103.11494491878581,
    "state": 789,
    "createTime": {
      "date": {
        "year": 478,
        "month": 34,
        "day": 88
      },
      "time": {
        "hour": 62,
        "minute": 19,
        "second": 102,
        "nano": 1017
      }
    },
    "updateTime": {
      "date": {
        "year": 603,
        "month": 21,
        "day": 68
      },
      "time": {
        "hour": -16,
        "minute": 51,
        "second": 38,
        "nano": 961
      }
    },
    "createdByName": "创建人11",
    "updatedByName": "修改人99",
    "deleteFlag": 252,
    "comments": "备注105",
    "ignoreTime": "2023-12-08T09:29:22.537+0000"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceAlarm**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceId     | **String**    |  设备id |   |
|└─ index     | **String**    |  指标 |   |
|└─ lowerAlarm     | **Double**    |  低报 |   |
|└─ seniorAlarm     | **Double**    |  高报 |   |
|└─ seniorSeniorAlarm     | **Double**    |  高高报 |   |
|└─ state     | **Integer**    |  启停状态 |   |
|└─ createTime     | **LocalDateTime**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ updateTime     | **LocalDateTime**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# inspection

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/3d/test/inspection
```

### 请求方法
```
GET
```


## 入参
> 此接口无任何入参



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": "主键23",
    "deviceId": "设备id125",
    "index": "指标92",
    "lowerAlarm": 459.2169816112214,
    "seniorAlarm": 507.08056225955767,
    "seniorSeniorAlarm": 975.4880728286166,
    "state": 940,
    "createTime": {
      "date": {
        "year": 871,
        "month": 93,
        "day": 65
      },
      "time": {
        "hour": 32,
        "minute": 76,
        "second": 38,
        "nano": 43
      }
    },
    "updateTime": {
      "date": {
        "year": 211,
        "month": 54,
        "day": 46
      },
      "time": {
        "hour": 67,
        "minute": -84,
        "second": -17,
        "nano": 468
      }
    },
    "createdByName": "创建人114",
    "updatedByName": "修改人81",
    "deleteFlag": 625,
    "comments": "备注58",
    "ignoreTime": "2023-12-08T15:45:56.715+0000"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceAlarm**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceId     | **String**    |  设备id |   |
|└─ index     | **String**    |  指标 |   |
|└─ lowerAlarm     | **Double**    |  低报 |   |
|└─ seniorAlarm     | **Double**    |  高报 |   |
|└─ seniorSeniorAlarm     | **Double**    |  高高报 |   |
|└─ state     | **Integer**    |  启停状态 |   |
|└─ createTime     | **LocalDateTime**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ updateTime     | **LocalDateTime**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# simulation

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/3d/test/simulation?id=蒙晰蓝宅拣
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
id:蒙晰蓝宅拣

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| id     | **String**     | **是**  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": "主键97",
    "deviceId": "设备id112",
    "index": "指标10",
    "lowerAlarm": 197.50408969030602,
    "seniorAlarm": 379.9388016503499,
    "seniorSeniorAlarm": 248.88788951760503,
    "state": 370,
    "createTime": {
      "date": {
        "year": 259,
        "month": 95,
        "day": 71
      },
      "time": {
        "hour": 99,
        "minute": 5,
        "second": 28,
        "nano": 280
      }
    },
    "updateTime": {
      "date": {
        "year": 64,
        "month": 21,
        "day": 15
      },
      "time": {
        "hour": 9,
        "minute": 24,
        "second": 84,
        "nano": 455
      }
    },
    "createdByName": "创建人30",
    "updatedByName": "修改人29",
    "deleteFlag": 177,
    "comments": "备注64",
    "ignoreTime": "2023-12-08T17:15:31.113+0000"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceAlarm**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceId     | **String**    |  设备id |   |
|└─ index     | **String**    |  指标 |   |
|└─ lowerAlarm     | **Double**    |  低报 |   |
|└─ seniorAlarm     | **Double**    |  高报 |   |
|└─ seniorSeniorAlarm     | **Double**    |  高高报 |   |
|└─ state     | **Integer**    |  启停状态 |   |
|└─ createTime     | **LocalDateTime**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ updateTime     | **LocalDateTime**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |




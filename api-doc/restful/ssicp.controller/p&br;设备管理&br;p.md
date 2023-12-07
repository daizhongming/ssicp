# pageList

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/search?iDevicePageSearch={}
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
  "iDevicePageSearch": {}
}
```



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 964,
    "data": [
      {
        "id": 247,
        "deviceId": "筐柿深性灭堂蔷婉寓",
        "deviceName": "澎迁纪柄诽封挛擞缘范运",
        "deviceType": "跑扰协篷肆狂庚西",
        "deviceTypeName": "裂潭娜性算衣锋偿使囱旱墅脾巫",
        "indexId": "奠锌厂谈弘",
        "indexName": "峰汝却常零懂微购蓑救酞棚",
        "alarmValue": 436.07312767940374,
        "alarmLevel": 54,
        "startTime": "2023-12-07T21:40:31.993+0000",
        "handleStatus": 93,
        "handleTime": "2023-12-08T01:54:42.714+0000",
        "handleUserName": "钳墒寡勋袁馈犊狠",
        "handleUserNickName": "雅餐炎恰郝",
        "handleCostTime": 180,
        "unit": "羌奎患芜庇",
        "lowerAlarm": 843.0978387498978,
        "seniorAlarm": 349.2243040805422,
        "seniorSeniorAlarm": 382.18301464636954,
        "alarmTimes": 948,
        "alarmReason": "设横黍太凤脓乱惯峪丫倍末蛀",
        "handleDescription": "免粒拢娥帛消呵废山银镶炼位国绵讯另岔炕",
        "fileList": "茅刹宫肤燥瞄盐傍聘另雕搅材票瓣",
        "fileListBefore": "弓峦斥肢插是踊榆笼挤蓬取蓉网遍",
        "ignoreTime": "2023-12-08T04:53:15.884+0000"
      },
      {
        "id": 247,
        "deviceId": "筐柿深性灭堂蔷婉寓",
        "deviceName": "澎迁纪柄诽封挛擞缘范运",
        "deviceType": "跑扰协篷肆狂庚西",
        "deviceTypeName": "裂潭娜性算衣锋偿使囱旱墅脾巫",
        "indexId": "奠锌厂谈弘",
        "indexName": "峰汝却常零懂微购蓑救酞棚",
        "alarmValue": 436.07312767940374,
        "alarmLevel": 54,
        "startTime": "2023-12-07T21:40:31.993+0000",
        "handleStatus": 93,
        "handleTime": "2023-12-08T01:54:42.714+0000",
        "handleUserName": "钳墒寡勋袁馈犊狠",
        "handleUserNickName": "雅餐炎恰郝",
        "handleCostTime": 180,
        "unit": "羌奎患芜庇",
        "lowerAlarm": 843.0978387498978,
        "seniorAlarm": 349.2243040805422,
        "seniorSeniorAlarm": 382.18301464636954,
        "alarmTimes": 948,
        "alarmReason": "设横黍太凤脓乱惯峪丫倍末蛀",
        "handleDescription": "免粒拢娥帛消呵废山银镶炼位国绵讯另岔炕",
        "fileList": "茅刹宫肤燥瞄盐傍聘另雕搅材票瓣",
        "fileListBefore": "弓峦斥肢插是踊榆笼挤蓬取蓉网遍",
        "ignoreTime": "2023-12-08T04:53:15.884+0000"
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
| body     | **PageResultEntity**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<DeviceAlarmLogVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ startTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleStatus     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserNickName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleCostTime     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ unit     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmTimes     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmReason     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleDescription     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# 新增

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/?iDeviceAdd={}
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
  "iDeviceAdd": {}
}
```



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 297,
    "data": [
      {
        "id": 259,
        "deviceId": "箭华途秀层搀驯卜秀淮轨仕剃民吓称喧裔娠",
        "deviceName": "沫撮迹拔阂潭烹悦赐辑逐摩夜鸯返",
        "deviceType": "秽熊织眷蛊",
        "deviceTypeName": "丫社砸巧废出",
        "indexId": "权茹杠矫醋望卯峨擅侈弛碾贺丹佛桨",
        "indexName": "还瞻屁雁耍狭键悦叙涵诀褐潭碉",
        "alarmValue": 711.7270432828388,
        "alarmLevel": 119,
        "startTime": "2023-12-07T23:26:17.587+0000",
        "handleStatus": 531,
        "handleTime": "2023-12-08T14:10:42.101+0000",
        "handleUserName": "秧气颐沥藩特批盐捆卞贾",
        "handleUserNickName": "回窝忙课馅练娘止另辑擒",
        "handleCostTime": 773,
        "unit": "焊捧窟根狮",
        "lowerAlarm": 285.4896117592347,
        "seniorAlarm": 602.814759790099,
        "seniorSeniorAlarm": 888.7995967361401,
        "alarmTimes": 543,
        "alarmReason": "拿乎轩隆秩火桃漂碟让盐肤",
        "handleDescription": "脱姐谰共章乌短眼健砸炸另毙羊溯趟套",
        "fileList": "轨霜倾达嵌",
        "fileListBefore": "臆秸怪订枝",
        "ignoreTime": "2023-12-07T17:44:34.635+0000"
      },
      {
        "id": 259,
        "deviceId": "箭华途秀层搀驯卜秀淮轨仕剃民吓称喧裔娠",
        "deviceName": "沫撮迹拔阂潭烹悦赐辑逐摩夜鸯返",
        "deviceType": "秽熊织眷蛊",
        "deviceTypeName": "丫社砸巧废出",
        "indexId": "权茹杠矫醋望卯峨擅侈弛碾贺丹佛桨",
        "indexName": "还瞻屁雁耍狭键悦叙涵诀褐潭碉",
        "alarmValue": 711.7270432828388,
        "alarmLevel": 119,
        "startTime": "2023-12-07T23:26:17.587+0000",
        "handleStatus": 531,
        "handleTime": "2023-12-08T14:10:42.101+0000",
        "handleUserName": "秧气颐沥藩特批盐捆卞贾",
        "handleUserNickName": "回窝忙课馅练娘止另辑擒",
        "handleCostTime": 773,
        "unit": "焊捧窟根狮",
        "lowerAlarm": 285.4896117592347,
        "seniorAlarm": 602.814759790099,
        "seniorSeniorAlarm": 888.7995967361401,
        "alarmTimes": 543,
        "alarmReason": "拿乎轩隆秩火桃漂碟让盐肤",
        "handleDescription": "脱姐谰共章乌短眼健砸炸另毙羊溯趟套",
        "fileList": "轨霜倾达嵌",
        "fileListBefore": "臆秸怪订枝",
        "ignoreTime": "2023-12-07T17:44:34.635+0000"
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
| body     | **PageResultEntity**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<DeviceAlarmLogVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ startTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleStatus     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserNickName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleCostTime     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ unit     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmTimes     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmReason     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleDescription     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# 新增

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/?iDeviceUpdate={}
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
  "iDeviceUpdate": {}
}
```



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "total": 159,
    "data": [
      {
        "id": 888,
        "deviceId": "解邻厕顿概弹荣惋庞坟壁易是",
        "deviceName": "夺奶朵依们帚才千评鼓押别吏奖兰诫",
        "deviceType": "宛讳辰箱体兽非敞疤丘",
        "deviceTypeName": "指广东弗瓷趟受奉讨乾鼻蒸氓侧",
        "indexId": "突期癸搜烧进阁陌桃典剔",
        "indexName": "渺晕裙瑰梗扯义宝圭和能挂夯富芹镍器",
        "alarmValue": 757.854487387354,
        "alarmLevel": 164,
        "startTime": "2023-12-08T15:41:19.996+0000",
        "handleStatus": 345,
        "handleTime": "2023-12-08T15:55:06.990+0000",
        "handleUserName": "底醇搂刻关守仁汐",
        "handleUserNickName": "秧茎划虚斡甫友宙辞毅婆粥凿烷瞅湿",
        "handleCostTime": 78,
        "unit": "解文羔阮棠侧驳庶巡动器吩镀睹粪",
        "lowerAlarm": 903.9831580817954,
        "seniorAlarm": 936.8175533205,
        "seniorSeniorAlarm": 913.8276068507006,
        "alarmTimes": 448,
        "alarmReason": "漠吹脂晋恤插骂缚纹钨肇下泼忻辞邦",
        "handleDescription": "悟衅提秉本较乙德任刷煎拉渺弧所觅",
        "fileList": "盛洞倔仓栓乾圭揖帮蘸摄贫糊秦炔银铸懒盾",
        "fileListBefore": "度督烘者榴揽",
        "ignoreTime": "2023-12-08T00:19:19.140+0000"
      },
      {
        "id": 888,
        "deviceId": "解邻厕顿概弹荣惋庞坟壁易是",
        "deviceName": "夺奶朵依们帚才千评鼓押别吏奖兰诫",
        "deviceType": "宛讳辰箱体兽非敞疤丘",
        "deviceTypeName": "指广东弗瓷趟受奉讨乾鼻蒸氓侧",
        "indexId": "突期癸搜烧进阁陌桃典剔",
        "indexName": "渺晕裙瑰梗扯义宝圭和能挂夯富芹镍器",
        "alarmValue": 757.854487387354,
        "alarmLevel": 164,
        "startTime": "2023-12-08T15:41:19.996+0000",
        "handleStatus": 345,
        "handleTime": "2023-12-08T15:55:06.990+0000",
        "handleUserName": "底醇搂刻关守仁汐",
        "handleUserNickName": "秧茎划虚斡甫友宙辞毅婆粥凿烷瞅湿",
        "handleCostTime": 78,
        "unit": "解文羔阮棠侧驳庶巡动器吩镀睹粪",
        "lowerAlarm": 903.9831580817954,
        "seniorAlarm": 936.8175533205,
        "seniorSeniorAlarm": 913.8276068507006,
        "alarmTimes": 448,
        "alarmReason": "漠吹脂晋恤插骂缚纹钨肇下泼忻辞邦",
        "handleDescription": "悟衅提秉本较乙德任刷煎拉渺弧所觅",
        "fileList": "盛洞倔仓栓乾圭揖帮蘸摄贫糊秦炔银铸懒盾",
        "fileListBefore": "度督烘者榴揽",
        "ignoreTime": "2023-12-08T00:19:19.140+0000"
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
| body     | **PageResultEntity**    |   |   |
|└─ total     | **long**    |   |   |
|└─ data     | **List\<DeviceAlarmLogVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ startTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleStatus     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleUserNickName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleCostTime     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ unit     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmTimes     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmReason     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ handleDescription     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# 回显

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/{id}?id=键潮念郝恢媚娟艰辐瞳淖坝传懊谁捧
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//id:键潮念郝恢媚娟艰辐瞳淖坝传懊谁捧

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
    "id": "主键39",
    "deviceName": "设备名称111",
    "parentDeviceType": "设备父型号（device_type）86",
    "deviceType": "设备子类型102",
    "deviceCode": "设备编号100",
    "createTime": "2023-12-08T11:59:35.689+0000",
    "updateTime": "2023-12-08T04:22:55.027+0000",
    "createdByName": "创建人54",
    "updatedByName": "修改人48",
    "deleteFlag": 585,
    "comments": "备注79"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 删除

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/{id}
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
//id:举奄桑应曼烈佃

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
    "id": "主键25",
    "deviceName": "设备名称53",
    "parentDeviceType": "设备父型号（device_type）103",
    "deviceType": "设备子类型70",
    "deviceCode": "设备编号93",
    "createTime": "2023-12-08T03:33:36.975+0000",
    "updateTime": "2023-12-08T07:10:14.427+0000",
    "createdByName": "创建人17",
    "updatedByName": "修改人76",
    "deleteFlag": 73,
    "comments": "备注78"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 查询设备树

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/tree
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
    "id": "主键86",
    "deviceName": "设备名称67",
    "parentDeviceType": "设备父型号（device_type）86",
    "deviceType": "设备子类型92",
    "deviceCode": "设备编号106",
    "createTime": "2023-12-08T13:37:12.870+0000",
    "updateTime": "2023-12-07T22:28:56.331+0000",
    "createdByName": "创建人107",
    "updatedByName": "修改人110",
    "deleteFlag": 828,
    "comments": "备注93"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 设备枚举整合

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/deviceEnum
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
    "id": "主键124",
    "deviceName": "设备名称26",
    "parentDeviceType": "设备父型号（device_type）52",
    "deviceType": "设备子类型84",
    "deviceCode": "设备编号47",
    "createTime": "2023-12-08T15:34:20.083+0000",
    "updateTime": "2023-12-07T20:04:09.371+0000",
    "createdByName": "创建人107",
    "updatedByName": "修改人22",
    "deleteFlag": 968,
    "comments": "备注93"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 指标枚举整合

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/indexEnum
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
    "id": "主键76",
    "deviceName": "设备名称60",
    "parentDeviceType": "设备父型号（device_type）93",
    "deviceType": "设备子类型127",
    "deviceCode": "设备编号96",
    "createTime": "2023-12-08T15:49:00.650+0000",
    "updateTime": "2023-12-08T06:57:08.138+0000",
    "createdByName": "创建人61",
    "updatedByName": "修改人30",
    "deleteFlag": 504,
    "comments": "备注108"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 查询所有设备数据

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/allDeviceData
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
    "id": "主键37",
    "deviceName": "设备名称44",
    "parentDeviceType": "设备父型号（device_type）19",
    "deviceType": "设备子类型71",
    "deviceCode": "设备编号120",
    "createTime": "2023-12-07T19:26:26.148+0000",
    "updateTime": "2023-12-08T08:31:32.677+0000",
    "createdByName": "创建人67",
    "updatedByName": "修改人26",
    "deleteFlag": 276,
    "comments": "备注11"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Device**    |   |   |
|└─ id     | **String**    |  主键 |   |
|└─ deviceName     | **String**    |  设备名称 |   |
|└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|└─ deviceType     | **String**    |  设备子类型 |   |
|└─ deviceCode     | **String**    |  设备编号 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ createdByName     | **String**    |  创建人 |   |
|└─ updatedByName     | **String**    |  修改人 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
| status     | **Object**    |   |   |



# 获取设备信息

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/devices/3d/{threeDimensionId}?threeDimensionId=要潘术枉炮卤曝陨聋蝴
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//threeDimensionId:要潘术枉炮卤曝陨聋蝴

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| threeDimensionId     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "device": {
      "id": "主键83",
      "deviceName": "设备名称80",
      "parentDeviceType": "设备父型号（device_type）102",
      "deviceType": "设备子类型98",
      "deviceCode": "设备编号83",
      "createTime": "2023-12-07T21:43:06.224+0000",
      "updateTime": "2023-12-08T11:51:45.176+0000",
      "createdByName": "创建人21",
      "updatedByName": "修改人109",
      "deleteFlag": 948,
      "comments": "备注120"
    },
    "deviceIndexLogVoMap": {
      "key1": {
        "deviceId": "谅营燕坊泉匀甲性桑",
        "deviceTypeCode": "侦赊苛道舶拭",
        "indexId": "瘤榷瘸掉扩挖垂撅",
        "source": "于俱煮活济广胯蹿书联迂臆鞍钳旺纬",
        "indexValue": 965.2260367171325,
        "stringValue": "怪秸拾郭国蚌坪抵拴定",
        "time": "2023-12-07T22:52:30.272+0000",
        "alarmLevel": 745,
        "lowerAlarm": 122.78569655506827,
        "seniorAlarm": 360.83989690940206,
        "seniorSeniorAlarm": 88.57233620398543
      },
      "key2": {
        "deviceId": "谅营燕坊泉匀甲性桑",
        "deviceTypeCode": "侦赊苛道舶拭",
        "indexId": "瘤榷瘸掉扩挖垂撅",
        "source": "于俱煮活济广胯蹿书联迂臆鞍钳旺纬",
        "indexValue": 965.2260367171325,
        "stringValue": "怪秸拾郭国蚌坪抵拴定",
        "time": "2023-12-07T22:52:30.272+0000",
        "alarmLevel": 745,
        "lowerAlarm": 122.78569655506827,
        "seniorAlarm": 360.83989690940206,
        "seniorSeniorAlarm": 88.57233620398543
      }
    },
    "deviceAlarmMap": {
      "key1": {
        "id": "主键53",
        "deviceId": "设备id82",
        "index": "指标76",
        "lowerAlarm": 169.5892772650081,
        "seniorAlarm": 483.8742406898694,
        "seniorSeniorAlarm": 208.50729958252828,
        "state": 816,
        "createTime": {
          "date": {
            "year": 289,
            "month": 46,
            "day": 54
          },
          "time": {
            "hour": 92,
            "minute": 87,
            "second": -11,
            "nano": 374
          }
        },
        "updateTime": {
          "date": {
            "year": 351,
            "month": 30,
            "day": 66
          },
          "time": {
            "hour": -121,
            "minute": 64,
            "second": 107,
            "nano": 838
          }
        },
        "createdByName": "创建人45",
        "updatedByName": "修改人6",
        "deleteFlag": 980,
        "comments": "备注42",
        "ignoreTime": "2023-12-08T01:57:50.845+0000"
      },
      "key2": {
        "id": "主键53",
        "deviceId": "设备id82",
        "index": "指标76",
        "lowerAlarm": 169.5892772650081,
        "seniorAlarm": 483.8742406898694,
        "seniorSeniorAlarm": 208.50729958252828,
        "state": 816,
        "createTime": {
          "date": {
            "year": 289,
            "month": 46,
            "day": 54
          },
          "time": {
            "hour": 92,
            "minute": 87,
            "second": -11,
            "nano": 374
          }
        },
        "updateTime": {
          "date": {
            "year": 351,
            "month": 30,
            "day": 66
          },
          "time": {
            "hour": -121,
            "minute": 64,
            "second": 107,
            "nano": 838
          }
        },
        "createdByName": "创建人45",
        "updatedByName": "修改人6",
        "deleteFlag": 980,
        "comments": "备注42",
        "ignoreTime": "2023-12-08T01:57:50.845+0000"
      }
    }
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceDataCache**    |   |   |
|└─ device     | **Device**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备父型号（device_type） |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceType     | **String**    |  设备子类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceCode     | **String**    |  设备编号 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |  更新时间 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdByName     | **String**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedByName     | **String**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|└─ deviceIndexLogVoMap     | **Map\<String, DeviceIndexLogVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceTypeCode     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ source     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ stringValue     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ time     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|└─ deviceAlarmMap     | **Map\<String, DeviceAlarm\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |  指标 |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |  低报 |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |  高报 |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |  高高报 |   |
|&ensp;&ensp;&ensp;&ensp;└─ state     | **Integer**    |  启停状态 |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **LocalDateTime**    |  创建时间 |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **LocalDateTime**    |  修改时间 |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ date     | **LocalDate**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ year     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ month     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ day     | **short**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ time     | **LocalTime**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ hour     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ minute     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ second     | **byte**    |   |   |
|&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;└─ nano     | **int**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ createdByName     | **String**    |  创建人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ updatedByName     | **String**    |  修改人 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|&ensp;&ensp;&ensp;&ensp;└─ comments     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |




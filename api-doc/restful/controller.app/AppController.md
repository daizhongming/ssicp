# 登录

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/login
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
  "name": "枕毕剖考客复惯个翱难谋涅遍栓棘",
  "password": "午罢绸布倒职抉氛杏肠瞪异"
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| name     | **String**     | **是**  |   |   |
| password     | **String**     | **是**  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": "风澈盟涕沧帮篇需浑衙荧庶颧挽悄时恢吊菌",
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **String**    |   |   |
| status     | **Object**    |   |   |



# 查询代办列表

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/todos?userName=绦斋芋判扎侵伦好畦佳
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:绦斋芋判扎侵伦好畦佳

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": "条哨厩洪骇碘仲演潘纠遥历痹灌解艳金爬妥",
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **String**    |   |   |
| status     | **Object**    |   |   |



# 查询消息

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/messages?userName=舱科儿金套隅
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:舱科儿金套隅

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": "番位宫窝漏捞疗套慌蝎",
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **String**    |   |   |
| status     | **Object**    |   |   |



# 消息已读

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/messages/read?userName=湾茅蔽监汾撤她渴均猫饥
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
  "allRead": 685,
  "messageId": 631,
  "userName": "吞抡筏雪颗糖涩改改"
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| readMessageParams     | **ReadMessageParams**     | 否  |   |   |
|└─ allRead     | **Integer**     | 否  |   |   |
|└─ messageId     | **Long**     | 否  |   |   |
|└─ userName     | **String**     | 否  |   |   |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": "弛纺撬竖燃锤蚂",
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **String**    |   |   |
| status     | **Object**    |   |   |



# 查询我的信息

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/me?userName=邀饵帮瞄淀漳减纬斋藐灾
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:邀饵帮瞄淀漳减纬斋藐灾

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "menuCodeList": [
      "迎铡呛挪笋",
      "胸欠肾邦岭敛符父枫"
    ],
    "buttonIdList": [
      "衰凉瀑裔人兰萝耶易壤霹荡割胯嫌伦",
      "燃肌劲决型巢铸型荔羚赞虱钝耕廷贮"
    ],
    "userVo": {
      "name": "搁抱庶尿丸",
      "password": "旦创背篙鹊痹玻诲",
      "nickName": "铂露团黔粤讼",
      "status": 540,
      "createTime": "2023-12-08T13:15:33.173+0000",
      "updateTime": "2023-12-08T15:35:01.423+0000",
      "roleName": "章措抨娘抛瞪果其陨惜刃督蚂刹淳酗鹏甜棺",
      "phone": "褒扳漠牲冷处锑曾以娱陷断升搐貌硼妊蔗靖",
      "systemFlag": 15,
      "roleTitle": "恋甩铰悄鲁笺曙括腰硷因痛",
      "isInspector": true,
      "isRoot": true
    }
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **LoginMessageVo**    |   |   |
|└─ menuCodeList     | **List\<String\>**    |   |   |
|└─ buttonIdList     | **List\<String\>**    |   |   |
|└─ userVo     | **UserVo**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ name     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ password     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ nickName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ status     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ roleName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ phone     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ systemFlag     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ roleTitle     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ isInspector     | **boolean**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ isRoot     | **boolean**    |   |   |
| status     | **Object**    |   |   |



# 查询产用汽统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/productionAndConsumption
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
    "menuCodeList": [
      "孔气粹统著彼庙嘎郎丁狞音忧檬加墓师",
      "律矣腊滔兴嘿"
    ],
    "buttonIdList": [
      "陶坞入佳秸井哇畜消",
      "捣复潞掳挝苍厘开纳柿拎"
    ],
    "userVo": {
      "name": "斟既使脖她巫汇朽剃匣蒂槛锨冕惮垮言腿",
      "password": "霞模岂绣暴涵烫滑澈",
      "nickName": "钢羌祟嘻刻凳厢涩玖颤石累终房匈鞍何懂",
      "status": 744,
      "createTime": "2023-12-08T08:34:07.672+0000",
      "updateTime": "2023-12-08T04:25:21.628+0000",
      "roleName": "罩急靛旺拟四虐带扣蛆竭钦鹅戳询统毕侈铂",
      "phone": "拈珍脾痕罗成沃嘘",
      "systemFlag": 455,
      "roleTitle": "九披断秤疫幽舱庞缉粳梧滴驭茹参",
      "isInspector": true,
      "isRoot": false
    }
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **LoginMessageVo**    |   |   |
|└─ menuCodeList     | **List\<String\>**    |   |   |
|└─ buttonIdList     | **List\<String\>**    |   |   |
|└─ userVo     | **UserVo**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ name     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ password     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ nickName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ status     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ createTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ updateTime     | **Date**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ roleName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ phone     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ systemFlag     | **Integer**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ roleTitle     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ isInspector     | **boolean**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ isRoot     | **boolean**    |   |   |
| status     | **Object**    |   |   |



# 消息统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/messages?userName=沸镶晚桑雁舞刘蓝火衫
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:沸镶晚桑雁舞刘蓝火衫

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "type": "拢锣赖疚垃幼饱火烙袭生簇喊俯洱镀滨庐",
      "unReadCount": 501,
      "readCount": 731
    },
    {
      "type": "拢锣赖疚垃幼饱火烙袭生簇喊俯洱镀滨庐",
      "unReadCount": 501,
      "readCount": 731
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<MessageAnalyseEntity\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ unReadCount     | **Integer**    |   |   |
|└─ readCount     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 指标报警统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/index/alarms
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
  "body": [
    {
      "type": "但筏狙屹凸右例闹匣咸翔略汝织",
      "count": 349
    },
    {
      "type": "但筏狙屹凸右例闹匣咸翔略汝织",
      "count": 349
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<AnalyseEntity\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ count     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 装置报警统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/device/alarms
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
  "body": [
    {
      "type": "府背扶漂沾哩宝织退雄管索改撑稀存卉",
      "count": 140
    },
    {
      "type": "府背扶漂沾哩宝织退雄管索改撑稀存卉",
      "count": 140
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<AnalyseEntity\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ count     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 设备个数统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/device/count
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
  "body": [
    {
      "type": "踌奢尖恩隆存疗凹亲苛个曲横算死茸",
      "count": 739
    },
    {
      "type": "踌奢尖恩隆存疗凹亲苛个曲横算死茸",
      "count": 739
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<AnalyseEntity\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ count     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 代办统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/todos?userName=鸦粱瘴癸嘿圃佣沛疡拈阜羽毛
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:鸦粱瘴癸嘿圃佣沛疡拈阜羽毛

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "type": "锚歪苹界迁杭酮蓄能恍拴哥馅符",
      "total": 386,
      "handled": 463,
      "unHandle": 948
    },
    {
      "type": "锚歪苹界迁杭酮蓄能恍拴哥馅符",
      "total": 386,
      "handled": 463,
      "unHandle": 948
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<TodoAnalyseEntity\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ total     | **Integer**    |   |   |
|└─ handled     | **Integer**    |   |   |
|└─ unHandle     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 上传文件

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/files
```

### 请求方法
```
POST
```

### 请求体类型
```
form-data
```

## 入参
### 入参示例 (Postman Bulk Edit)
```json
file:@"/扣调笺生糯登蝴牡客牛训镊闯螟遂臣伟奸泻"

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| file     | **MultipartFile**     | **是**  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "code": "悦粉钮皂兆秧豆汝考巩锑干希蔬距蒂",
    "name": "粘单绦坟瘸眩素",
    "size": 601,
    "type": "锹百疯紊读粱麦白卜杏违"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **FileInfo**    |   |   |
|└─ code     | **String**    |   |   |
|└─ name     | **String**    |   |   |
|└─ size     | **Long**    |   |   |
|└─ type     | **String**    |   |   |
| status     | **Object**    |   |   |



# 上传多文件

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/files/multi
```

### 请求方法
```
POST
```

### 请求体类型
```
application/x-www-form-urlencoded
```

## 入参
### 入参示例 (Postman Bulk Edit)
```json
files:@"/獭殷凰盎润劳芜狡央导吊滦今"

```



## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "code": "峨在脓仪孔膀宰哮融异航标屿屈忽拖碗疲坎",
      "name": "童冻刁骄涎炊寡",
      "size": 106,
      "type": "霹折待哟氧渊咬衫挑"
    },
    {
      "code": "峨在脓仪孔膀宰哮融异航标屿屈忽拖碗疲坎",
      "name": "童冻刁骄涎炊寡",
      "size": 106,
      "type": "霹折待哟氧渊咬衫挑"
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<FileInfo\>**    |   |   |
|└─ code     | **String**    |   |   |
|└─ name     | **String**    |   |   |
|└─ size     | **Long**    |   |   |
|└─ type     | **String**    |   |   |
| status     | **Object**    |   |   |



# 下载文件

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/files/{fileCode}?fileCode=拧蝗醇脐各尼络斥鸿求诉铃铱阑
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//fileCode:拧蝗醇脐各尼络斥鸿求诉铃铱阑

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| fileCode     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {},
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **Resource**    |   |   |
| status     | **Object**    |   |   |



# 历史指标趋势

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/deviceIndexLogs/indexLogsHistory?deviceId=榆蕊稍夫辙胆者烷&indexId=畅名盒舔涩余喜栖叶批洪崎嫉酋&startTime=2023-12-07 20:09:42
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//deviceId:榆蕊稍夫辙胆者烷
//indexId:畅名盒舔涩余喜栖叶批洪崎嫉酋
//startTime:2023-12-07 20:09:42

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| deviceId     | **String**     | 否  |   |   |
| indexId     | **String**     | 否  |   |   |
| startTime     | **Date**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "deviceId": "精轻坎智咒百庇洽躇库便峭挝蝴腊",
      "deviceTypeCode": "前叼荫屏浅召曼磅亲纽缸",
      "indexId": "诌烬底弥铬破荐窝祭踏挎必",
      "source": "赶瓣牡恼病氧土跨曝卿",
      "value": 240.99371847036826,
      "stringValue": "队淫入褪测辈锯赂绥馁狼盖掠望",
      "time": {
        "seconds": 852,
        "nanos": 618
      }
    },
    {
      "deviceId": "精轻坎智咒百庇洽躇库便峭挝蝴腊",
      "deviceTypeCode": "前叼荫屏浅召曼磅亲纽缸",
      "indexId": "诌烬底弥铬破荐窝祭踏挎必",
      "source": "赶瓣牡恼病氧土跨曝卿",
      "value": 240.99371847036826,
      "stringValue": "队淫入褪测辈锯赂绥馁狼盖掠望",
      "time": {
        "seconds": 852,
        "nanos": 618
      }
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<DeviceIndexLog\>**    |   |   |
|└─ deviceId     | **String**    |   |   |
|└─ deviceTypeCode     | **String**    |   |   |
|└─ indexId     | **String**    |   |   |
|└─ source     | **String**    |   |   |
|└─ value     | **Double**    |   |   |
|└─ stringValue     | **String**    |   |   |
|└─ time     | **Instant**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seconds     | **long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ nanos     | **int**    |   |   |
| status     | **Object**    |   |   |



# 处理

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/alarmHandle/handleStatus?userName=筷虽蛋妨疲筷迄宙档动颧涣呕&userNickName=澳尹凤争琅科啤胡貌雨称螺谨虱瘴亏闸傲先
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
  "deviceId": "菌劫坡李晤象诫闷抑癌",
  "indexId": "讳叫涟铸笆吊蜀撇缸绝谢尚晴襟合",
  "handleStatus": 372,
  "alarmReason": "文蹲烷碧侠士们",
  "ignoreTime": "2023-12-08T05:24:45.554+0000",
  "handleDescription": "逢娄魂械拴",
  "handleUserName": "起但教寺菱胜曳秒粪晋十距坎侯箩框匙浪",
  "handleUserNickName": "巴迅病耽盖调镍此怀通臆辑减糖某呢溃校",
  "fileList": "截岩斩茬渝照鸵郸授嫡备护",
  "fileListBefore": "委哲输屋奶莱倒臂发",
  "alarmActionInfoId": "韭歇毒魔早舅藤踌国"
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| deviceAlarmLogHandleRequest     | **DeviceAlarmLogHandleRequest**     | 否  |   |   |
|└─ deviceId     | **String**     | 否  |   |   |
|└─ indexId     | **String**     | 否  |   |   |
|└─ handleStatus     | **Integer**     | 否  |   |   |
|└─ alarmReason     | **String**     | **是**  |   |   |
|└─ ignoreTime     | **Date**     | 否  |   |   |
|└─ handleDescription     | **String**     | 否  |   |   |
|└─ handleUserName     | **String**     | 否  |   |   |
|└─ handleUserNickName     | **String**     | 否  |   |   |
|└─ fileList     | **String**     | 否  |   |   |
|└─ fileListBefore     | **String**     | 否  |   |   |
|└─ alarmActionInfoId     | **String**     | 否  |   |   |
| userName     | **String**     | 否  |   |   |
| userNickName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "deviceId": "戚蓉纪栋哉登然啼疑泌您菊",
      "deviceTypeCode": "蝗隔羊艺物虽诛碳拱睬模狮厚竞吁老俺",
      "indexId": "擅饰世绣驶捣求唾雾烯巩躬皇抡辗",
      "source": "林王挪跪生昼皿亦琼骏痘雀技麓让",
      "value": 677.2861111007905,
      "stringValue": "香垛责辱芳腿卉桨柔洼瞳",
      "time": {
        "seconds": 265,
        "nanos": 336
      }
    },
    {
      "deviceId": "戚蓉纪栋哉登然啼疑泌您菊",
      "deviceTypeCode": "蝗隔羊艺物虽诛碳拱睬模狮厚竞吁老俺",
      "indexId": "擅饰世绣驶捣求唾雾烯巩躬皇抡辗",
      "source": "林王挪跪生昼皿亦琼骏痘雀技麓让",
      "value": 677.2861111007905,
      "stringValue": "香垛责辱芳腿卉桨柔洼瞳",
      "time": {
        "seconds": 265,
        "nanos": 336
      }
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<DeviceIndexLog\>**    |   |   |
|└─ deviceId     | **String**    |   |   |
|└─ deviceTypeCode     | **String**    |   |   |
|└─ indexId     | **String**    |   |   |
|└─ source     | **String**    |   |   |
|└─ value     | **Double**    |   |   |
|└─ stringValue     | **String**    |   |   |
|└─ time     | **Instant**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seconds     | **long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ nanos     | **int**    |   |   |
| status     | **Object**    |   |   |



# 实时监测数据

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/deviceIndexLogs
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
  "body": [
    {
      "deviceType": "欢将扬舵疯颓胡哭厉",
      "deviceName": "师帆脊嗓培饥歼丢须模湍藩箱棉",
      "source": "肇刻播蔑涅砚匈盗人滚唁臀赏拆踏础葛",
      "indexValue": 620.6107051529582,
      "createTime": "2023-12-08T01:19:04.116+0000",
      "alarmFlag": 810,
      "alarmLevel": 197,
      "handleFlag": 258,
      "handleTime": "2023-12-07T21:27:14.004+0000",
      "indexId": "放貌通眼徘材困茵挣娃听潮脊涛敌煤冗岔",
      "deviceId": "旁坊织平究晴"
    },
    {
      "deviceType": "欢将扬舵疯颓胡哭厉",
      "deviceName": "师帆脊嗓培饥歼丢须模湍藩箱棉",
      "source": "肇刻播蔑涅砚匈盗人滚唁臀赏拆踏础葛",
      "indexValue": 620.6107051529582,
      "createTime": "2023-12-08T01:19:04.116+0000",
      "alarmFlag": 810,
      "alarmLevel": 197,
      "handleFlag": 258,
      "handleTime": "2023-12-07T21:27:14.004+0000",
      "indexId": "放貌通眼徘材困茵挣娃听潮脊涛敌煤冗岔",
      "deviceId": "旁坊织平究晴"
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<DeviceIndexLogVo\>**    |   |   |
|└─ deviceType     | **String**    |   |   |
|└─ deviceName     | **String**    |   |   |
|└─ source     | **String**    |   |   |
|└─ indexValue     | **Double**    |   |   |
|└─ createTime     | **Date**    |   |   |
|└─ alarmFlag     | **Integer**    |   |   |
|└─ alarmLevel     | **Integer**    |   |   |
|└─ handleFlag     | **Integer**    |   |   |
|└─ handleTime     | **Date**    |   |   |
|└─ indexId     | **String**    |   |   |
|└─ deviceId     | **String**    |   |   |
| status     | **Object**    |   |   |



# 获取最近的指标报警处理记录

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/alarmHandle/data?deviceId=舰铲砰丹恫蓝霖窘绷致滤移钧馈敌花&indexId=嗓卿主刻迪溃虹军霖涸沧竹蔷恶话犬时陇粱
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//deviceId:舰铲砰丹恫蓝霖窘绷致滤移钧馈敌花
//indexId:嗓卿主刻迪溃虹军霖涸沧竹蔷恶话犬时陇粱

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| deviceId     | **String**     | 否  |   |   |
| indexId     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": 946,
    "deviceId": "为陡口藤傻筏晃令补虑拎怠棵紊厨",
    "deviceName": "倡赢蔬韭晋娥虏斥响币仙赐拥悠程踢劣绒",
    "deviceType": "法垢呆阴敌狼敝树懊弟芋株读御循捆偏年傈",
    "deviceTypeName": "秃涤友琶拔睁",
    "indexId": "疚殖唬伙面疟荷峭使",
    "indexName": "贝凳膜河蹋坷份谐膜尺敌",
    "alarmValue": 710.3872716674673,
    "alarmLevel": 783,
    "startTime": "2023-12-08T05:08:57.604+0000",
    "handleStatus": 1000,
    "handleTime": "2023-12-08T09:00:11.790+0000",
    "handleUserName": "娜垣慨垣燥咆捎佯猎弧殿揭问笔矢避鹅荔",
    "handleUserNickName": "叮痹痉扎锌食靶栏硬酣纤阔媳丸评",
    "handleCostTime": 373,
    "unit": "帕扭娥极馒伐呛砧稿",
    "lowerAlarm": 673.3975256539683,
    "seniorAlarm": 609.888261693389,
    "seniorSeniorAlarm": 962.8158991235509,
    "alarmTimes": 767,
    "alarmReason": "懦泣瞩咆镭姑验脾滨答脆漆衫锚凌",
    "handleDescription": "活拂喻劳混",
    "fileList": "寡琅鸯强群钮",
    "fileListBefore": "寅匆乍搏某耕停僳朝菌",
    "ignoreTime": "2023-12-08T11:28:39.456+0000"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **DeviceAlarmLogVo**    |   |   |
|└─ id     | **Long**    |   |   |
|└─ deviceId     | **String**    |   |   |
|└─ deviceName     | **String**    |   |   |
|└─ deviceType     | **String**    |   |   |
|└─ deviceTypeName     | **String**    |   |   |
|└─ indexId     | **String**    |   |   |
|└─ indexName     | **String**    |   |   |
|└─ alarmValue     | **Double**    |   |   |
|└─ alarmLevel     | **Integer**    |   |   |
|└─ startTime     | **Date**    |   |   |
|└─ handleStatus     | **Integer**    |   |   |
|└─ handleTime     | **Date**    |   |   |
|└─ handleUserName     | **String**    |   |   |
|└─ handleUserNickName     | **String**    |   |   |
|└─ handleCostTime     | **Long**    |   |   |
|└─ unit     | **String**    |   |   |
|└─ lowerAlarm     | **Double**    |   |   |
|└─ seniorAlarm     | **Double**    |   |   |
|└─ seniorSeniorAlarm     | **Double**    |   |   |
|└─ alarmTimes     | **Integer**    |   |   |
|└─ alarmReason     | **String**    |   |   |
|└─ handleDescription     | **String**    |   |   |
|└─ fileList     | **String**    |   |   |
|└─ fileListBefore     | **String**    |   |   |
|└─ ignoreTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# 报警详情

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/alarmHandle/alarmDetails?deviceId=鹿啦升劣驳吭淡块&indexId=棱惕翁磷碉况旁缘胁报壕粮盎糙论&handleTime=2023-12-08 06:10:32
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//deviceId:鹿啦升劣驳吭淡块
//indexId:棱惕翁磷碉况旁缘胁报壕粮盎糙论
//handleTime:2023-12-08 06:10:32

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| deviceId     | **String**     | 否  |   |   |
| indexId     | **String**     | 否  |   |   |
| handleTime     | **Date**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "indexId": "练相帧斥梗卸丰成库酗消",
      "indexName": "秉钎肛糊佛镐赖景隙贯",
      "alarmValue": 676.218732232141,
      "unit": "透沪团肩炕返克瞩徐菏往事侯尽",
      "alarmLevel": 390,
      "lowerAlarm": 371.3923345816429,
      "seniorAlarm": 490.892655975778,
      "seniorSeniorAlarm": 108.19058574143241,
      "startTime": "2023-12-07T18:12:03.809+0000"
    },
    {
      "indexId": "练相帧斥梗卸丰成库酗消",
      "indexName": "秉钎肛糊佛镐赖景隙贯",
      "alarmValue": 676.218732232141,
      "unit": "透沪团肩炕返克瞩徐菏往事侯尽",
      "alarmLevel": 390,
      "lowerAlarm": 371.3923345816429,
      "seniorAlarm": 490.892655975778,
      "seniorSeniorAlarm": 108.19058574143241,
      "startTime": "2023-12-07T18:12:03.809+0000"
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<DeviceAlarmLogAlarmDetailsVo\>**    |   |   |
|└─ indexId     | **String**    |   |   |
|└─ indexName     | **String**    |   |   |
|└─ alarmValue     | **Double**    |   |   |
|└─ unit     | **String**    |   |   |
|└─ alarmLevel     | **Integer**    |   |   |
|└─ lowerAlarm     | **Double**    |   |   |
|└─ seniorAlarm     | **Double**    |   |   |
|└─ seniorSeniorAlarm     | **Double**    |   |   |
|└─ startTime     | **Date**    |   |   |
| status     | **Object**    |   |   |



# 近一个月人工巡检统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/inspection/manual/month
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
    "total": 953,
    "UndoCount": 839,
    "expireCount": 628,
    "completeCount": 808,
    "errorUnHandleCount": 772,
    "errorHandledCount": 656
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **AnalyseManualInspection**    |   |   |
|└─ total     | **Integer**    |   |   |
|└─ UndoCount     | **Integer**    |   |   |
|└─ expireCount     | **Integer**    |   |   |
|└─ completeCount     | **Integer**    |   |   |
|└─ errorUnHandleCount     | **Integer**    |   |   |
|└─ errorHandledCount     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 所有人工巡检统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/inspection/manual/all
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
    "total": 181,
    "UndoCount": 776,
    "expireCount": 173,
    "completeCount": 185,
    "errorUnHandleCount": 496,
    "errorHandledCount": 637
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **AnalyseManualInspection**    |   |   |
|└─ total     | **Integer**    |   |   |
|└─ UndoCount     | **Integer**    |   |   |
|└─ expireCount     | **Integer**    |   |   |
|└─ completeCount     | **Integer**    |   |   |
|└─ errorUnHandleCount     | **Integer**    |   |   |
|└─ errorHandledCount     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 巡检统计

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/statistics/inspection
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
  "body": [
    {
      "type": "谁梦挺动哗芝耍樱船己侈浙寄都碱琶",
      "total": 750,
      "errorCount": 394
    },
    {
      "type": "谁梦挺动哗芝耍樱船己侈浙寄都碱琶",
      "total": 750,
      "errorCount": 394
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<AnalyseInspection\>**    |   |   |
|└─ type     | **String**    |   |   |
|└─ total     | **Integer**    |   |   |
|└─ errorCount     | **Integer**    |   |   |
| status     | **Object**    |   |   |



# 最近半月智能巡检

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/inspection/halfMonth?userName=鹤铃恨功荒拜
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:鹤铃恨功荒拜

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "outcomeCode": "巡检结果编号15",
      "planCode": "巡检计划编号21",
      "planName": "地己派茧末邪图缮离危贺痈域沼摊广媒",
      "iterationNumber": 835,
      "state": 866,
      "responsibleName": "负责人名称17",
      "createTime": "2023-12-08T07:36:50.454+0000",
      "updateTime": "2023-12-08T07:27:51.374+0000",
      "deleteFlag": 560,
      "comments": "备注32",
      "nickName": "杖噬颇蹭耸柴段宅甄竿挨烯闽芦话末千淤",
      "instrumentCount": 79,
      "steamTrapsCount": 960,
      "valveCount": 496,
      "pipeCount": 873,
      "steamProductionPointCount": 423,
      "withSteamSpotsCount": 95,
      "details": [
        {
          "id": 126,
          "index": "网腰戈褂碰摧蛙砸漂扮",
          "detailsId": "攻玖万炭罩辕忠毫鸟",
          "indexValue": 813.0195749017831,
          "alarmLevel": "斌渗宴山圃栖育娃土哼闯咸谁伸存用嘱",
          "lowerAlarm": 699.8425632239413,
          "seniorAlarm": 361.36571671096175,
          "seniorSeniorAlarm": 459.49794748945357,
          "parentDeviceType": "岭荒祟握垦必俏形赌",
          "deviceId": "赌枯谚疚冗",
          "deviceName": "帖但耽牌障祷配寇僳甥策碉嗅累瘤诫须瘸黑",
          "resolutionReason": "原因2",
          "summary": "简述40",
          "remarks": "备注106",
          "fileListStr": "处理后图片21",
          "fileList": [
            "处理后图片68",
            "处理后图片10"
          ],
          "fileListBeforeStr": "处理前图片18",
          "fileListBefore": [
            "处理前图片127",
            "处理前图片24"
          ]
        },
        {
          "id": 126,
          "index": "网腰戈褂碰摧蛙砸漂扮",
          "detailsId": "攻玖万炭罩辕忠毫鸟",
          "indexValue": 813.0195749017831,
          "alarmLevel": "斌渗宴山圃栖育娃土哼闯咸谁伸存用嘱",
          "lowerAlarm": 699.8425632239413,
          "seniorAlarm": 361.36571671096175,
          "seniorSeniorAlarm": 459.49794748945357,
          "parentDeviceType": "岭荒祟握垦必俏形赌",
          "deviceId": "赌枯谚疚冗",
          "deviceName": "帖但耽牌障祷配寇僳甥策碉嗅累瘤诫须瘸黑",
          "resolutionReason": "原因2",
          "summary": "简述40",
          "remarks": "备注106",
          "fileListStr": "处理后图片21",
          "fileList": [
            "处理后图片68",
            "处理后图片10"
          ],
          "fileListBeforeStr": "处理前图片18",
          "fileListBefore": [
            "处理前图片127",
            "处理前图片24"
          ]
        }
      ],
      "responsibleNickName": "隅崇敬歇派祸扔炙艰众苗新闰",
      "intelligentOutcomeDetailsVos": [
        {
          "id": 554,
          "index": "戚妄路乍败筒显塘",
          "detailsId": "情穿证衍癌焉属噶箱衅",
          "indexValue": 897.5763522114561,
          "alarmLevel": "尤氢辉甚崖香刷柜率舔凶截欧使",
          "lowerAlarm": 238.3621051494637,
          "seniorAlarm": 907.4430009587535,
          "seniorSeniorAlarm": 884.0694130467542,
          "parentDeviceType": "暴本彩邑咕跌遮狱硬泪憋堂然兢涟凳",
          "deviceId": "缘魄葛窖斗驾袭姥烹炒龟馏怕违",
          "deviceName": "验赎架常萄宾嘎颁瘫秸弊条芦嗅胳",
          "resolutionReason": "原因2",
          "summary": "简述114",
          "remarks": "备注117",
          "fileListStr": "处理后图片6",
          "fileList": [
            "处理后图片47",
            "处理后图片74"
          ],
          "fileListBeforeStr": "处理前图片109",
          "fileListBefore": [
            "处理前图片99",
            "处理前图片13"
          ]
        },
        {
          "id": 554,
          "index": "戚妄路乍败筒显塘",
          "detailsId": "情穿证衍癌焉属噶箱衅",
          "indexValue": 897.5763522114561,
          "alarmLevel": "尤氢辉甚崖香刷柜率舔凶截欧使",
          "lowerAlarm": 238.3621051494637,
          "seniorAlarm": 907.4430009587535,
          "seniorSeniorAlarm": 884.0694130467542,
          "parentDeviceType": "暴本彩邑咕跌遮狱硬泪憋堂然兢涟凳",
          "deviceId": "缘魄葛窖斗驾袭姥烹炒龟馏怕违",
          "deviceName": "验赎架常萄宾嘎颁瘫秸弊条芦嗅胳",
          "resolutionReason": "原因2",
          "summary": "简述114",
          "remarks": "备注117",
          "fileListStr": "处理后图片6",
          "fileList": [
            "处理后图片47",
            "处理后图片74"
          ],
          "fileListBeforeStr": "处理前图片109",
          "fileListBefore": [
            "处理前图片99",
            "处理前图片13"
          ]
        }
      ]
    },
    {
      "outcomeCode": "巡检结果编号15",
      "planCode": "巡检计划编号21",
      "planName": "地己派茧末邪图缮离危贺痈域沼摊广媒",
      "iterationNumber": 835,
      "state": 866,
      "responsibleName": "负责人名称17",
      "createTime": "2023-12-08T07:36:50.454+0000",
      "updateTime": "2023-12-08T07:27:51.374+0000",
      "deleteFlag": 560,
      "comments": "备注32",
      "nickName": "杖噬颇蹭耸柴段宅甄竿挨烯闽芦话末千淤",
      "instrumentCount": 79,
      "steamTrapsCount": 960,
      "valveCount": 496,
      "pipeCount": 873,
      "steamProductionPointCount": 423,
      "withSteamSpotsCount": 95,
      "details": [
        {
          "id": 126,
          "index": "网腰戈褂碰摧蛙砸漂扮",
          "detailsId": "攻玖万炭罩辕忠毫鸟",
          "indexValue": 813.0195749017831,
          "alarmLevel": "斌渗宴山圃栖育娃土哼闯咸谁伸存用嘱",
          "lowerAlarm": 699.8425632239413,
          "seniorAlarm": 361.36571671096175,
          "seniorSeniorAlarm": 459.49794748945357,
          "parentDeviceType": "岭荒祟握垦必俏形赌",
          "deviceId": "赌枯谚疚冗",
          "deviceName": "帖但耽牌障祷配寇僳甥策碉嗅累瘤诫须瘸黑",
          "resolutionReason": "原因2",
          "summary": "简述40",
          "remarks": "备注106",
          "fileListStr": "处理后图片21",
          "fileList": [
            "处理后图片68",
            "处理后图片10"
          ],
          "fileListBeforeStr": "处理前图片18",
          "fileListBefore": [
            "处理前图片127",
            "处理前图片24"
          ]
        },
        {
          "id": 126,
          "index": "网腰戈褂碰摧蛙砸漂扮",
          "detailsId": "攻玖万炭罩辕忠毫鸟",
          "indexValue": 813.0195749017831,
          "alarmLevel": "斌渗宴山圃栖育娃土哼闯咸谁伸存用嘱",
          "lowerAlarm": 699.8425632239413,
          "seniorAlarm": 361.36571671096175,
          "seniorSeniorAlarm": 459.49794748945357,
          "parentDeviceType": "岭荒祟握垦必俏形赌",
          "deviceId": "赌枯谚疚冗",
          "deviceName": "帖但耽牌障祷配寇僳甥策碉嗅累瘤诫须瘸黑",
          "resolutionReason": "原因2",
          "summary": "简述40",
          "remarks": "备注106",
          "fileListStr": "处理后图片21",
          "fileList": [
            "处理后图片68",
            "处理后图片10"
          ],
          "fileListBeforeStr": "处理前图片18",
          "fileListBefore": [
            "处理前图片127",
            "处理前图片24"
          ]
        }
      ],
      "responsibleNickName": "隅崇敬歇派祸扔炙艰众苗新闰",
      "intelligentOutcomeDetailsVos": [
        {
          "id": 554,
          "index": "戚妄路乍败筒显塘",
          "detailsId": "情穿证衍癌焉属噶箱衅",
          "indexValue": 897.5763522114561,
          "alarmLevel": "尤氢辉甚崖香刷柜率舔凶截欧使",
          "lowerAlarm": 238.3621051494637,
          "seniorAlarm": 907.4430009587535,
          "seniorSeniorAlarm": 884.0694130467542,
          "parentDeviceType": "暴本彩邑咕跌遮狱硬泪憋堂然兢涟凳",
          "deviceId": "缘魄葛窖斗驾袭姥烹炒龟馏怕违",
          "deviceName": "验赎架常萄宾嘎颁瘫秸弊条芦嗅胳",
          "resolutionReason": "原因2",
          "summary": "简述114",
          "remarks": "备注117",
          "fileListStr": "处理后图片6",
          "fileList": [
            "处理后图片47",
            "处理后图片74"
          ],
          "fileListBeforeStr": "处理前图片109",
          "fileListBefore": [
            "处理前图片99",
            "处理前图片13"
          ]
        },
        {
          "id": 554,
          "index": "戚妄路乍败筒显塘",
          "detailsId": "情穿证衍癌焉属噶箱衅",
          "indexValue": 897.5763522114561,
          "alarmLevel": "尤氢辉甚崖香刷柜率舔凶截欧使",
          "lowerAlarm": 238.3621051494637,
          "seniorAlarm": 907.4430009587535,
          "seniorSeniorAlarm": 884.0694130467542,
          "parentDeviceType": "暴本彩邑咕跌遮狱硬泪憋堂然兢涟凳",
          "deviceId": "缘魄葛窖斗驾袭姥烹炒龟馏怕违",
          "deviceName": "验赎架常萄宾嘎颁瘫秸弊条芦嗅胳",
          "resolutionReason": "原因2",
          "summary": "简述114",
          "remarks": "备注117",
          "fileListStr": "处理后图片6",
          "fileList": [
            "处理后图片47",
            "处理后图片74"
          ],
          "fileListBeforeStr": "处理前图片109",
          "fileListBefore": [
            "处理前图片99",
            "处理前图片13"
          ]
        }
      ]
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<IntelligentInspectionOutcomeAppVo\>**    |   |   |
|└─ outcomeCode     | **String**    |  巡检结果编号 |   |
|└─ planCode     | **String**    |  巡检计划编号 |   |
|└─ planName     | **String**    |   |   |
|└─ iterationNumber     | **int**    |  巡检次数 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理） |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ nickName     | **String**    |   |   |
|└─ instrumentCount     | **Integer**    |   |   |
|└─ steamTrapsCount     | **Integer**    |   |   |
|└─ valveCount     | **Integer**    |   |   |
|└─ pipeCount     | **Integer**    |   |   |
|└─ steamProductionPointCount     | **Integer**    |   |   |
|└─ withSteamSpotsCount     | **Integer**    |   |   |
|└─ details     | **Map\<String, Map\<String, List\<IntelligentOutcomeDetailsVo\>\>\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ intelligentOutcomeDetailsVos     | **List\<IntelligentOutcomeDetailsVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
| status     | **Object**    |   |   |



# 最近半月人工能巡检

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/manualInspection/halfMonth?userName=余迷程铰坚描纠刀苇描势陶瞪可吱
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//userName:余迷程铰坚描纠刀苇描势陶瞪可吱

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| userName     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": [
    {
      "outcomeCode": "实绩编号95",
      "planCode": "计划编号36",
      "planName": "巡检计划名称67",
      "state": 455,
      "createTime": "2023-12-08T06:52:47.699+0000",
      "dueDate": "2023-12-08T14:35:43.029+0000",
      "updateTime": "2023-12-08T12:10:13.199+0000",
      "iterationNumber": 107,
      "responsibleName": "负责人名称85",
      "responsibleNickName": "呈况戒世鲁耽",
      "manualOutcomeDetails": [
        {
          "id": "主键106",
          "indexValue": 776.0689212482118,
          "index": "指标70",
          "alarmLevel": 450,
          "deviceId": "设备id3",
          "deviceName": "设备名称123",
          "infoId": "处理id58",
          "outcomeCode": "实绩id97",
          "parentDeviceType": "设备类型19",
          "resolutionReason": "原因118",
          "summary": "简述29",
          "remarks": "备注95",
          "fileListStr": "处理后图片115",
          "fileList": [
            "处理后图片46",
            "处理后图片30"
          ],
          "fileListBeforeStr": "处理前图片65",
          "fileListBefore": [
            "处理前图片104",
            "处理前图片61"
          ]
        },
        {
          "id": "主键106",
          "indexValue": 776.0689212482118,
          "index": "指标70",
          "alarmLevel": 450,
          "deviceId": "设备id3",
          "deviceName": "设备名称123",
          "infoId": "处理id58",
          "outcomeCode": "实绩id97",
          "parentDeviceType": "设备类型19",
          "resolutionReason": "原因118",
          "summary": "简述29",
          "remarks": "备注95",
          "fileListStr": "处理后图片115",
          "fileList": [
            "处理后图片46",
            "处理后图片30"
          ],
          "fileListBeforeStr": "处理前图片65",
          "fileListBefore": [
            "处理前图片104",
            "处理前图片61"
          ]
        }
      ],
      "manualOutcomeImages": [
        {
          "id": "主键28",
          "deviceId": "设备id79",
          "deviceName": "设备名称15",
          "infoId": "处理id9",
          "outcomeCode": "实绩id72",
          "fileList": "图片50",
          "parentDeviceType": "设备类型5"
        },
        {
          "id": "主键28",
          "deviceId": "设备id79",
          "deviceName": "设备名称15",
          "infoId": "处理id9",
          "outcomeCode": "实绩id72",
          "fileList": "图片50",
          "parentDeviceType": "设备类型5"
        }
      ],
      "manualOutcomeObserves": [
        {
          "id": "id59",
          "decide": 312,
          "decideDescription": "观察项描述79",
          "deviceId": "设备id66",
          "deviceName": "设备名称91",
          "infoId": "处理id34",
          "outcomeCode": "实绩id19",
          "parentDeviceType": "设备类型109",
          "parentDeviceTypeName": "蔽摆锦坟白妖导绞纯存缉陌",
          "resolutionReason": "原因3",
          "summary": "简述117",
          "remarks": "备注10",
          "fileListStr": "处理后图片5",
          "fileList": [
            "处理后图片44",
            "处理后图片44"
          ],
          "fileListBeforeStr": "处理前图片47",
          "fileListBefore": [
            "处理前图片12",
            "处理前图片7"
          ]
        },
        {
          "id": "id59",
          "decide": 312,
          "decideDescription": "观察项描述79",
          "deviceId": "设备id66",
          "deviceName": "设备名称91",
          "infoId": "处理id34",
          "outcomeCode": "实绩id19",
          "parentDeviceType": "设备类型109",
          "parentDeviceTypeName": "蔽摆锦坟白妖导绞纯存缉陌",
          "resolutionReason": "原因3",
          "summary": "简述117",
          "remarks": "备注10",
          "fileListStr": "处理后图片5",
          "fileList": [
            "处理后图片44",
            "处理后图片44"
          ],
          "fileListBeforeStr": "处理前图片47",
          "fileListBefore": [
            "处理前图片12",
            "处理前图片7"
          ]
        }
      ],
      "inspectionType": "攫头苟淫验拜",
      "inspectionTypeName": "勤之孪歇泽生刚洁"
    },
    {
      "outcomeCode": "实绩编号95",
      "planCode": "计划编号36",
      "planName": "巡检计划名称67",
      "state": 455,
      "createTime": "2023-12-08T06:52:47.699+0000",
      "dueDate": "2023-12-08T14:35:43.029+0000",
      "updateTime": "2023-12-08T12:10:13.199+0000",
      "iterationNumber": 107,
      "responsibleName": "负责人名称85",
      "responsibleNickName": "呈况戒世鲁耽",
      "manualOutcomeDetails": [
        {
          "id": "主键106",
          "indexValue": 776.0689212482118,
          "index": "指标70",
          "alarmLevel": 450,
          "deviceId": "设备id3",
          "deviceName": "设备名称123",
          "infoId": "处理id58",
          "outcomeCode": "实绩id97",
          "parentDeviceType": "设备类型19",
          "resolutionReason": "原因118",
          "summary": "简述29",
          "remarks": "备注95",
          "fileListStr": "处理后图片115",
          "fileList": [
            "处理后图片46",
            "处理后图片30"
          ],
          "fileListBeforeStr": "处理前图片65",
          "fileListBefore": [
            "处理前图片104",
            "处理前图片61"
          ]
        },
        {
          "id": "主键106",
          "indexValue": 776.0689212482118,
          "index": "指标70",
          "alarmLevel": 450,
          "deviceId": "设备id3",
          "deviceName": "设备名称123",
          "infoId": "处理id58",
          "outcomeCode": "实绩id97",
          "parentDeviceType": "设备类型19",
          "resolutionReason": "原因118",
          "summary": "简述29",
          "remarks": "备注95",
          "fileListStr": "处理后图片115",
          "fileList": [
            "处理后图片46",
            "处理后图片30"
          ],
          "fileListBeforeStr": "处理前图片65",
          "fileListBefore": [
            "处理前图片104",
            "处理前图片61"
          ]
        }
      ],
      "manualOutcomeImages": [
        {
          "id": "主键28",
          "deviceId": "设备id79",
          "deviceName": "设备名称15",
          "infoId": "处理id9",
          "outcomeCode": "实绩id72",
          "fileList": "图片50",
          "parentDeviceType": "设备类型5"
        },
        {
          "id": "主键28",
          "deviceId": "设备id79",
          "deviceName": "设备名称15",
          "infoId": "处理id9",
          "outcomeCode": "实绩id72",
          "fileList": "图片50",
          "parentDeviceType": "设备类型5"
        }
      ],
      "manualOutcomeObserves": [
        {
          "id": "id59",
          "decide": 312,
          "decideDescription": "观察项描述79",
          "deviceId": "设备id66",
          "deviceName": "设备名称91",
          "infoId": "处理id34",
          "outcomeCode": "实绩id19",
          "parentDeviceType": "设备类型109",
          "parentDeviceTypeName": "蔽摆锦坟白妖导绞纯存缉陌",
          "resolutionReason": "原因3",
          "summary": "简述117",
          "remarks": "备注10",
          "fileListStr": "处理后图片5",
          "fileList": [
            "处理后图片44",
            "处理后图片44"
          ],
          "fileListBeforeStr": "处理前图片47",
          "fileListBefore": [
            "处理前图片12",
            "处理前图片7"
          ]
        },
        {
          "id": "id59",
          "decide": 312,
          "decideDescription": "观察项描述79",
          "deviceId": "设备id66",
          "deviceName": "设备名称91",
          "infoId": "处理id34",
          "outcomeCode": "实绩id19",
          "parentDeviceType": "设备类型109",
          "parentDeviceTypeName": "蔽摆锦坟白妖导绞纯存缉陌",
          "resolutionReason": "原因3",
          "summary": "简述117",
          "remarks": "备注10",
          "fileListStr": "处理后图片5",
          "fileList": [
            "处理后图片44",
            "处理后图片44"
          ],
          "fileListBeforeStr": "处理前图片47",
          "fileListBefore": [
            "处理前图片12",
            "处理前图片7"
          ]
        }
      ],
      "inspectionType": "攫头苟淫验拜",
      "inspectionTypeName": "勤之孪歇泽生刚洁"
    }
  ],
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **List\<ManualInspectionOutcomeAppVo\>**    |   |   |
|└─ outcomeCode     | **String**    |  实绩编号 |   |
|└─ planCode     | **String**    |  计划编号 |   |
|└─ planName     | **String**    |  巡检计划名称 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理 3.未做） |   |
|└─ createTime     | **Date**    |  完成时间 |   |
|└─ dueDate     | **Date**    |  截至时间 |   |
|└─ updateTime     | **Date**    |  修改时间（处理完成时间） |   |
|└─ iterationNumber     | **Integer**    |  巡检次数 |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ manualOutcomeDetails     | **List\<ManualOutcomeDetailsWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |  指标值 |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |  指标 |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |  报警级别(alarm_level) |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ manualOutcomeImages     | **List\<ManualOutcomeImage\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |  图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|└─ manualOutcomeObserves     | **List\<ManualOutcomeObserveWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  id |   |
|&ensp;&ensp;&ensp;&ensp;└─ decide     | **Integer**    |  判定(0.正常 1.异常) |   |
|&ensp;&ensp;&ensp;&ensp;└─ decideDescription     | **String**    |  观察项描述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ inspectionType     | **String**    |   |   |
|└─ inspectionTypeName     | **String**    |   |   |
| status     | **Object**    |   |   |



# 查询智能巡检结果详情

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/inspection/outcome/{outcomeCode}?outcomeCode=疮概划澎抡幻天
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//outcomeCode:疮概划澎抡幻天

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| outcomeCode     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "outcomeCode": "巡检结果编号75",
    "planCode": "巡检计划编号86",
    "planName": "贵潜宣某燃",
    "iterationNumber": 781,
    "state": 1020,
    "responsibleName": "负责人名称89",
    "createTime": "2023-12-08T01:39:03.508+0000",
    "updateTime": "2023-12-07T17:23:59.338+0000",
    "deleteFlag": 320,
    "comments": "备注41",
    "nickName": "西容赛天盟卞荤脾糠缉妨嫉桂卤概",
    "instrumentCount": 855,
    "steamTrapsCount": 474,
    "valveCount": 605,
    "pipeCount": 28,
    "steamProductionPointCount": 269,
    "withSteamSpotsCount": 923,
    "details": [
      {
        "id": 204,
        "index": "吼下摩肌逛拇饯簇笨雀苍控维抹婶粹",
        "detailsId": "搔赠涤千凿梨猛知岗",
        "indexValue": 178.292108478742,
        "alarmLevel": "周啡哑辖升抗织姜滴拼续润杉釜揭鲍凉",
        "lowerAlarm": 717.1568074103799,
        "seniorAlarm": 614.7003427597335,
        "seniorSeniorAlarm": 281.6216142734439,
        "parentDeviceType": "纬脓姐速是",
        "deviceId": "篡姻硕砾悯刻瑚游体材泊植绣吩",
        "deviceName": "道挚坯初阮娇胺吸垂谰哉友庶簇",
        "resolutionReason": "原因104",
        "summary": "简述89",
        "remarks": "备注40",
        "fileListStr": "处理后图片17",
        "fileList": [
          "处理后图片124",
          "处理后图片13"
        ],
        "fileListBeforeStr": "处理前图片125",
        "fileListBefore": [
          "处理前图片123",
          "处理前图片7"
        ]
      },
      {
        "id": 204,
        "index": "吼下摩肌逛拇饯簇笨雀苍控维抹婶粹",
        "detailsId": "搔赠涤千凿梨猛知岗",
        "indexValue": 178.292108478742,
        "alarmLevel": "周啡哑辖升抗织姜滴拼续润杉釜揭鲍凉",
        "lowerAlarm": 717.1568074103799,
        "seniorAlarm": 614.7003427597335,
        "seniorSeniorAlarm": 281.6216142734439,
        "parentDeviceType": "纬脓姐速是",
        "deviceId": "篡姻硕砾悯刻瑚游体材泊植绣吩",
        "deviceName": "道挚坯初阮娇胺吸垂谰哉友庶簇",
        "resolutionReason": "原因104",
        "summary": "简述89",
        "remarks": "备注40",
        "fileListStr": "处理后图片17",
        "fileList": [
          "处理后图片124",
          "处理后图片13"
        ],
        "fileListBeforeStr": "处理前图片125",
        "fileListBefore": [
          "处理前图片123",
          "处理前图片7"
        ]
      }
    ],
    "responsibleNickName": "狐毖柠尺侵队肖依辟茎织统趴每蕊起",
    "intelligentOutcomeDetailsVos": [
      {
        "id": 966,
        "index": "助蓖铃雁搞班",
        "detailsId": "谁诸诞跋钠胡党淑蠕投",
        "indexValue": 74.44729753800657,
        "alarmLevel": "柜唐哭逻舔菱翔议方闰巫越裁惧仑偏私踏",
        "lowerAlarm": 195.5123887043238,
        "seniorAlarm": 687.5883668332244,
        "seniorSeniorAlarm": 626.2616313325866,
        "parentDeviceType": "满时唁稗呜拍午八涡辉懈若歌唾牡",
        "deviceId": "咸讹读饿先寞激寥翌蚌扼徒窝堡涤诊弦袍绝",
        "deviceName": "弊贡妄挪馏襟填栅湍循掷轮星域仲鳞虎评",
        "resolutionReason": "原因82",
        "summary": "简述82",
        "remarks": "备注74",
        "fileListStr": "处理后图片35",
        "fileList": [
          "处理后图片92",
          "处理后图片66"
        ],
        "fileListBeforeStr": "处理前图片120",
        "fileListBefore": [
          "处理前图片103",
          "处理前图片108"
        ]
      },
      {
        "id": 966,
        "index": "助蓖铃雁搞班",
        "detailsId": "谁诸诞跋钠胡党淑蠕投",
        "indexValue": 74.44729753800657,
        "alarmLevel": "柜唐哭逻舔菱翔议方闰巫越裁惧仑偏私踏",
        "lowerAlarm": 195.5123887043238,
        "seniorAlarm": 687.5883668332244,
        "seniorSeniorAlarm": 626.2616313325866,
        "parentDeviceType": "满时唁稗呜拍午八涡辉懈若歌唾牡",
        "deviceId": "咸讹读饿先寞激寥翌蚌扼徒窝堡涤诊弦袍绝",
        "deviceName": "弊贡妄挪馏襟填栅湍循掷轮星域仲鳞虎评",
        "resolutionReason": "原因82",
        "summary": "简述82",
        "remarks": "备注74",
        "fileListStr": "处理后图片35",
        "fileList": [
          "处理后图片92",
          "处理后图片66"
        ],
        "fileListBeforeStr": "处理前图片120",
        "fileListBefore": [
          "处理前图片103",
          "处理前图片108"
        ]
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
| body     | **IntelligentInspectionOutcomeAppVo**    |   |   |
|└─ outcomeCode     | **String**    |  巡检结果编号 |   |
|└─ planCode     | **String**    |  巡检计划编号 |   |
|└─ planName     | **String**    |   |   |
|└─ iterationNumber     | **int**    |  巡检次数 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理） |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ nickName     | **String**    |   |   |
|└─ instrumentCount     | **Integer**    |   |   |
|└─ steamTrapsCount     | **Integer**    |   |   |
|└─ valveCount     | **Integer**    |   |   |
|└─ pipeCount     | **Integer**    |   |   |
|└─ steamProductionPointCount     | **Integer**    |   |   |
|└─ withSteamSpotsCount     | **Integer**    |   |   |
|└─ details     | **Map\<String, Map\<String, List\<IntelligentOutcomeDetailsVo\>\>\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ intelligentOutcomeDetailsVos     | **List\<IntelligentOutcomeDetailsVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
| status     | **Object**    |   |   |



# dispose

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/inspection/outcome/dispose/{outcomeCode}?outcomeCode=教赫甲伊晰罢钟脓亭杜惋秒纹碾希刑乓躺
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
  "outcomeDisposes": [
    {
      "detailsId": [
        "郸肄歉恃代泉",
        "脱框捌那稚促"
      ],
      "observesId": [
        "凡役傻拔偶",
        "胸语淤忆去不秒煽旁许嚏浆哺喻"
      ],
      "imagesId": [
        "浓披控慢永测狭横沸帘申跪借跋氛豫",
        "谬曹踏鸡焦领饭"
      ],
      "fileList": [
        "帆桐埔惑济病啤",
        "拦锻伏斋贾粥傻涉预榔"
      ],
      "fileListBefore": [
        "乃乡剑颓凡柄丑增憾躲癣鉴苦丈忽帚",
        "蔫量换抡幅首尚羽绅贮讨骆虏确颗惜候坷芥"
      ],
      "deviceId": "套条赎疗档犯刑碾缆廉顽驭待鉴户胳吠",
      "deviceName": "堤仟淀寄飘播",
      "remarks": "隋枯抿碎彻襄贞么浅轰买",
      "resolutionReason": "乡址绞睬夯噬益",
      "summary": "烛鸽畔冲粘川茄蜕亢凋蜡档"
    },
    {
      "detailsId": [
        "郸肄歉恃代泉",
        "脱框捌那稚促"
      ],
      "observesId": [
        "凡役傻拔偶",
        "胸语淤忆去不秒煽旁许嚏浆哺喻"
      ],
      "imagesId": [
        "浓披控慢永测狭横沸帘申跪借跋氛豫",
        "谬曹踏鸡焦领饭"
      ],
      "fileList": [
        "帆桐埔惑济病啤",
        "拦锻伏斋贾粥傻涉预榔"
      ],
      "fileListBefore": [
        "乃乡剑颓凡柄丑增憾躲癣鉴苦丈忽帚",
        "蔫量换抡幅首尚羽绅贮讨骆虏确颗惜候坷芥"
      ],
      "deviceId": "套条赎疗档犯刑碾缆廉顽驭待鉴户胳吠",
      "deviceName": "堤仟淀寄飘播",
      "remarks": "隋枯抿碎彻襄贞么浅轰买",
      "resolutionReason": "乡址绞睬夯噬益",
      "summary": "烛鸽畔冲粘川茄蜕亢凋蜡档"
    }
  ]
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| outcomeDisposeDTO     | **OutcomeDisposeDTO**     | 否  |   |   |
|└─ outcomeDisposes     | **List\<OutcomeDispose\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ observesId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ imagesId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**     | **是**  |   |   |
| outcomeCode     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "outcomeCode": "巡检结果编号80",
    "planCode": "巡检计划编号30",
    "planName": "抱彭搪泛谈哭燕液赖华叭狭",
    "iterationNumber": 681,
    "state": 819,
    "responsibleName": "负责人名称82",
    "createTime": "2023-12-07T22:59:28.061+0000",
    "updateTime": "2023-12-08T04:37:01.083+0000",
    "deleteFlag": 199,
    "comments": "备注63",
    "nickName": "傲送晨谁篙蝇淮",
    "instrumentCount": 317,
    "steamTrapsCount": 780,
    "valveCount": 526,
    "pipeCount": 654,
    "steamProductionPointCount": 479,
    "withSteamSpotsCount": 239,
    "details": [
      {
        "id": 422,
        "index": "搓义蝎躺悼瞅",
        "detailsId": "集散吵呈笨掀命",
        "indexValue": 804.4780170467316,
        "alarmLevel": "绣郎伯蝇浑",
        "lowerAlarm": 146.87941569494137,
        "seniorAlarm": 800.9733076970185,
        "seniorSeniorAlarm": 532.902366270199,
        "parentDeviceType": "岁国堑往契血湃匡粒但酉辨榔",
        "deviceId": "兼贮噬濒抚究基藏锻",
        "deviceName": "泛佣瞄链绚胀帘",
        "resolutionReason": "原因120",
        "summary": "简述111",
        "remarks": "备注66",
        "fileListStr": "处理后图片86",
        "fileList": [
          "处理后图片78",
          "处理后图片87"
        ],
        "fileListBeforeStr": "处理前图片78",
        "fileListBefore": [
          "处理前图片83",
          "处理前图片44"
        ]
      },
      {
        "id": 422,
        "index": "搓义蝎躺悼瞅",
        "detailsId": "集散吵呈笨掀命",
        "indexValue": 804.4780170467316,
        "alarmLevel": "绣郎伯蝇浑",
        "lowerAlarm": 146.87941569494137,
        "seniorAlarm": 800.9733076970185,
        "seniorSeniorAlarm": 532.902366270199,
        "parentDeviceType": "岁国堑往契血湃匡粒但酉辨榔",
        "deviceId": "兼贮噬濒抚究基藏锻",
        "deviceName": "泛佣瞄链绚胀帘",
        "resolutionReason": "原因120",
        "summary": "简述111",
        "remarks": "备注66",
        "fileListStr": "处理后图片86",
        "fileList": [
          "处理后图片78",
          "处理后图片87"
        ],
        "fileListBeforeStr": "处理前图片78",
        "fileListBefore": [
          "处理前图片83",
          "处理前图片44"
        ]
      }
    ],
    "responsibleNickName": "迭诬鄙氧艳烙亏睬桔普衰侣槛景",
    "intelligentOutcomeDetailsVos": [
      {
        "id": 364,
        "index": "丑血拯虫弦日",
        "detailsId": "受挖蔫项幻待讹疚琴爆",
        "indexValue": 719.5590224355698,
        "alarmLevel": "溺未翰合掀闹鄙惹爬酞冒诛产柏拷瘪",
        "lowerAlarm": 100.89769590048408,
        "seniorAlarm": 827.5901637217834,
        "seniorSeniorAlarm": 607.3393791647217,
        "parentDeviceType": "判道勺囊嫂",
        "deviceId": "卉斜佑喊彝帚宵姬军铀袜忽陛弛甫臣椒搬嘉",
        "deviceName": "讲速窟特哭绿掩漂钥泄凑",
        "resolutionReason": "原因11",
        "summary": "简述19",
        "remarks": "备注81",
        "fileListStr": "处理后图片87",
        "fileList": [
          "处理后图片115",
          "处理后图片69"
        ],
        "fileListBeforeStr": "处理前图片57",
        "fileListBefore": [
          "处理前图片56",
          "处理前图片45"
        ]
      },
      {
        "id": 364,
        "index": "丑血拯虫弦日",
        "detailsId": "受挖蔫项幻待讹疚琴爆",
        "indexValue": 719.5590224355698,
        "alarmLevel": "溺未翰合掀闹鄙惹爬酞冒诛产柏拷瘪",
        "lowerAlarm": 100.89769590048408,
        "seniorAlarm": 827.5901637217834,
        "seniorSeniorAlarm": 607.3393791647217,
        "parentDeviceType": "判道勺囊嫂",
        "deviceId": "卉斜佑喊彝帚宵姬军铀袜忽陛弛甫臣椒搬嘉",
        "deviceName": "讲速窟特哭绿掩漂钥泄凑",
        "resolutionReason": "原因11",
        "summary": "简述19",
        "remarks": "备注81",
        "fileListStr": "处理后图片87",
        "fileList": [
          "处理后图片115",
          "处理后图片69"
        ],
        "fileListBeforeStr": "处理前图片57",
        "fileListBefore": [
          "处理前图片56",
          "处理前图片45"
        ]
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
| body     | **IntelligentInspectionOutcomeAppVo**    |   |   |
|└─ outcomeCode     | **String**    |  巡检结果编号 |   |
|└─ planCode     | **String**    |  巡检计划编号 |   |
|└─ planName     | **String**    |   |   |
|└─ iterationNumber     | **int**    |  巡检次数 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理） |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ createTime     | **Date**    |  创建时间 |   |
|└─ updateTime     | **Date**    |  更新时间 |   |
|└─ deleteFlag     | **Integer**    |  删除标记（0.未删除 1.删除） |   |
|└─ comments     | **String**    |  备注 |   |
|└─ nickName     | **String**    |   |   |
|└─ instrumentCount     | **Integer**    |   |   |
|└─ steamTrapsCount     | **Integer**    |   |   |
|└─ valveCount     | **Integer**    |   |   |
|└─ pipeCount     | **Integer**    |   |   |
|└─ steamProductionPointCount     | **Integer**    |   |   |
|└─ withSteamSpotsCount     | **Integer**    |   |   |
|└─ details     | **Map\<String, Map\<String, List\<IntelligentOutcomeDetailsVo\>\>\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ intelligentOutcomeDetailsVos     | **List\<IntelligentOutcomeDetailsVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **Long**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ lowerAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ seniorSeniorAlarm     | **Double**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
| status     | **Object**    |   |   |



# 查询人工巡检结果详情

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/manualInspection/outcome/{planCode}?planCode=惭嚷幂边置努揽直等陵污
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//planCode:惭嚷幂边置努揽直等陵污

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| planCode     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "outcomeCode": "实绩编号112",
    "planCode": "计划编号24",
    "planName": "巡检计划名称4",
    "state": 358,
    "createTime": "2023-12-08T05:07:44.285+0000",
    "dueDate": "2023-12-08T01:50:21.207+0000",
    "updateTime": "2023-12-07T17:54:16.310+0000",
    "iterationNumber": 14,
    "responsibleName": "负责人名称38",
    "responsibleNickName": "朽沦瞬扑森布削烙筷宴娱从将",
    "manualOutcomeDetails": [
      {
        "id": "主键28",
        "indexValue": 214.76782610603675,
        "index": "指标85",
        "alarmLevel": 793,
        "deviceId": "设备id28",
        "deviceName": "设备名称106",
        "infoId": "处理id105",
        "outcomeCode": "实绩id94",
        "parentDeviceType": "设备类型3",
        "resolutionReason": "原因91",
        "summary": "简述125",
        "remarks": "备注79",
        "fileListStr": "处理后图片108",
        "fileList": [
          "处理后图片78",
          "处理后图片10"
        ],
        "fileListBeforeStr": "处理前图片65",
        "fileListBefore": [
          "处理前图片14",
          "处理前图片123"
        ]
      },
      {
        "id": "主键28",
        "indexValue": 214.76782610603675,
        "index": "指标85",
        "alarmLevel": 793,
        "deviceId": "设备id28",
        "deviceName": "设备名称106",
        "infoId": "处理id105",
        "outcomeCode": "实绩id94",
        "parentDeviceType": "设备类型3",
        "resolutionReason": "原因91",
        "summary": "简述125",
        "remarks": "备注79",
        "fileListStr": "处理后图片108",
        "fileList": [
          "处理后图片78",
          "处理后图片10"
        ],
        "fileListBeforeStr": "处理前图片65",
        "fileListBefore": [
          "处理前图片14",
          "处理前图片123"
        ]
      }
    ],
    "manualOutcomeImages": [
      {
        "id": "主键126",
        "deviceId": "设备id27",
        "deviceName": "设备名称82",
        "infoId": "处理id5",
        "outcomeCode": "实绩id24",
        "fileList": "图片44",
        "parentDeviceType": "设备类型94"
      },
      {
        "id": "主键126",
        "deviceId": "设备id27",
        "deviceName": "设备名称82",
        "infoId": "处理id5",
        "outcomeCode": "实绩id24",
        "fileList": "图片44",
        "parentDeviceType": "设备类型94"
      }
    ],
    "manualOutcomeObserves": [
      {
        "id": "id31",
        "decide": 340,
        "decideDescription": "观察项描述70",
        "deviceId": "设备id53",
        "deviceName": "设备名称84",
        "infoId": "处理id51",
        "outcomeCode": "实绩id84",
        "parentDeviceType": "设备类型76",
        "parentDeviceTypeName": "荆乒隶捻絮掳锐写",
        "resolutionReason": "原因33",
        "summary": "简述92",
        "remarks": "备注51",
        "fileListStr": "处理后图片99",
        "fileList": [
          "处理后图片45",
          "处理后图片91"
        ],
        "fileListBeforeStr": "处理前图片73",
        "fileListBefore": [
          "处理前图片58",
          "处理前图片36"
        ]
      },
      {
        "id": "id31",
        "decide": 340,
        "decideDescription": "观察项描述70",
        "deviceId": "设备id53",
        "deviceName": "设备名称84",
        "infoId": "处理id51",
        "outcomeCode": "实绩id84",
        "parentDeviceType": "设备类型76",
        "parentDeviceTypeName": "荆乒隶捻絮掳锐写",
        "resolutionReason": "原因33",
        "summary": "简述92",
        "remarks": "备注51",
        "fileListStr": "处理后图片99",
        "fileList": [
          "处理后图片45",
          "处理后图片91"
        ],
        "fileListBeforeStr": "处理前图片73",
        "fileListBefore": [
          "处理前图片58",
          "处理前图片36"
        ]
      }
    ],
    "inspectionType": "债韧聪邀儿蔓通",
    "inspectionTypeName": "永痊油讹眺栓宝樊椭驼付碧秒盂甚伟顽"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **ManualInspectionOutcomeAppVo**    |   |   |
|└─ outcomeCode     | **String**    |  实绩编号 |   |
|└─ planCode     | **String**    |  计划编号 |   |
|└─ planName     | **String**    |  巡检计划名称 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理 3.未做） |   |
|└─ createTime     | **Date**    |  完成时间 |   |
|└─ dueDate     | **Date**    |  截至时间 |   |
|└─ updateTime     | **Date**    |  修改时间（处理完成时间） |   |
|└─ iterationNumber     | **Integer**    |  巡检次数 |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ manualOutcomeDetails     | **List\<ManualOutcomeDetailsWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |  指标值 |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |  指标 |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |  报警级别(alarm_level) |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ manualOutcomeImages     | **List\<ManualOutcomeImage\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |  图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|└─ manualOutcomeObserves     | **List\<ManualOutcomeObserveWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  id |   |
|&ensp;&ensp;&ensp;&ensp;└─ decide     | **Integer**    |  判定(0.正常 1.异常) |   |
|&ensp;&ensp;&ensp;&ensp;└─ decideDescription     | **String**    |  观察项描述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ inspectionType     | **String**    |   |   |
|└─ inspectionTypeName     | **String**    |   |   |
| status     | **Object**    |   |   |



# 人工巡检结果结果录入

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/manualInspection/outcome/dispose/{outcomeCode}?outcomeCode=劣未全掂淘
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
  "outcomeDisposes": [
    {
      "detailsId": [
        "幕既肘区赵羌",
        "梳吗疵负辑燕迟缩匈横叛吝削是羔垂茬"
      ],
      "observesId": [
        "根臆琐诬娃",
        "蒙冠货晴饼甜尝赶粘咙诵吝碘彰园兵诛仇成"
      ],
      "imagesId": [
        "唾疹合豁怠瘪盆唁淆梯妹戌氮",
        "适麦蔗劝广恩府硫羊蓖耀蛊撇些"
      ],
      "fileList": [
        "象能瘪饼接葛耗",
        "胆瞪逾已创幂吓灵砌淤涣梨踏迹痢阀辨"
      ],
      "fileListBefore": [
        "芥为被惩冬诀束脉巾榨雀瞒仿缚苇",
        "酬寇趣捅砸荒山镜栋传晒欠涕"
      ],
      "deviceId": "漓阀婿红画默店酗搽胰视",
      "deviceName": "旅赋乙嘻故厄娄晦个鸭谨累踩掖吕",
      "remarks": "赛就研窖漆舱奥垄绪散畦卫硒髓顾靖梅",
      "resolutionReason": "肌贤你择魂之喘吏",
      "summary": "示贾逗疤拱绸"
    },
    {
      "detailsId": [
        "幕既肘区赵羌",
        "梳吗疵负辑燕迟缩匈横叛吝削是羔垂茬"
      ],
      "observesId": [
        "根臆琐诬娃",
        "蒙冠货晴饼甜尝赶粘咙诵吝碘彰园兵诛仇成"
      ],
      "imagesId": [
        "唾疹合豁怠瘪盆唁淆梯妹戌氮",
        "适麦蔗劝广恩府硫羊蓖耀蛊撇些"
      ],
      "fileList": [
        "象能瘪饼接葛耗",
        "胆瞪逾已创幂吓灵砌淤涣梨踏迹痢阀辨"
      ],
      "fileListBefore": [
        "芥为被惩冬诀束脉巾榨雀瞒仿缚苇",
        "酬寇趣捅砸荒山镜栋传晒欠涕"
      ],
      "deviceId": "漓阀婿红画默店酗搽胰视",
      "deviceName": "旅赋乙嘻故厄娄晦个鸭谨累踩掖吕",
      "remarks": "赛就研窖漆舱奥垄绪散畦卫硒髓顾靖梅",
      "resolutionReason": "肌贤你择魂之喘吏",
      "summary": "示贾逗疤拱绸"
    }
  ]
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| manualDisposeDTO     | **ManualDisposeDTO**     | 否  |   |   |
|└─ outcomeDisposes     | **List\<OutcomeDispose\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ detailsId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ observesId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ imagesId     | **List\<String\>**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**     | 否  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**     | **是**  |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**     | **是**  |   |   |
| outcomeCode     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "outcomeCode": "实绩编号48",
    "planCode": "计划编号62",
    "planName": "巡检计划名称12",
    "state": 718,
    "createTime": "2023-12-08T16:07:16.066+0000",
    "dueDate": "2023-12-08T05:37:48.609+0000",
    "updateTime": "2023-12-08T01:15:00.413+0000",
    "iterationNumber": 831,
    "responsibleName": "负责人名称106",
    "responsibleNickName": "胚腔源帮帮链惠圣笑膊勋咸",
    "manualOutcomeDetails": [
      {
        "id": "主键55",
        "indexValue": 387.3246800598093,
        "index": "指标59",
        "alarmLevel": 469,
        "deviceId": "设备id87",
        "deviceName": "设备名称11",
        "infoId": "处理id31",
        "outcomeCode": "实绩id85",
        "parentDeviceType": "设备类型77",
        "resolutionReason": "原因17",
        "summary": "简述39",
        "remarks": "备注64",
        "fileListStr": "处理后图片111",
        "fileList": [
          "处理后图片60",
          "处理后图片98"
        ],
        "fileListBeforeStr": "处理前图片105",
        "fileListBefore": [
          "处理前图片18",
          "处理前图片111"
        ]
      },
      {
        "id": "主键55",
        "indexValue": 387.3246800598093,
        "index": "指标59",
        "alarmLevel": 469,
        "deviceId": "设备id87",
        "deviceName": "设备名称11",
        "infoId": "处理id31",
        "outcomeCode": "实绩id85",
        "parentDeviceType": "设备类型77",
        "resolutionReason": "原因17",
        "summary": "简述39",
        "remarks": "备注64",
        "fileListStr": "处理后图片111",
        "fileList": [
          "处理后图片60",
          "处理后图片98"
        ],
        "fileListBeforeStr": "处理前图片105",
        "fileListBefore": [
          "处理前图片18",
          "处理前图片111"
        ]
      }
    ],
    "manualOutcomeImages": [
      {
        "id": "主键73",
        "deviceId": "设备id73",
        "deviceName": "设备名称27",
        "infoId": "处理id109",
        "outcomeCode": "实绩id31",
        "fileList": "图片22",
        "parentDeviceType": "设备类型58"
      },
      {
        "id": "主键73",
        "deviceId": "设备id73",
        "deviceName": "设备名称27",
        "infoId": "处理id109",
        "outcomeCode": "实绩id31",
        "fileList": "图片22",
        "parentDeviceType": "设备类型58"
      }
    ],
    "manualOutcomeObserves": [
      {
        "id": "id18",
        "decide": 770,
        "decideDescription": "观察项描述36",
        "deviceId": "设备id36",
        "deviceName": "设备名称72",
        "infoId": "处理id48",
        "outcomeCode": "实绩id98",
        "parentDeviceType": "设备类型104",
        "parentDeviceTypeName": "伊淀激些寒源挠绽嵌茶肇牟嘿藻绝",
        "resolutionReason": "原因84",
        "summary": "简述60",
        "remarks": "备注118",
        "fileListStr": "处理后图片1",
        "fileList": [
          "处理后图片103",
          "处理后图片95"
        ],
        "fileListBeforeStr": "处理前图片33",
        "fileListBefore": [
          "处理前图片35",
          "处理前图片43"
        ]
      },
      {
        "id": "id18",
        "decide": 770,
        "decideDescription": "观察项描述36",
        "deviceId": "设备id36",
        "deviceName": "设备名称72",
        "infoId": "处理id48",
        "outcomeCode": "实绩id98",
        "parentDeviceType": "设备类型104",
        "parentDeviceTypeName": "伊淀激些寒源挠绽嵌茶肇牟嘿藻绝",
        "resolutionReason": "原因84",
        "summary": "简述60",
        "remarks": "备注118",
        "fileListStr": "处理后图片1",
        "fileList": [
          "处理后图片103",
          "处理后图片95"
        ],
        "fileListBeforeStr": "处理前图片33",
        "fileListBefore": [
          "处理前图片35",
          "处理前图片43"
        ]
      }
    ],
    "inspectionType": "踊蕾魄擞存似",
    "inspectionTypeName": "秽增尚室污幂"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **ManualInspectionOutcomeAppVo**    |   |   |
|└─ outcomeCode     | **String**    |  实绩编号 |   |
|└─ planCode     | **String**    |  计划编号 |   |
|└─ planName     | **String**    |  巡检计划名称 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理 3.未做） |   |
|└─ createTime     | **Date**    |  完成时间 |   |
|└─ dueDate     | **Date**    |  截至时间 |   |
|└─ updateTime     | **Date**    |  修改时间（处理完成时间） |   |
|└─ iterationNumber     | **Integer**    |  巡检次数 |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ manualOutcomeDetails     | **List\<ManualOutcomeDetailsWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |  指标值 |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |  指标 |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |  报警级别(alarm_level) |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ manualOutcomeImages     | **List\<ManualOutcomeImage\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |  图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|└─ manualOutcomeObserves     | **List\<ManualOutcomeObserveWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  id |   |
|&ensp;&ensp;&ensp;&ensp;└─ decide     | **Integer**    |  判定(0.正常 1.异常) |   |
|&ensp;&ensp;&ensp;&ensp;└─ decideDescription     | **String**    |  观察项描述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ inspectionType     | **String**    |   |   |
|└─ inspectionTypeName     | **String**    |   |   |
| status     | **Object**    |   |   |



# manualInspectionDisposeWrite

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/manualInspection/disposeWrite
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
  "outcomeCode": "纺堰青廓乙梅辣",
  "planCode": "谷缺稻嵌吧城蜘国胁屁窄历",
  "planName": "磋烂瞬愤奇谴目庸磅真春匿丝尺甭造朗",
  "responsibleName": "袁厂础奸包拟倾憨峰闷景断",
  "manualOutcomeDetails": [
    {
      "outcomeCode": "涵象养姐儡皮雁长屯",
      "id": "创罕囊进忠谜己办抠赐闽盯携蔑候勉鲤寇",
      "index": "靴缝拒氓篷蓟斑蓄",
      "deviceId": "煽裹确黑正苗匀毡介进浦薪品恿芯映薛灸",
      "indexValue": 171.48177997266384,
      "alarmLevel": 497
    },
    {
      "outcomeCode": "涵象养姐儡皮雁长屯",
      "id": "创罕囊进忠谜己办抠赐闽盯携蔑候勉鲤寇",
      "index": "靴缝拒氓篷蓟斑蓄",
      "deviceId": "煽裹确黑正苗匀毡介进浦薪品恿芯映薛灸",
      "indexValue": 171.48177997266384,
      "alarmLevel": 497
    }
  ],
  "manualOutcomeImages": [
    {
      "outcomeCode": "胸吱画押廊林庭观急伎哨啊肾",
      "id": "蓖啪干喊搅桃柜驶场腊语供蔑柳坑谜枉滔庇",
      "fileList": "褐骗骑脊氮款澡审"
    },
    {
      "outcomeCode": "胸吱画押廊林庭观急伎哨啊肾",
      "id": "蓖啪干喊搅桃柜驶场腊语供蔑柳坑谜枉滔庇",
      "fileList": "褐骗骑脊氮款澡审"
    }
  ],
  "manualOutcomeObserves": [
    {
      "outcomeCode": "臣察摩壕臼矽肆弦芬馅侄兔压潞行隅后对",
      "id": "杯度晴旺蛀谍邯抠",
      "decide": 349
    },
    {
      "outcomeCode": "臣察摩壕臼矽肆弦芬馅侄兔压潞行隅后对",
      "id": "杯度晴旺蛀谍邯抠",
      "decide": 349
    }
  ]
}
```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| outcomeCode     | **String**     | 否  |   |   |
| planCode     | **String**     | 否  |   |   |
| planName     | **String**     | 否  |   |   |
| responsibleName     | **String**     | 否  |   |   |
| manualOutcomeDetails     | **List\<ManualOutcomeDetailsAdd\>**     | 否  |   |   |
|└─ outcomeCode     | **String**     | 否  |   |   |
|└─ id     | **String**     | 否  |   |   |
|└─ index     | **String**     | 否  |   |   |
|└─ deviceId     | **String**     | 否  |   |   |
|└─ indexValue     | **Double**     | **是**  |   |   |
|└─ alarmLevel     | **Integer**     | 否  |   |   |
| manualOutcomeImages     | **List\<ManualOutcomeImageAdd\>**     | 否  |   |   |
|└─ outcomeCode     | **String**     | 否  |   |   |
|└─ id     | **String**     | 否  |   |   |
|└─ fileList     | **String**     | **是**  |   |   |
| manualOutcomeObserves     | **List\<ManualOutcomeObserveAdd\>**     | 否  |   |   |
|└─ outcomeCode     | **String**     | 否  |   |   |
|└─ id     | **String**     | 否  |   |   |
|└─ decide     | **Integer**     | **是**  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "outcomeCode": "实绩编号56",
    "planCode": "计划编号69",
    "planName": "巡检计划名称56",
    "state": 405,
    "createTime": "2023-12-08T11:39:38.197+0000",
    "dueDate": "2023-12-07T19:39:56.169+0000",
    "updateTime": "2023-12-08T05:41:49.916+0000",
    "iterationNumber": 850,
    "responsibleName": "负责人名称114",
    "responsibleNickName": "梨峭聪较穗男案穆韩斡撵寸城奠含唱洞慷军",
    "manualOutcomeDetails": [
      {
        "id": "主键101",
        "indexValue": 533.3779031538379,
        "index": "指标80",
        "alarmLevel": 345,
        "deviceId": "设备id29",
        "deviceName": "设备名称96",
        "infoId": "处理id114",
        "outcomeCode": "实绩id79",
        "parentDeviceType": "设备类型64",
        "resolutionReason": "原因112",
        "summary": "简述6",
        "remarks": "备注62",
        "fileListStr": "处理后图片123",
        "fileList": [
          "处理后图片110",
          "处理后图片86"
        ],
        "fileListBeforeStr": "处理前图片65",
        "fileListBefore": [
          "处理前图片77",
          "处理前图片7"
        ]
      },
      {
        "id": "主键101",
        "indexValue": 533.3779031538379,
        "index": "指标80",
        "alarmLevel": 345,
        "deviceId": "设备id29",
        "deviceName": "设备名称96",
        "infoId": "处理id114",
        "outcomeCode": "实绩id79",
        "parentDeviceType": "设备类型64",
        "resolutionReason": "原因112",
        "summary": "简述6",
        "remarks": "备注62",
        "fileListStr": "处理后图片123",
        "fileList": [
          "处理后图片110",
          "处理后图片86"
        ],
        "fileListBeforeStr": "处理前图片65",
        "fileListBefore": [
          "处理前图片77",
          "处理前图片7"
        ]
      }
    ],
    "manualOutcomeImages": [
      {
        "id": "主键95",
        "deviceId": "设备id60",
        "deviceName": "设备名称92",
        "infoId": "处理id89",
        "outcomeCode": "实绩id66",
        "fileList": "图片97",
        "parentDeviceType": "设备类型16"
      },
      {
        "id": "主键95",
        "deviceId": "设备id60",
        "deviceName": "设备名称92",
        "infoId": "处理id89",
        "outcomeCode": "实绩id66",
        "fileList": "图片97",
        "parentDeviceType": "设备类型16"
      }
    ],
    "manualOutcomeObserves": [
      {
        "id": "id75",
        "decide": 548,
        "decideDescription": "观察项描述88",
        "deviceId": "设备id6",
        "deviceName": "设备名称49",
        "infoId": "处理id95",
        "outcomeCode": "实绩id104",
        "parentDeviceType": "设备类型52",
        "parentDeviceTypeName": "超型贤患肿僻目",
        "resolutionReason": "原因64",
        "summary": "简述118",
        "remarks": "备注23",
        "fileListStr": "处理后图片67",
        "fileList": [
          "处理后图片8",
          "处理后图片18"
        ],
        "fileListBeforeStr": "处理前图片2",
        "fileListBefore": [
          "处理前图片68",
          "处理前图片67"
        ]
      },
      {
        "id": "id75",
        "decide": 548,
        "decideDescription": "观察项描述88",
        "deviceId": "设备id6",
        "deviceName": "设备名称49",
        "infoId": "处理id95",
        "outcomeCode": "实绩id104",
        "parentDeviceType": "设备类型52",
        "parentDeviceTypeName": "超型贤患肿僻目",
        "resolutionReason": "原因64",
        "summary": "简述118",
        "remarks": "备注23",
        "fileListStr": "处理后图片67",
        "fileList": [
          "处理后图片8",
          "处理后图片18"
        ],
        "fileListBeforeStr": "处理前图片2",
        "fileListBefore": [
          "处理前图片68",
          "处理前图片67"
        ]
      }
    ],
    "inspectionType": "暂厅惭庶腋捡驮舶雕团折孕品采伴攒钥砷幻",
    "inspectionTypeName": "姻咒坤呀孤匹脓还滤交蔽贷济疙丝趟"
  },
  "status": {}
}
```


### 出参字段说明

| **字段** | **类型**  | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- |
| headers     | **HttpHeaders**    |   |   |
| body     | **ManualInspectionOutcomeAppVo**    |   |   |
|└─ outcomeCode     | **String**    |  实绩编号 |   |
|└─ planCode     | **String**    |  计划编号 |   |
|└─ planName     | **String**    |  巡检计划名称 |   |
|└─ state     | **Integer**    |  实绩状态（0.正常 1.未处理 2.已处理 3.未做） |   |
|└─ createTime     | **Date**    |  完成时间 |   |
|└─ dueDate     | **Date**    |  截至时间 |   |
|└─ updateTime     | **Date**    |  修改时间（处理完成时间） |   |
|└─ iterationNumber     | **Integer**    |  巡检次数 |   |
|└─ responsibleName     | **String**    |  负责人名称 |   |
|└─ responsibleNickName     | **String**    |   |   |
|└─ manualOutcomeDetails     | **List\<ManualOutcomeDetailsWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ indexValue     | **Double**    |  指标值 |   |
|&ensp;&ensp;&ensp;&ensp;└─ index     | **String**    |  指标 |   |
|&ensp;&ensp;&ensp;&ensp;└─ alarmLevel     | **Integer**    |  报警级别(alarm_level) |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ manualOutcomeImages     | **List\<ManualOutcomeImage\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  主键 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **String**    |  图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|└─ manualOutcomeObserves     | **List\<ManualOutcomeObserveWithHandleVo\>**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ id     | **String**    |  id |   |
|&ensp;&ensp;&ensp;&ensp;└─ decide     | **Integer**    |  判定(0.正常 1.异常) |   |
|&ensp;&ensp;&ensp;&ensp;└─ decideDescription     | **String**    |  观察项描述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceId     | **String**    |  设备id |   |
|&ensp;&ensp;&ensp;&ensp;└─ deviceName     | **String**    |  设备名称 |   |
|&ensp;&ensp;&ensp;&ensp;└─ infoId     | **String**    |  处理id |   |
|&ensp;&ensp;&ensp;&ensp;└─ outcomeCode     | **String**    |  实绩id |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceType     | **String**    |  设备类型 |   |
|&ensp;&ensp;&ensp;&ensp;└─ parentDeviceTypeName     | **String**    |   |   |
|&ensp;&ensp;&ensp;&ensp;└─ resolutionReason     | **String**    |  原因 |   |
|&ensp;&ensp;&ensp;&ensp;└─ summary     | **String**    |  简述 |   |
|&ensp;&ensp;&ensp;&ensp;└─ remarks     | **String**    |  备注 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListStr     | **String**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileList     | **List\<String\>**    |  处理后图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBeforeStr     | **String**    |  处理前图片 |   |
|&ensp;&ensp;&ensp;&ensp;└─ fileListBefore     | **List\<String\>**    |  处理前图片 |   |
|└─ inspectionType     | **String**    |   |   |
|└─ inspectionTypeName     | **String**    |   |   |
| status     | **Object**    |   |   |



# 查询某个设备id的报警规则

## 请求信息

### 请求地址
```
http://192.168.1.38:8080/app/deviceAlarms/findByDeviceIdAndIndexId?deviceId=阉若熄楼既往训月领凯崔存代氢乳&indexId=蜕负掸廓搭冕寺逢堤笼溅瘸溺惕进馁氯
```

### 请求方法
```
GET
```


## 入参
### 入参示例 (Postman Bulk Edit)
```json
//deviceId:阉若熄楼既往训月领凯崔存代氢乳
//indexId:蜕负掸廓搭冕寺逢堤笼溅瘸溺惕进馁氯

```


### 入参字段说明

| **字段** | **类型** | **必填** | **含义** | **其他参考信息** |
| -------- | -------- | -------- | -------- | -------- |
| deviceId     | **String**     | 否  |   |   |
| indexId     | **String**     | 否  |   |   |

## 出参
### 出参示例
```json
{
  "headers": {},
  "body": {
    "id": "主键17",
    "deviceId": "设备id115",
    "index": "指标51",
    "lowerAlarm": 563.3153862409295,
    "seniorAlarm": 952.1280027554848,
    "seniorSeniorAlarm": 81.21550619207747,
    "state": 964,
    "createTime": {
      "date": {
        "year": 950,
        "month": 51,
        "day": 52
      },
      "time": {
        "hour": -16,
        "minute": -121,
        "second": 16,
        "nano": 711
      }
    },
    "updateTime": {
      "date": {
        "year": 1021,
        "month": 44,
        "day": 83
      },
      "time": {
        "hour": -62,
        "minute": 73,
        "second": -23,
        "nano": 406
      }
    },
    "createdByName": "创建人72",
    "updatedByName": "修改人70",
    "deleteFlag": 244,
    "comments": "备注86",
    "ignoreTime": "2023-12-08T13:31:47.111+0000"
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




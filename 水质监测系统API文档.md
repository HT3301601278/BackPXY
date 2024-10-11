# 水质监测系统API文档

## 基础URL

所有API都以以下基础URL开头:

```
http://localhost:8080/api
```

## API列表

### 1. 用户管理

#### 1.1 注册用户

- **URL**: `/users/register`
- **方法**: POST
- **描述**: 注册新用户
- **请求体**:
  ```json
  {
    "username": "string",
    "password": "string",
    "email": "string",
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 注册成功的用户信息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常注册:
   ```
   POST /api/users/register
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "password123",
     "email": "testuser@example.com",
   }
   ```

2. 重复用户名:
   ```
   POST /api/users/register
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "password456",
     "email": "testuser2@example.com",
   }
   ```

3. 重复邮箱:
   ```
   POST /api/users/register
   Content-Type: application/json

   {
     "username": "testuser2",
     "password": "password789",
     "email": "testuser@example.com",
   }
   ```

#### 1.2 用户登录

- **URL**: `/users/login`
- **方法**: POST
- **描述**: 用户登录
- **请求体**:
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 登录成功的用户信息
- **错误响应**:
  - 状态码: 401 Unauthorized
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常登录:
   ```
   POST /api/users/login
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "password123"
   }
   ```

2. 错误密码:
   ```
   POST /api/users/login
   Content-Type: application/json

   {
     "username": "testuser",
     "password": "wrongpassword"
   }
   ```

3. 不存在的用户:
   ```
   POST /api/users/login
   Content-Type: application/json

   {
     "username": "nonexistentuser",
     "password": "password123"
   }
   ```

### 2. 设备管理

#### 2.1 添加设备

- **URL**: `/devices`
- **方法**: POST
- **描述**: 添加新设备
- **请求体**:
  ```json
  {
    "name": "string",
    "type": "string",
    "status": "string",
    "samplingFrequency": "number"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 添加成功的设备信息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常添加设备:
   ```
   POST /api/devices
   Content-Type: application/json

   {
     "name": "Device 1",
     "type": "WaterQualitySensor",
     "status": "Active",
     "samplingFrequency": 5.0
   }
   ```

2. 名称为空:
   ```
   POST /api/devices
   Content-Type: application/json

   {
     "name": "",
     "type": "WaterQualitySensor",
     "status": "Active",
     "samplingFrequency": 5.0
   }
   ```

3. 类型为空:
   ```
   POST /api/devices
   Content-Type: application/json

   {
     "name": "Device 2",
     "type": "",
     "status": "Active",
     "samplingFrequency": 5.0
   }
   ```

#### 2.2 更新设备

- **URL**: `/devices/{id}`
- **方法**: PUT
- **描述**: 更新指定ID的设备信息
- **请求体**:
  ```json
  {
    "name": "string",
    "type": "string",
    "status": "string",
    "samplingFrequency": "number"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 更新后的设备信息
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常更新设备:
   ```
   PUT /api/devices/1
   Content-Type: application/json

   {
     "name": "Updated Device 1",
     "type": "WaterQualitySensor",
     "status": "Inactive",
     "samplingFrequency": 10.0
   }
   ```

2. 更新不存在的设备:
   ```
   PUT /api/devices/999
   Content-Type: application/json

   {
     "name": "Non-existent Device",
     "type": "WaterQualitySensor",
     "status": "Active",
     "samplingFrequency": 5.0
   }
   ```

#### 2.3 删除设备

- **URL**: `/devices/{id}`
- **方法**: DELETE
- **描述**: 删除指定ID的设备
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 无
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常删除设备:
   ```
   DELETE /api/devices/1
   ```

2. 删除不存在的设备:
   ```
   DELETE /api/devices/999
   ```

#### 2.4 获取单个设备

- **URL**: `/devices/{id}`
- **方法**: GET
- **描述**: 获取指定ID的设备信息
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 设备信息
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 无

**测试用例**:

1. 获取存在的设备:
   ```
   GET /api/devices/1
   ```

2. 获取不存在的设备:
   ```
   GET /api/devices/999
   ```

#### 2.5 获取所有设备

- **URL**: `/devices`
- **方法**: GET
- **描述**: 获取所有设备的列表
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 设备列表

**测试用例**:

1. 获取所有设备:
   ```
   GET /api/devices
   ```

#### 2.6 根据状态获取设备

- **URL**: `/devices/status/{status}`
- **方法**: GET
- **描述**: 获取指定状态的设备列表
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 设备列表

**测试用例**:

1. 获取活跃状态的设备:
   ```
   GET /api/devices/status/Active
   ```

2. 获取非活跃状态的设备:
   ```
   GET /api/devices/status/Inactive
   ```

#### 2.7 更新设备状态

- **URL**: `/devices/{id}/status`
- **方法**: PUT
- **描述**: 更新指定ID设备的状态
- **请求参数**:
  - `status`: 新的设备状态
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 更新后的设备信息
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 错误信息字符串

**测试用例**:

1. 更新存在设备的状态:
   ```
   PUT /api/devices/1/status?status=Inactive
   ```

2. 更新不存在设备的状态:
   ```
   PUT /api/devices/999/status?status=Active
   ```

### 3. 监测数据管理

#### 3.1 保存监测数据

- **URL**: `/monitoring-data`
- **方法**: POST
- **描述**: 保存新的监测数据
- **请求体**:
  ```json
  {
    "deviceId": "number",
    "temperature": "number",
    "waterLevel": "number",
    "timestamp": "string (ISO 8601 format)"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 保存成功的监测数据信息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常保存监测数据:
   ```
   POST /api/monitoring-data
   Content-Type: application/json

   {
     "deviceId": 1,
     "temperature": 25.5,
     "waterLevel": 10.2,
     "timestamp": "2023-05-20T10:30:00Z"
   }
   ```

2. 保存数据到不存在的设备:
   ```
   POST /api/monitoring-data
   Content-Type: application/json

   {
     "deviceId": 999,
     "temperature": 26.0,
     "waterLevel": 11.0,
     "timestamp": "2023-05-20T10:35:00Z"
   }
   ```

#### 3.2 获取设备的监测数据

- **URL**: `/monitoring-data/device/{deviceId}`
- **方法**: GET
- **描述**: 获取指定设备在给定时间范围内的监测数据
- **请求参数**:
  - `startTime`: 开始时间 (ISO 8601 格式)
  - `endTime`: 结束时间 (ISO 8601 格式)
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 监测数据列表
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取有效时间范围内的数据:
   ```
   GET /api/monitoring-data/device/1?startTime=2023-05-20T00:00:00Z&endTime=2023-05-21T00:00:00Z
   ```

2. 获取无效时间范围的数据:
   ```
   GET /api/monitoring-data/device/1?startTime=2023-05-21T00:00:00Z&endTime=2023-05-20T00:00:00Z
   ```

3. 获取不存在设备的数据:
   ```
   GET /api/monitoring-data/device/999?startTime=2023-05-20T00:00:00Z&endTime=2023-05-21T00:00:00Z
   ```

#### 3.3 获取设备的最新监测数据

- **URL**: `/monitoring-data/device/{deviceId}/latest`
- **方法**: GET
- **描述**: 获取指定设备的最新监测数据
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 最新的监测数据
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 无

**测试用例**:

1. 获取存在设备的最新数据:
   ```
   GET /api/monitoring-data/device/1/latest
   ```

2. 获取不存在设备的最新数据:
   ```
   GET /api/monitoring-data/device/999/latest
   ```

### 4. 报警阈值管理

#### 4.1 创建报警阈值

- **URL**: `/alarm-thresholds`
- **方法**: POST
- **描述**: 为指定设备创建新的报警阈值
- **请求体**:
  ```json
  {
    "deviceId": "number",
    "metricName": "string",
    "minThreshold": "number",
    "maxThreshold": "number"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 创建成功的报警阈值信息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常创建报警阈值:
   ```
   POST /api/alarm-thresholds
   Content-Type: application/json

   {
     "deviceId": 1,
     "metricName": "temperature",
     "minThreshold": 10.0,
     "maxThreshold": 30.0
   }
   ```

2. 为不存在的设备创建报警阈值:
   ```
   POST /api/alarm-thresholds
   Content-Type: application/json

   {
     "deviceId": 999,
     "metricName": "waterLevel",
     "minThreshold": 5.0,
     "maxThreshold": 15.0
   }
   ```

#### 4.2 更新报警阈值

- **URL**: `/alarm-thresholds/{id}`
- **方法**: PUT
- **描述**: 更新指定ID的报警阈值
- **请求体**:
  ```json
  {
    "metricName": "string",
    "minThreshold": "number",
    "maxThreshold": "number"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 更新后的报警阈值信息
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 错误信息字符串

**测试用例**:

1. 更新存在的报警阈值:
   ```
   PUT /api/alarm-thresholds/1
   Content-Type: application/json

   {
     "metricName": "temperature",
     "minThreshold": 15.0,
     "maxThreshold": 35.0
   }
   ```

2. 更新不存在的报警阈值:
   ```
   PUT /api/alarm-thresholds/999
   Content-Type: application/json

   {
     "metricName": "waterLevel",
     "minThreshold": 2.0,
     "maxThreshold": 8.0
   }
   ```

#### 4.3 删除报警阈值

- **URL**: `/alarm-thresholds/{id}`
- **方法**: DELETE
- **描述**: 删除指定ID的报警阈值
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 成功消息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 删除存在的报警阈值:
   ```
   DELETE /api/alarm-thresholds/1
   ```

2. 删除不存在的报警阈值:
   ```
   DELETE /api/alarm-thresholds/999
   ```

#### 4.4 获取设备的报警阈值

- **URL**: `/alarm-thresholds/device/{deviceId}`
- **方法**: GET
- **描述**: 获取指定设备的所有报警阈值
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 报警阈值列表
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 无

**测试用例**:

1. 获取存在设备的报警阈值:
   ```
   GET /api/alarm-thresholds/device/1
   ```

2. 获取不存在设备的报警阈值:
   ```
   GET /api/alarm-thresholds/device/999
   ```

### 5. 报警管理

#### 5.1 获取设备报警

- **URL**: `/alarms/device/{deviceId}`
- **方法**: GET
- **描述**: 获取指定设备的报警记录
- **请求参数**:
  - `status`: 报警状态（可选，默认为"ACTIVE"）
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 报警记录列表
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取设备的活跃报警:
   ```
   GET /api/alarms/device/1?status=ACTIVE
   ```

2. 获取设备的所有报警:
   ```
   GET /api/alarms/device/1
   ```

#### 5.2 更新报警状态

- **URL**: `/alarms/{alarmId}/status`
- **方法**: PUT
- **描述**: 更新指定报警的状态
- **请求参数**:
  - `newStatus`: 新的报警状态
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 更新后的报警记录
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 无

**测试用例**:

1. 更新存在的报警状态:
   ```
   PUT /api/alarms/1/status?newStatus=RESOLVED
   ```

2. 更新不存在的报警状态:
   ```
   PUT /api/alarms/999/status?newStatus=RESOLVED
   ```

#### 5.3 获取报警统计

- **URL**: `/alarms/statistics/count`
- **方法**: GET
- **描述**: 获取指定设备在给定时间范围内的报警数量
- **请求参数**:
  - `deviceId`: 设备ID
  - `startTime`: 开始时间 (ISO 8601 格式)
  - `endTime`: 结束时间 (ISO 8601 格式)
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 报警数量
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取有效时间范围内的报警统计:
   ```
   GET /api/alarms/statistics/count?deviceId=1&startTime=2023-05-20T00:00:00Z&endTime=2023-05-21T00:00:00Z
   ```

2. 获取无效时间范围的报警统计:
   ```
   GET /api/alarms/statistics/count?deviceId=1&startTime=2023-05-21T00:00:00Z&endTime=2023-05-20T00:00:00Z
   ```

#### 5.4 获取报警频率

- **URL**: `/alarms/statistics/frequency`
- **方法**: GET
- **描述**: 获取指定设备在给定时间范围内的报警频率（按类型分类）
- **请求参数**:
  - `deviceId`: 设备ID
  - `startTime`: 开始时间 (ISO 8601 格式)
  - `endTime`: 结束时间 (ISO 8601 格式)
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 报警频率统计（按类型分类）
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取有效时间范围内的报警频率:
   ```
   GET /api/alarms/statistics/frequency?deviceId=1&startTime=2023-05-20T00:00:00Z&endTime=2023-05-21T00:00:00Z
   ```

2. 获取无效时间范围的报警频率:
   ```
   GET /api/alarms/statistics/frequency?deviceId=1&startTime=2023-05-21T00:00:00Z&endTime=2023-05-20T00:00:00Z
   ```

#### 5.5 获取设备的所有报警

- **URL**: `/alarms/device/{deviceId}/all`
- **方法**: GET
- **描述**: 获取指定设备的所有报警记录
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 所有报警记录列表
- **错误响应**:
  - 状态码: 500 Internal Server Error
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取存在设备的所有报警:
   ```
   GET /api/alarms/device/1/all
   ```

2. 获取不存在设备的所有报警:
   ```
   GET /api/alarms/device/999/all
   ```

#### 5.6 获取设备的活跃报警

- **URL**: `/alarms/device/{deviceId}/active`
- **方法**: GET
- **描述**: 获取指定设备的活跃报警记录
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 活跃报警记录列表
- **错误响应**:
  - 状态码: 500 Internal Server Error
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取存在设备的活跃报警:
   ```
   GET /api/alarms/device/1/active
   ```

2. 获取不存在设备的活跃报警:
   ```
   GET /api/alarms/device/999/active
   ```

### 6. 监测数据管理

#### 6.1 保存监测数据

- **URL**: `/monitoring-data`
- **方法**: POST
- **描述**: 保存新的监测数据
- **请求体**:
  ```json
  {
    "deviceId": "number",
    "temperature": "number",
    "waterLevel": "number",
    "timestamp": "string (ISO 8601 format)"
  }
  ```
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 保存成功的监测数据信息
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 正常保存监测数据:
   ```
   POST /api/monitoring-data
   Content-Type: application/json

   {
     "deviceId": 1,
     "temperature": 25.5,
     "waterLevel": 10.2,
     "timestamp": "2023-05-20T10:30:00Z"
   }
   ```

2. 保存数据到不存在的设备:
   ```
   POST /api/monitoring-data
   Content-Type: application/json

   {
     "deviceId": 999,
     "temperature": 26.0,
     "waterLevel": 11.0,
     "timestamp": "2023-05-20T10:35:00Z"
   }
   ```

#### 6.2 获取设备的监测数据

- **URL**: `/monitoring-data/device/{deviceId}`
- **方法**: GET
- **描述**: 获取指定设备在给定时间范围内的监测数据
- **请求参数**:
  - `startTime`: 开始时间 (ISO 8601 格式)
  - `endTime`: 结束时间 (ISO 8601 格式)
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 监测数据列表
- **错误响应**:
  - 状态码: 400 Bad Request
  - 响应体: 错误信息字符串

**测试用例**:

1. 获取有效时间范围内的数据:
   ```
   GET /api/monitoring-data/device/1?startTime=2023-05-20T00:00:00Z&endTime=2023-05-21T00:00:00Z
   ```

2. 获取无效时间范围的数据:
   ```
   GET /api/monitoring-data/device/1?startTime=2023-05-21T00:00:00Z&endTime=2023-05-20T00:00:00Z
   ```

#### 6.3 获取设备的最新监测数据

- **URL**: `/monitoring-data/device/{deviceId}/latest`
- **方法**: GET
- **描述**: 获取指定设备的最新监测数据
- **成功响应**: 
  - 状态码: 200 OK
  - 响应体: 最新的监测数据
- **错误响应**:
  - 状态码: 404 Not Found
  - 响应体: 无

**测试用例**:

1. 获取存在设备的最新数据:
   ```
   GET /api/monitoring-data/device/1/latest
   ```

2. 获取不存在设备的最新数据:
   ```
   GET /api/monitoring-data/device/999/latest
   ```
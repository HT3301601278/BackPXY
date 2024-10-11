# 水质监测系统后端

## 项目简介

这是一个基于Spring Boot的水质监测系统后端项目。该系统旨在实时监控水质数据,管理监测设备,并在异常情况下发出警报。本项目适合想要学习Spring Boot、JPA、RESTful API设计以及如何构建一个完整的后端系统的开发者。

## 技术栈

- Java 8+
- Spring Boot 2.x
- Spring Data JPA
- MySQL
- Maven

## 项目结构

```
src/main/java/org/example/backpxy/
├── BackPxyApplication.java
├── config/
│   ├── CorsConfig.java
│   └── SecurityConfig.java
├── controller/
│   ├── AlarmController.java
│   ├── AlarmThresholdController.java
│   ├── DeviceController.java
│   ├── MonitoringDataController.java
│   └── UserController.java
├── dto/
│   └── MonitoringDataDTO.java
├── model/
│   ├── AlarmRecord.java
│   ├── AlarmThreshold.java
│   ├── Device.java
│   ├── MonitoringData.java
│   └── User.java
├── repository/
│   ├── AlarmRecordRepository.java
│   ├── AlarmThresholdRepository.java
│   ├── DeviceRepository.java
│   ├── MonitoringDataRepository.java
│   └── UserRepository.java
└── service/
    ├── AlarmService.java
    ├── AlarmThresholdService.java
    ├── DeviceService.java
    ├── MonitoringDataService.java
    ├── UserService.java
    └── impl/
        ├── AlarmServiceImpl.java
        ├── AlarmThresholdServiceImpl.java
        ├── DeviceServiceImpl.java
        ├── MonitoringDataServiceImpl.java
        └── UserServiceImpl.java
```

## 核心功能模块

1. 用户管理
   - 用户注册
   - 用户登录
   - 用户信息更新

2. 设备管理
   - 添加设备
   - 更新设备信息
   - 删除设备
   - 查询设备列表

3. 监测数据管理
   - 保存监测数据
   - 查询指定时间范围内的监测数据
   - 获取最新监测数据

4. 报警管理
   - 设置报警阈值
   - 检查并创建报警记录
   - 查询报警记录
   - 更新报警状态

5. 报警阈值管理
   - 创建报警阈值
   - 更新报警阈值
   - 删除报警阈值
   - 查询设备的报警阈值

## 详细模块说明

### 1. 用户管理 (User Management)

用户管理模块负责处理用户相关的操作,包括注册、登录和信息更新。

主要类:
- `User.java`: 用户实体类
- `UserRepository.java`: 用户数据访问接口
- `UserService.java`: 用户服务接口
- `UserServiceImpl.java`: 用户服务实现类
- `UserController.java`: 用户控制器

关键功能:
- 用户注册: `UserServiceImpl.java` 中的 `registerUser` 方法
- 用户登录: `UserServiceImpl.java` 中的 `login` 方法

### 2. 设备管理 (Device Management)

设备管理模块处理与监测设备相关的操作,如添加、更新、删除和查询设备。

主要类:
- `Device.java`: 设备实体类
- `DeviceRepository.java`: 设备数据访问接口
- `DeviceService.java`: 设备服务接口
- `DeviceServiceImpl.java`: 设备服务实现类
- `DeviceController.java`: 设备控制器

关键功能:
- 添加设备: `DeviceServiceImpl.java` 中的 `addDevice` 方法
- 更新设备状态: `DeviceServiceImpl.java` 中的 `updateDeviceStatus` 方法

### 3. 监测数据管理 (Monitoring Data Management)

监测数据管理模块负责处理水质监测数据的保存和查询。

主要类:
- `MonitoringData.java`: 监测数据实体类
- `MonitoringDataDTO.java`: 监测数据传输对象
- `MonitoringDataRepository.java`: 监测数据数据访问接口
- `MonitoringDataService.java`: 监测数据服务接口
- `MonitoringDataServiceImpl.java`: 监测数据服务实现类
- `MonitoringDataController.java`: 监测数据控制器

关键功能:
- 保存监测数据: `MonitoringDataServiceImpl.java` 中的 `saveData` 方法
- 查询最新监测数据: `MonitoringDataServiceImpl.java` 中的 `findLatestByDeviceId` 方法

### 4. 报警管理 (Alarm Management)

报警管理模块负责处理报警相关的操作,包括创建报警记录、查询报警和更新报警状态。

主要类:
- `AlarmRecord.java`: 报警记录实体类
- `AlarmRecordRepository.java`: 报警记录数据访问接口
- `AlarmService.java`: 报警服务接口
- `AlarmServiceImpl.java`: 报警服务实现类
- `AlarmController.java`: 报警控制器

关键功能:
- 检查并创建报警: `AlarmServiceImpl.java` 中的 `checkAndCreateAlarm` 方法
- 获取报警统计信息: `AlarmServiceImpl.java` 中的 `getAlarmCountByDeviceAndTimeRange` 和 `getAlarmFrequencyByType` 方法

### 5. 报警阈值管理 (Alarm Threshold Management)

报警阈值管理模块处理报警阈值的设置和管理。

主要类:
- `AlarmThreshold.java`: 报警阈值实体类
- `AlarmThresholdRepository.java`: 报警阈值数据访问接口
- `AlarmThresholdService.java`: 报警阈值服务接口
- `AlarmThresholdServiceImpl.java`: 报警阈值服务实现类
- `AlarmThresholdController.java`: 报警阈值控制器

关键功能:
- 创建报警阈值: `AlarmThresholdServiceImpl.java` 中的 `createThreshold` 方法
- 获取设备的报警阈值: `AlarmThresholdServiceImpl.java` 中的 `getThresholdsByDeviceId` 方法

## 配置说明

1. 数据库配置

数据库配置位于 `src/main/resources/application.properties` 文件中:

```properties
spring.datasource.url=jdbc:mysql://localhost:3305/water_monitoring?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

2. JPA配置

JPA相关配置也在 `application.properties` 文件中:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.jpa.open-in-view=true
```

3. 安全配置

安全配置位于 `SecurityConfig.java` 文件中,目前配置为允许所有请求,禁用了CSRF保护和基本认证。

4. CORS配置

跨域资源共享(CORS)配置位于 `CorsConfig.java` 文件中,允许来自 `http://localhost:8081` 的请求。

## 如何运行项目

1. 确保您的系统中已安装Java 8+和Maven。
2. 克隆项目到本地。
3. 在项目根目录下运行 `mvn clean install` 命令来构建项目。
4. 运行 `mvn spring-boot:run` 命令来启动项目。
5. 项目将在 `http://localhost:8080` 上运行。

## API文档

项目中的主要API包括:

1. 用户API: `/api/users`
   - POST `/api/users/register`: 注册新用户
   - POST `/api/users/login`: 用户登录

2. 设备API: `/api/devices`
   - POST `/api/devices`: 添加新设备
   - GET `/api/devices`: 获取所有设备
   - GET `/api/devices/{id}`: 获取指定ID的设备
   - PUT `/api/devices/{id}`: 更新指定ID的设备
   - DELETE `/api/devices/{id}`: 删除指定ID的设备

3. 监测数据API: `/api/monitoring-data`
   - POST `/api/monitoring-data`: 保存监测数据
   - GET `/api/monitoring-data/device/{deviceId}`: 获取指定设备的监测数据
   - GET `/api/monitoring-data/device/{deviceId}/latest`: 获取指定设备的最新监测数据

4. 报警API: `/api/alarms`
   - GET `/api/alarms/device/{deviceId}`: 获取指定设备的报警记录
   - PUT `/api/alarms/{alarmId}/status`: 更新报警状态
   - GET `/api/alarms/statistics/count`: 获取报警统计数量
   - GET `/api/alarms/statistics/frequency`: 获取报警频率统计

5. 报警阈值API: `/api/alarm-thresholds`
   - POST `/api/alarm-thresholds`: 创建新的报警阈值
   - PUT `/api/alarm-thresholds/{id}`: 更新指定ID的报警阈值
   - DELETE `/api/alarm-thresholds/{id}`: 删除指定ID的报警阈值
   - GET `/api/alarm-thresholds/device/{deviceId}`: 获取指定设备的报警阈值

## 学习建议

1. 首先熟悉项目的整体结构,了解各个包的作用。
2. 仔细阅读 `model` 包中的实体类,理解系统中的核心数据模型。
3. 研究 `repository` 包中的接口,了解如何使用Spring Data JPA进行数据访问。
4. 查看 `service` 包中的接口和实现类,理解业务逻辑的处理方式。
5. 最后研究 `controller` 包中的类,了解如何设计RESTful API。
6. 尝试运行项目,使用API测试工具(如Postman)测试各个接口。
package org.example.backpxy.controller;

import org.example.backpxy.model.AlarmRecord;
import org.example.backpxy.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<AlarmRecord>> getAlarmsByDevice(
            @PathVariable Long deviceId,
            @RequestParam(required = false, defaultValue = "ACTIVE") String status) {
        return ResponseEntity.ok(alarmService.getAlarmsByDeviceAndStatus(deviceId, status));
    }

    @PutMapping("/{alarmId}/status")
    public ResponseEntity<AlarmRecord> updateAlarmStatus(
            @PathVariable Long alarmId,
            @RequestParam String newStatus) {
        AlarmRecord updatedAlarm = alarmService.updateAlarmStatus(alarmId, newStatus);
        return updatedAlarm != null ? ResponseEntity.ok(updatedAlarm) : ResponseEntity.notFound().build();
    }

    @GetMapping("/statistics/count")
    public ResponseEntity<?> getAlarmCount(
            @RequestParam Long deviceId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date endTime) {
        try {
            logger.info("获取报警统计信息 - 设备ID: {}, 开始时间: {}, 结束时间: {}", deviceId, startTime, endTime);
            
            if (startTime.after(endTime)) {
                logger.warn("开始时间晚于结束时间");
                return ResponseEntity.badRequest().body("开始时间不能晚于结束时间");
            }
            
            long count = alarmService.getAlarmCountByDeviceAndTimeRange(deviceId, startTime, endTime);
            logger.info("报警统计信息获取成功 - 报警数量: {}", count);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            logger.error("获取报警统计信息时发生错误", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取报警统计信息失败：" + e.getMessage());
        }
    }

    @GetMapping("/statistics/frequency")
    public ResponseEntity<?> getAlarmFrequency(
            @RequestParam Long deviceId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date startTime,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") Date endTime) {
        try {
            logger.info("获取报警频率统计 - 设备ID: {}, 开始时间: {}, 结束时间: {}", deviceId, startTime, endTime);
            
            if (startTime.after(endTime)) {
                logger.warn("开始时间晚于结束时间");
                return ResponseEntity.badRequest().body("开始时间不能晚于结束时间");
            }
            
            Map<String, Long> frequency = alarmService.getAlarmFrequencyByType(deviceId, startTime, endTime);
            logger.info("报警频率统计获取成功 - 统计结果: {}", frequency);
            return ResponseEntity.ok(frequency);
        } catch (Exception e) {
            logger.error("获取报警频率统计时发生错误", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取报警频率统计失败：" + e.getMessage());
        }
    }

    @GetMapping("/device/{deviceId}/all")
    public ResponseEntity<?> getAllAlarmsByDevice(@PathVariable Long deviceId) {
        try {
            logger.info("获取设备 {} 的所有报警", deviceId);
            List<AlarmRecord> alarms = alarmService.getAllAlarmsByDevice(deviceId);
            logger.info("成功获取设备 {} 的所有报警，数量: {}", deviceId, alarms.size());
            return ResponseEntity.ok(alarms);
        } catch (Exception e) {
            logger.error("获取设备 {} 的所有报警时发生错误", deviceId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取报警失败：" + e.getMessage());
        }
    }

    @GetMapping("/device/{deviceId}/active")
    public ResponseEntity<?> getActiveAlarmsByDevice(@PathVariable Long deviceId) {
        try {
            logger.info("获取设备 {} 的活跃报警", deviceId);
            List<AlarmRecord> activeAlarms = alarmService.getActiveAlarmsByDevice(deviceId);
            logger.info("成功获取设备 {} 的活跃报警，数量: {}", deviceId, activeAlarms.size());
            return ResponseEntity.ok(activeAlarms);
        } catch (Exception e) {
            logger.error("获取设备 {} 的活跃报警时发生错误", deviceId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取活跃报警失败：" + e.getMessage());
        }
    }
}
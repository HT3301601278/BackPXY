package org.example.backpxy.controller;

import org.example.backpxy.model.AlarmRecord;
import org.example.backpxy.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

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
    public ResponseEntity<Long> getAlarmCount(
            @RequestParam Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime) {
        return ResponseEntity.ok(alarmService.getAlarmCountByDeviceAndTimeRange(deviceId, startTime, endTime));
    }

    @GetMapping("/statistics/frequency")
    public ResponseEntity<Map<String, Long>> getAlarmFrequency(
            @RequestParam Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime) {
        return ResponseEntity.ok(alarmService.getAlarmFrequencyByType(deviceId, startTime, endTime));
    }
}
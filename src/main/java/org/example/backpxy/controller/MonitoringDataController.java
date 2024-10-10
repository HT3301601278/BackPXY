package org.example.backpxy.controller;

import org.example.backpxy.model.MonitoringData;
import org.example.backpxy.service.MonitoringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/monitoring-data")
public class MonitoringDataController {

    @Autowired
    private MonitoringDataService monitoringDataService;

    @PostMapping
    public ResponseEntity<MonitoringData> saveData(@RequestBody MonitoringData data) {
        return ResponseEntity.ok(monitoringDataService.saveData(data));
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<MonitoringData>> getDataByDeviceAndTimeRange(
            @PathVariable Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime) {
        return ResponseEntity.ok(monitoringDataService.findByDeviceIdAndTimeRange(deviceId, startTime, endTime));
    }

    @GetMapping("/device/{deviceId}/latest")
    public ResponseEntity<MonitoringData> getLatestDataByDevice(@PathVariable Long deviceId) {
        MonitoringData data = monitoringDataService.findLatestByDeviceId(deviceId);
        return data != null ? ResponseEntity.ok(data) : ResponseEntity.notFound().build();
    }
}
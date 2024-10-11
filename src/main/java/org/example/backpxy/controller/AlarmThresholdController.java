package org.example.backpxy.controller;

import org.example.backpxy.model.AlarmThreshold;
import org.example.backpxy.model.Device;
import org.example.backpxy.service.AlarmThresholdService;
import org.example.backpxy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/alarm-thresholds")
public class AlarmThresholdController {

    @Autowired
    private AlarmThresholdService alarmThresholdService;

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> createThreshold(@RequestBody Map<String, Object> request) {
        try {
            Long deviceId = Long.parseLong(request.get("deviceId").toString());
            Device device = deviceService.findById(deviceId);
            if (device == null) {
                return ResponseEntity.badRequest().body("设备不存在");
            }

            AlarmThreshold threshold = new AlarmThreshold();
            threshold.setDevice(device);
            threshold.setMetricName((String) request.get("metricName"));
            threshold.setMinThreshold((Double) request.get("minThreshold"));
            threshold.setMaxThreshold((Double) request.get("maxThreshold"));

            AlarmThreshold createdThreshold = alarmThresholdService.createThreshold(threshold);
            return ResponseEntity.ok(createdThreshold);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("创建报警阈值失败：" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateThreshold(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            AlarmThreshold existingThreshold = alarmThresholdService.findById(id);
            if (existingThreshold == null) {
                return ResponseEntity.notFound().build();
            }

            existingThreshold.setMetricName((String) request.get("metricName"));
            existingThreshold.setMinThreshold((Double) request.get("minThreshold"));
            existingThreshold.setMaxThreshold((Double) request.get("maxThreshold"));

            AlarmThreshold updatedThreshold = alarmThresholdService.updateThreshold(existingThreshold);
            return ResponseEntity.ok(updatedThreshold);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("更新报警阈值失败：" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteThreshold(@PathVariable Long id) {
        try {
            alarmThresholdService.deleteThreshold(id);
            return ResponseEntity.ok().body("报警阈值已成功删除");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("删除报警阈值失败：" + e.getMessage());
        }
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<?> getThresholdsByDevice(@PathVariable Long deviceId) {
        try {
            List<AlarmThreshold> thresholds = alarmThresholdService.getThresholdsByDeviceId(deviceId);
            if (thresholds.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(thresholds);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("获取报警阈值失败：" + e.getMessage());
        }
    }

    // Implement other endpoints
    // ...
}

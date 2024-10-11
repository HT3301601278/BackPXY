package org.example.backpxy.controller;

import org.example.backpxy.model.Device;
import org.example.backpxy.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        logger.info("收到添加设备请求: {}", device);
        try {
            if (device.getName() == null || device.getName().trim().isEmpty()) {
                logger.warn("设备名称为空");
                return ResponseEntity.badRequest().body("设备名称不能为空");
            }
            if (device.getType() == null || device.getType().trim().isEmpty()) {
                logger.warn("设备类型为空");
                return ResponseEntity.badRequest().body("设备类型不能为空");
            }
            if (device.getStatus() == null || device.getStatus().trim().isEmpty()) {
                logger.warn("设备状态为空");
                return ResponseEntity.badRequest().body("设备状态不能为空");
            }
            Device savedDevice = deviceService.addDevice(device);
            logger.info("设备添加成功: {}", savedDevice);
            return ResponseEntity.ok(savedDevice);
        } catch (Exception e) {
            logger.error("添加设备时发生错误", e);
            return ResponseEntity.badRequest().body("添加设备失败：" + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        device.setId(id);
        return ResponseEntity.ok(deviceService.updateDevice(device));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        Device device = deviceService.findById(id);
        return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.findAllDevices());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Device>> getDevicesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(deviceService.findByStatus(status));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Device> updateDeviceStatus(@PathVariable Long id, @RequestParam String status) {
        deviceService.updateDeviceStatus(id, status);
        return ResponseEntity.ok(deviceService.findById(id));
    }
}

package org.example.backpxy.service;

import org.example.backpxy.model.Device;

import java.util.List;

public interface DeviceService {
    Device addDevice(Device device) throws IllegalArgumentException;
    Device updateDevice(Device device);
    void deleteDevice(Long id);
    Device findById(Long id);
    List<Device> findAllDevices();
    List<Device> findByStatus(String status);
    void updateDeviceStatus(Long deviceId, String status);
}

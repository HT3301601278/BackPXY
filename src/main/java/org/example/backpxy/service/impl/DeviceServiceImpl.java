package org.example.backpxy.service.impl;

import org.example.backpxy.model.Device;
import org.example.backpxy.repository.DeviceRepository;
import org.example.backpxy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Device addDevice(Device device) throws IllegalArgumentException {
        if (device.getName() == null || device.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("设备名称不能为空");
        }
        if (device.getType() == null || device.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("设备类型不能为空");
        }
        return deviceRepository.save(device);
    }

    @Override
    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public Device findById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Device> findAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<Device> findByStatus(String status) {
        return deviceRepository.findByStatus(status);
    }

    @Override
    public void updateDeviceStatus(Long deviceId, String status) {
        Device device = findById(deviceId);
        if (device != null) {
            device.setStatus(status);
            deviceRepository.save(device);
        }
    }
}
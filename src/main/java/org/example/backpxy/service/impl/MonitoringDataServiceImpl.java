package org.example.backpxy.service.impl;

import org.example.backpxy.dto.MonitoringDataDTO;
import org.example.backpxy.model.Device;
import org.example.backpxy.model.MonitoringData;
import org.example.backpxy.repository.MonitoringDataRepository;
import org.example.backpxy.service.AlarmService;
import org.example.backpxy.service.DeviceService;
import org.example.backpxy.service.MonitoringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MonitoringDataServiceImpl implements MonitoringDataService {

    @Autowired
    private MonitoringDataRepository monitoringDataRepository;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private DeviceService deviceService;

    @Override
    public MonitoringData saveData(MonitoringDataDTO dataDTO) {
        Device device = deviceService.findById(dataDTO.getDeviceId());
        if (device == null) {
            throw new RuntimeException("Device not found with id: " + dataDTO.getDeviceId());
        }

        MonitoringData data = new MonitoringData();
        data.setDevice(device);
        data.setTemperature(dataDTO.getTemperature());
        data.setWaterLevel(dataDTO.getWaterLevel());
        data.setTimestamp(dataDTO.getTimestamp());

        MonitoringData savedData = monitoringDataRepository.save(data);
        alarmService.checkAndCreateAlarm(savedData);
        return savedData;
    }

    @Override
    public List<MonitoringData> findByDeviceIdAndTimeRange(Long deviceId, Date startTime, Date endTime) {
        Device device = deviceService.findById(deviceId);
        if (device == null) {
            throw new RuntimeException("设备不存在");
        }
        return monitoringDataRepository.findByDeviceIdAndTimestampBetween(deviceId, startTime, endTime);
    }

    @Override
    public MonitoringData findLatestByDeviceId(Long deviceId) {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<MonitoringData> latestData = monitoringDataRepository.findLatestByDeviceId(deviceId, pageRequest);
        return latestData.isEmpty() ? null : latestData.get(0);
    }
}

package org.example.backpxy.service.impl;

import org.example.backpxy.model.MonitoringData;
import org.example.backpxy.repository.MonitoringDataRepository;
import org.example.backpxy.service.AlarmService;
import org.example.backpxy.service.MonitoringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;

@Service
public class MonitoringDataServiceImpl implements MonitoringDataService {

    @Autowired
    private MonitoringDataRepository monitoringDataRepository;

    @Autowired
    private AlarmService alarmService;

    @Override
    public MonitoringData saveData(MonitoringData data) {
        MonitoringData savedData = monitoringDataRepository.save(data);
        alarmService.checkAndCreateAlarm(savedData);
        return savedData;
    }

    @Override
    public List<MonitoringData> findByDeviceIdAndTimeRange(Long deviceId, Date startTime, Date endTime) {
        return monitoringDataRepository.findByDeviceIdAndTimestampBetween(deviceId, startTime, endTime);
    }

    @Override
    public MonitoringData findLatestByDeviceId(Long deviceId) {
        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "timestamp"));
        List<MonitoringData> latestData = monitoringDataRepository.findLatestByDeviceId(deviceId, pageRequest);
        return latestData.isEmpty() ? null : latestData.get(0);
    }
}
package org.example.backpxy.service;

import org.example.backpxy.dto.MonitoringDataDTO;
import org.example.backpxy.model.MonitoringData;

import java.util.Date;
import java.util.List;

public interface MonitoringDataService {
    MonitoringData saveData(MonitoringDataDTO dataDTO);
    List<MonitoringData> findByDeviceIdAndTimeRange(Long deviceId, Date startTime, Date endTime);
    MonitoringData findLatestByDeviceId(Long deviceId);
}

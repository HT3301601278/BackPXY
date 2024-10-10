package org.example.backpxy.service;

import org.example.backpxy.model.AlarmRecord;
import org.example.backpxy.model.MonitoringData;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AlarmService {
    void checkAndCreateAlarm(MonitoringData data);
    List<AlarmRecord> getAlarmsByDeviceAndStatus(Long deviceId, String status);
    AlarmRecord updateAlarmStatus(Long alarmId, String newStatus);
    long getAlarmCountByDeviceAndTimeRange(Long deviceId, Date startTime, Date endTime);
    Map<String, Long> getAlarmFrequencyByType(Long deviceId, Date startTime, Date endTime);
}
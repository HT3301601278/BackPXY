package org.example.backpxy.service.impl;

import org.example.backpxy.model.AlarmRecord;
import org.example.backpxy.model.AlarmThreshold;
import org.example.backpxy.model.Device;
import org.example.backpxy.model.MonitoringData;
import org.example.backpxy.repository.AlarmRecordRepository;
import org.example.backpxy.service.AlarmService;
import org.example.backpxy.service.AlarmThresholdService;
import org.example.backpxy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlarmServiceImpl implements AlarmService {

    private static final Logger logger = LoggerFactory.getLogger(AlarmServiceImpl.class);

    @Autowired
    private AlarmRecordRepository alarmRecordRepository;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private AlarmThresholdService alarmThresholdService;

    @Override
    public void checkAndCreateAlarm(MonitoringData data) {
        Device device = data.getDevice();
        List<AlarmThreshold> thresholds = alarmThresholdService.getThresholdsByDeviceId(device.getId());
        
        for (AlarmThreshold threshold : thresholds) {
            if (isAlarmConditionMet(data, threshold)) {
                AlarmRecord alarm = new AlarmRecord();
                alarm.setDevice(device);
                alarm.setAlarmType(threshold.getMetricName());
                alarm.setThresholdValue(threshold.getMaxThreshold());
                alarm.setCurrentValue(getCurrentValue(data, threshold.getMetricName()));
                alarm.setStatus("ACTIVE");
                alarm.setAlarmTime(new Date());
                alarmRecordRepository.save(alarm);
            }
        }
    }

    @Override
    public List<AlarmRecord> getAlarmsByDeviceAndStatus(Long deviceId, String status) {
        return alarmRecordRepository.findByDeviceIdAndStatus(deviceId, status);
    }

    @Override
    public AlarmRecord updateAlarmStatus(Long alarmId, String newStatus) {
        AlarmRecord alarm = alarmRecordRepository.findById(alarmId).orElse(null);
        if (alarm != null) {
            alarm.setStatus(newStatus);
            return alarmRecordRepository.save(alarm);
        }
        return null;
    }

    private boolean isAlarmConditionMet(MonitoringData data, AlarmThreshold threshold) {
        Double currentValue = getCurrentValue(data, threshold.getMetricName());
        return currentValue != null && 
               (currentValue < threshold.getMinThreshold() || currentValue > threshold.getMaxThreshold());
    }

    private String determineAlarmType(MonitoringData data) {
        // 实现报警类型判断逻辑
        return "";
    }

    private Double getThresholdValue(Device device, String alarmType) {
        // 获取设备的报警阈值
        return 0.0;
    }

    private Double getCurrentValue(MonitoringData data, String metricName) {
        switch (metricName) {
            case "temperature":
                return data.getTemperature();
            case "waterLevel":
                return data.getWaterLevel();
            default:
                return null;
        }
    }

    @Override
    public long getAlarmCountByDeviceAndTimeRange(Long deviceId, Date startTime, Date endTime) {
        logger.info("正在获取设备 {} 在 {} 到 {} 之间的报警数量", deviceId, startTime, endTime);
        long count = alarmRecordRepository.countByDeviceIdAndAlarmTimeBetween(deviceId, startTime, endTime);
        logger.info("设备 {} 在指定时间范围内的报警数量为: {}", deviceId, count);
        return count;
    }

    @Override
    public Map<String, Long> getAlarmFrequencyByType(Long deviceId, Date startTime, Date endTime) {
        logger.info("正在获取设备 {} 在 {} 到 {} 之间的报警频率", deviceId, startTime, endTime);
        List<AlarmRecord> alarms = alarmRecordRepository.findByDeviceIdAndAlarmTimeBetween(deviceId, startTime, endTime);
        Map<String, Long> frequency = new HashMap<>();
        for (AlarmRecord alarm : alarms) {
            frequency.put(alarm.getAlarmType(), frequency.getOrDefault(alarm.getAlarmType(), 0L) + 1);
        }
        logger.info("设备 {} 在指定时间范围内的报警频率为: {}", deviceId, frequency);
        return frequency;
    }
}

package org.example.backpxy.service;

import org.example.backpxy.model.AlarmThreshold;

import java.util.List;

public interface AlarmThresholdService {
    AlarmThreshold createThreshold(AlarmThreshold threshold);
    AlarmThreshold updateThreshold(AlarmThreshold threshold);
    void deleteThreshold(Long id);
    List<AlarmThreshold> getThresholdsByDeviceId(Long deviceId);
}
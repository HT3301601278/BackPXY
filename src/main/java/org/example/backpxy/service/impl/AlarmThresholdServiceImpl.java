package org.example.backpxy.service.impl;

import org.example.backpxy.model.AlarmThreshold;
import org.example.backpxy.repository.AlarmThresholdRepository;
import org.example.backpxy.service.AlarmThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmThresholdServiceImpl implements AlarmThresholdService {

    @Autowired
    private AlarmThresholdRepository alarmThresholdRepository;

    @Override
    public AlarmThreshold createThreshold(AlarmThreshold threshold) {
        return alarmThresholdRepository.save(threshold);
    }

    @Override
    public AlarmThreshold updateThreshold(AlarmThreshold threshold) {
        return alarmThresholdRepository.save(threshold);
    }

    @Override
    public void deleteThreshold(Long id) {
        alarmThresholdRepository.deleteById(id);
    }

    @Override
    public List<AlarmThreshold> getThresholdsByDeviceId(Long deviceId) {
        return alarmThresholdRepository.findByDeviceId(deviceId);
    }
}
package org.example.backpxy.repository;

import org.example.backpxy.model.AlarmThreshold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmThresholdRepository extends JpaRepository<AlarmThreshold, Long> {
    List<AlarmThreshold> findByDeviceId(Long deviceId);
}
package org.example.backpxy.repository;

import org.example.backpxy.model.AlarmRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AlarmRecordRepository extends JpaRepository<AlarmRecord, Long> {
    List<AlarmRecord> findByDeviceIdAndStatus(Long deviceId, String status);
    long countByDeviceIdAndAlarmTimeBetween(Long deviceId, Date startTime, Date endTime);
    List<AlarmRecord> findByDeviceIdAndAlarmTimeBetween(Long deviceId, Date startTime, Date endTime);
}
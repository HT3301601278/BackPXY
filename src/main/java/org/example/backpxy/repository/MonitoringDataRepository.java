package org.example.backpxy.repository;

import org.example.backpxy.model.MonitoringData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MonitoringDataRepository extends JpaRepository<MonitoringData, Long> {
    List<MonitoringData> findByDeviceIdAndTimestampBetween(Long deviceId, Date startTime, Date endTime);

    @Query("SELECT m FROM MonitoringData m WHERE m.device.id = :deviceId ORDER BY m.timestamp DESC")
    List<MonitoringData> findLatestByDeviceId(@Param("deviceId") Long deviceId, Pageable pageable);
}

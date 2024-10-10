package org.example.backpxy.model;

import javax.persistence.*;

@Entity
@Table(name = "alarm_thresholds")
public class AlarmThreshold {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;

    @Column(nullable = false)
    private String metricName;

    private Double minThreshold;

    private Double maxThreshold;

    // Getters and setters
    // ...
}
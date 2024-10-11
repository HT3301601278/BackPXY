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

    // Getter and Setter for device
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Column(nullable = false)
    private String metricName;

    private Double minThreshold;

    private Double maxThreshold;

    // Getters and setters
    public String getMetricName() {
        return metricName;
    }

    public Double getMinThreshold() {
        return minThreshold;
    }

    public Double getMaxThreshold() {
        return maxThreshold;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setMinThreshold(Double minThreshold) {
        this.minThreshold = minThreshold;
    }

    public void setMaxThreshold(Double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }
}
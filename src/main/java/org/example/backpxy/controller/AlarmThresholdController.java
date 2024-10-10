package org.example.backpxy.controller;

import org.example.backpxy.model.AlarmThreshold;
import org.example.backpxy.service.AlarmThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarm-thresholds")
public class AlarmThresholdController {

    @Autowired
    private AlarmThresholdService alarmThresholdService;

    @PostMapping
    public ResponseEntity<AlarmThreshold> createThreshold(@RequestBody AlarmThreshold threshold) {
        return ResponseEntity.ok(alarmThresholdService.createThreshold(threshold));
    }

    // Implement other endpoints
    // ...
}
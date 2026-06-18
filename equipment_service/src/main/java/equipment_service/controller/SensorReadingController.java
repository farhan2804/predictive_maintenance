package equipment_service.controller;

import equipment_service.entity.SensorReading;
import equipment_service.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @GetMapping
    public List<SensorReading> getAllReadings() {

        return sensorReadingService.getAllReadings();
    }

    @PostMapping
    public SensorReading saveReading(@RequestBody SensorReading reading) {
        return sensorReadingService.saveReading(reading);
    }
}
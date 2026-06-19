package equipment_service.controller;

import equipment_service.entity.SensorReading;
import equipment_service.service.SensorReadingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/sensors")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    public SensorReadingController(SensorReadingService sensorReadingService) {
        this.sensorReadingService = sensorReadingService;
    }

    @GetMapping("/latest")
    public SensorReading getLatestReading() {
        return sensorReadingService.getLatestReading();
    }

    @GetMapping
    public List<SensorReading> getAllReadings() {

        return sensorReadingService.getAllReadings();
    }

    @PostMapping
    public SensorReading saveReading(@RequestBody SensorReading reading) {
        return sensorReadingService.saveReading(reading);
    }

    @GetMapping("/machine/{machineId}")
    public List<SensorReading> getReadingsByMachineId(
            @PathVariable Long machineId) {

        return sensorReadingService.getReadingsByMachineId(machineId);
    }

    @GetMapping("/machine/{machineId}/latest")
    public SensorReading getLatestReadingByMachineId(
            @PathVariable Long machineId) {

        return sensorReadingService
                .getLatestReadingByMachineId(machineId);
    }

    @GetMapping("/export")
    public List<SensorReading> exportData() {
        return sensorReadingService.getAllReadings();
    }
}
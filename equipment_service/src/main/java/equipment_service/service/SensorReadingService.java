package equipment_service.service;

import equipment_service.entity.SensorReading;
import equipment_service.repository.SensorReadingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorReadingService {

    private final SensorReadingRepository sensorReadingRepository;

    public SensorReadingService(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    public SensorReading saveReading(SensorReading reading) {
        return sensorReadingRepository.save(reading);
    }

    public List<SensorReading> getAllReadings() {
        return sensorReadingRepository.findAll();
    }
}
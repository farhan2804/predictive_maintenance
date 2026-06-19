package equipment_service.simulator;

import equipment_service.entity.Machine;
import equipment_service.entity.SensorReading;
import equipment_service.repository.MachineRepository;
import equipment_service.repository.SensorReadingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class SensorSimulatorService {

    private final SensorReadingRepository sensorReadingRepository;
    private final MachineRepository machineRepository;
    private final Random random = new Random();

    public SensorSimulatorService(
            SensorReadingRepository sensorReadingRepository,
            MachineRepository machineRepository) {

        this.sensorReadingRepository = sensorReadingRepository;
        this.machineRepository = machineRepository;
    }

    @Scheduled(fixedRate = 5000)
    public void generateSensorData() {

        SensorReading reading = new SensorReading();

        List<Machine> machines = machineRepository.findAll();

        if (!machines.isEmpty()) {

            Machine machine =
                    machines.get(random.nextInt(machines.size()));

            reading.setMachine(machine);
        }

        reading.setReadingTime(LocalDateTime.now());

        reading.setTemperature(60 + random.nextDouble() * 20);
        reading.setVibration(1 + random.nextDouble() * 4);
        reading.setPressure(10 + random.nextDouble() * 5);
        reading.setRpm(1400 + random.nextDouble() * 200);
        reading.setCurrent(20 + random.nextDouble() * 10);

        sensorReadingRepository.save(reading);

        System.out.println("Sensor Reading Generated");
    }
}
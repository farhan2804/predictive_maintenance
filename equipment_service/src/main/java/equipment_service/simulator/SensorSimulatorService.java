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

            Machine machine = machines.get(random.nextInt(machines.size()));

            reading.setMachine(machine);
        }

        reading.setReadingTime(LocalDateTime.now());

        int condition = random.nextInt(100);

        if (condition < 70) {

            reading.setHealthStatus("HEALTHY");

            reading.setTemperature(60 + random.nextDouble() * 15);
            reading.setVibration(1 + random.nextDouble() * 2);
            reading.setPressure(10 + random.nextDouble() * 3);
            reading.setRpm(1450 + random.nextDouble() * 100);
            reading.setCurrent(20 + random.nextDouble() * 5);

        } else if (condition < 90) {

            reading.setHealthStatus("WARNING");

            reading.setTemperature(80 + random.nextDouble() * 10);
            reading.setVibration(3 + random.nextDouble() * 3);
            reading.setPressure(14 + random.nextDouble() * 3);
            reading.setRpm(1550 + random.nextDouble() * 100);
            reading.setCurrent(25 + random.nextDouble() * 5);

        } else {

            reading.setHealthStatus("CRITICAL");

            reading.setTemperature(95 + random.nextDouble() * 15);
            reading.setVibration(7 + random.nextDouble() * 3);
            reading.setPressure(18 + random.nextDouble() * 4);
            reading.setRpm(1700 + random.nextDouble() * 150);
            reading.setCurrent(30 + random.nextDouble() * 8);
        }

        sensorReadingRepository.save(reading);

        System.out.println("Sensor Reading Generated");
    }
}
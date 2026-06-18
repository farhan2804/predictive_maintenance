package equipment_service.config;

import equipment_service.entity.Machine;
import equipment_service.repository.MachineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(MachineRepository machineRepository) {
        return args -> {

            if(machineRepository.count() == 0) {

                Machine pumpA = new Machine();
                pumpA.setName("Pump A");
                pumpA.setType("Pump");
                pumpA.setLocation("Zone 1");
                pumpA.setStatus("RUNNING");

                Machine pumpB = new Machine();
                pumpB.setName("Pump B");
                pumpB.setType("Pump");
                pumpB.setLocation("Zone 2");
                pumpB.setStatus("RUNNING");

                Machine compressor = new Machine();
                compressor.setName("Compressor A");
                compressor.setType("Compressor");
                compressor.setLocation("Zone 3");
                compressor.setStatus("RUNNING");

                Machine motor = new Machine();
                motor.setName("Motor A");
                motor.setType("Motor");
                motor.setLocation("Zone 4");
                motor.setStatus("RUNNING");

                machineRepository.save(pumpA);
                machineRepository.save(pumpB);
                machineRepository.save(compressor);
                machineRepository.save(motor);
            }
        };
    }
}
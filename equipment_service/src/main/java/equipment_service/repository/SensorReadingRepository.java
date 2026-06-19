package equipment_service.repository;

import equipment_service.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    SensorReading findTopByOrderByIdDesc();

    List<SensorReading> findByMachineId(Long machineId);
    SensorReading findTopByMachineIdOrderByIdDesc(Long machineId);
}
package equipment_service.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import equipment_service.entity.Machine;
import equipment_service.service.MachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
public class MachineController {

    private final MachineService machineService;

    public MachineController(MachineService machineService) {
        this.machineService = machineService;
    }

    @GetMapping("/api/machines")
    public List<Machine> getAllMachines() {
        return machineService.getAllMachines();
    }
}
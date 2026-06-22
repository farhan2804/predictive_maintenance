package equipment_service.controller;

import equipment_service.dto.PredictionRequest;
import equipment_service.dto.PredictionResponse;
import equipment_service.service.PredictionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(
            PredictionService predictionService) {

        this.predictionService = predictionService;
    }

    @PostMapping
    public PredictionResponse predict(
            @RequestBody PredictionRequest request) {

        return predictionService.predict(request);
    }
}
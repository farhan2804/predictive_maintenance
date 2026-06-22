package equipment_service.service;

import equipment_service.dto.PredictionRequest;
import equipment_service.dto.PredictionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public PredictionResponse predict(
            PredictionRequest request) {

        String url = "http://localhost:8000/predict";

        ResponseEntity<PredictionResponse> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        PredictionResponse.class
                );

        return response.getBody();
    }
}
package equipment_service.service;

import equipment_service.dto.PredictionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String predict(PredictionRequest request) {

        String url = "http://localhost:8000/predict";

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        url,
                        request,
                        String.class
                );

        return response.getBody();
    }
}
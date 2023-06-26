package numble.api.health.controller;

import numble.api.health.response.HealthCheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponse> healthCheck() {
        HealthCheckResponse healthCheckResponse = HealthCheckResponse.builder()
                .status(true)
                .health("ok")
                .build();

       return ResponseEntity.ok(healthCheckResponse);
    }

}

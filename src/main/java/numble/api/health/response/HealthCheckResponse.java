package numble.api.health.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HealthCheckResponse {

    private boolean status;
    private String health;

}

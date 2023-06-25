package numble.api.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
public class ApiErrorResponseDto {

        private int status;
        private List<String> errorMessages;

        public static ApiErrorResponseDto of(HttpStatus status, List<String> errorMessages) {
            return ApiErrorResponseDto.builder()
                    .status(status.value())
                    .errorMessages(errorMessages)
                    .build();
        }

}

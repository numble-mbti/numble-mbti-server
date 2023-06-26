package numble.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseDto<T>{

    private T data;
    private String message;

    public static <T> ApiResponseDto<T> create(T data) {
        return new ApiResponseDto<>(data, Strings.EMPTY);
    }

    public static <T> ApiResponseDto<T> create(T data, String message) {
        return new ApiResponseDto<>(data, message);
    }

}


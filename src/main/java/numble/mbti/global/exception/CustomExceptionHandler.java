package numble.mbti.global.exception;

import numble.api.dto.ApiErrorResponseDto;
import numble.api.dto.ApiResponseDto;
import numble.api.mbti.controller.response.MbtiFeaturesResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponseDto<MbtiFeaturesResponse>> handleBusinessException(BusinessException ex) {
        var errorResponse = ApiResponseDto.<MbtiFeaturesResponse>create(null, ex.getErrorCode().getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    //TO-DO 예외 클레스 추가
    /*
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
        var errorResponse = new ApiErrorResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponseDto> handleValidationException(ValidationException ex) {
        var errorResponse = new ApiErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(Exception ex) {
        var errorResponse = new ApiErrorResponseDto(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

package numble.mbti.global.exception;

import lombok.Getter;
import numble.mbti.global.exception.constant.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String extraMessage) {
        super(String.format(errorCode.getMessage(), extraMessage));
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return this.errorCode.getStatus();
    }
}

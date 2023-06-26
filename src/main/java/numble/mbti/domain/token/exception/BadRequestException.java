package numble.mbti.domain.token.exception;

public class BadRequestException extends RuntimeException {
    private String errorCode = "BAD_REQUEST";
    private String clientMessage = "요청값이 잘못되었습니다.";

    public BadRequestException(String serverMessage, String clientMessage, String errorCode) {
        super(serverMessage);
        this.clientMessage = clientMessage;
        this.errorCode = errorCode;
    }

    public String getClientMessage() {
        return clientMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

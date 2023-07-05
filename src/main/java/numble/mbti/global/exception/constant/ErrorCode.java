package numble.mbti.global.exception.constant;


import lombok.Getter;

@Getter
public enum ErrorCode {

    // 인증
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 빈값입니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다."),
    NOT_VALID_TOKEN(401, "유효하지않은 토큰 입니다."),
    ACCESS_TOKEN_EXPIRED(401, "해당 access token은 만료됐습니다."),
    FORBIDDEN_NOT_ADMIN(403, "관리자 권한이 없습니다."),

    //
    NOT_EXISTS_INFO(404,"해당하는 정보를 찾을 수 없습니다.");



    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}

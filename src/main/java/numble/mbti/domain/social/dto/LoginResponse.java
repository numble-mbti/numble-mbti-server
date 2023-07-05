package numble.mbti.domain.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginResponse {
    @JsonProperty("access_token")
    private String accessToken;
    private String nickname;
    private String email;

    public LoginResponse(String accessToken, String nickname, String email) {
        this.accessToken = accessToken;
        this.nickname = nickname;
        this.email = email;
    }
}

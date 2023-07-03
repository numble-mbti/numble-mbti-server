package numble.mbti.domain.social.dto;

import lombok.Getter;

@Getter
public class LoginURLResponse {
    private String url;

    public LoginURLResponse(String url) {
        this.url = url;
    }
}

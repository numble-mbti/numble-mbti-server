package numble.mbti.domain.social.service;

import numble.mbti.domain.social.dto.OAuthAttributes;
import numble.mbti.domain.social.dto.OAuthToken;
import org.springframework.http.ResponseEntity;

public interface SocialOauth {
    String getOauthRedirectURL();
    ResponseEntity<String> requestToken(String code);
    OAuthToken mapToKakaoToken(ResponseEntity<String> strToken);
    ResponseEntity<String> requestUserInfo(String accessToken);
    OAuthAttributes getUserInfo(ResponseEntity<String> userInfoResponseEntity);
}

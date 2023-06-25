package numble.mbti.domain.social.service;

import numble.mbti.domain.social.dto.KakaoOAuthToken;
import numble.mbti.domain.social.dto.OAuthAttributes;
import org.springframework.http.ResponseEntity;

public interface SocialOauth {
    String getOauthRedirectURL();
    ResponseEntity<String> requestToken(String code);
    KakaoOAuthToken mapToKakaoToken(ResponseEntity<String> strToken);
    ResponseEntity<String> requestUserInfo(String accessToken);
    OAuthAttributes getUserInfo(ResponseEntity<String> userInfoResponseEntity);
}

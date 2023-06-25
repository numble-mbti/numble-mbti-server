package numble.mbti.domain.social.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.KakaoOAuthToken;
import numble.mbti.domain.social.dto.OAuthAttributes;
import numble.mbti.domain.social.dto.SocialConstant;
import numble.mbti.domain.social.entity.UserSocial;
import numble.mbti.domain.social.repository.UserSocialJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocialService {

    private final KakaoOauth kakaoOauth;
    private final UserSocialJpaRepository userSocialJpaRepository;
    /**
     *  소셜 로그인 url 반환
     */
    public String requestSocialLogin(final SocialConstant.SocialLoginType socialLoginType) {
        String redirectUrl;
        if(socialLoginType == SocialConstant.SocialLoginType.KAKAO){
            redirectUrl = kakaoOauth.getOauthRedirectURL();
            return redirectUrl;
        }else if(socialLoginType == SocialConstant.SocialLoginType.GOOGLE){
            return "GOOGLE";
        }
        else{
            throw new IllegalArgumentException("socialLoginType is not supported");
        }
    }

    public OAuthAttributes oAuthLogin(final String code) {
        ResponseEntity<String> strTokensResponseEntity = kakaoOauth.requestToken(code);
        KakaoOAuthToken kakaoOAuthToken = kakaoOauth.mapToKakaoToken(strTokensResponseEntity);
        ResponseEntity<String> userInfoResponseEntity = kakaoOauth.requestUserInfo(kakaoOAuthToken.getAccessToken());
        OAuthAttributes kakaoAttributes = kakaoOauth.getUserInfo(userInfoResponseEntity);
        return kakaoAttributes;
    }

    public boolean existsByProviderId(Long providerId) {
        return userSocialJpaRepository.existsByProviderId(providerId);
    }

    public UserSocial findByProviderId(Long providerId) {
        return userSocialJpaRepository.findByProviderId(providerId);
    }
}

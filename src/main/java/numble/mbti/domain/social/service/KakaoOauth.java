package numble.mbti.domain.social.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.KakaoOAuthToken;
import numble.mbti.domain.social.dto.OAuthAttributes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoOauth implements SocialOauth {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUrl;
    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String authorizeUrl;
    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUrl;
    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String userInfoUrl;
    @Value("${spring.security.oauth2.client.registration.kakao.scope}")
    private String scope;

    private final RestTemplate restTemplate;

    private Gson gson = new Gson();

    @Override
    public String getOauthRedirectURL() {
        Map<String, String> params = new HashMap<>();
//        params.put("scope", scope);
        params.put("response_type","code");
        params.put("client_id", clientId);
        params.put("redirect_uri", kakaoRedirectUrl);

        String parameterString = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL = authorizeUrl +"?"+parameterString;
        return redirectURL;
    }

    @Override
    public ResponseEntity<String> requestToken(String code) {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        // Content-type: application/x-www-form-urlencoded;으로 호출
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Accept", "application/json");

        params.add("grant_type", "authorization_code");
        params.add("client_id" , clientId);
        params.add("redirect_uri", kakaoRedirectUrl);
        params.add("code", code);

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> strTokensResponseEntity = restTemplate.postForEntity(tokenUrl, request, String.class);
        log.info(strTokensResponseEntity.getBody());

        if (strTokensResponseEntity.getStatusCode() != HttpStatus.OK){
            throw new JsonParseException("strTokensResponseEntity is not found");
        }
        return strTokensResponseEntity;
    }

    @Override
    public ResponseEntity<String> requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        headers.add("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> userInfoResponseEntity = restTemplate.exchange(userInfoUrl, HttpMethod.GET, request, String.class);
        log.info("userInfoResponseEntity: {} ", userInfoResponseEntity.getBody());
        return userInfoResponseEntity;
    }

    @Override
    public OAuthAttributes getUserInfo(ResponseEntity<String> userInfoResponseEntity) {
        OAuthAttributes kakaoAttributes = OAuthAttributes.of("kakao","",gson.fromJson(userInfoResponseEntity.getBody(), Map.class));
        log.info("KakaoOauth.kakaoAttributes: {}", kakaoAttributes);
        return kakaoAttributes;
    }

    @Override
    public KakaoOAuthToken mapToKakaoToken(ResponseEntity<String> strToken) {
        return gson.fromJson(strToken.getBody(), KakaoOAuthToken.class);
    }

}

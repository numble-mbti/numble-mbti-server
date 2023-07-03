package numble.mbti.domain.social.service;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.GoogleOAuthToken;
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
public class GoogleOauth implements SocialOauth {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String redirectUrl;
    @Value("${spring.security.oauth2.client.provider.google.authorization-uri}")
    private String authorizeUrl;
    @Value("${spring.security.oauth2.client.provider.google.token-uri}")
    private String tokenUrl;
    @Value("${spring.security.oauth2.client.provider.google.user-info-uri}")
    private String userInfoUrl;
    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String scope;

    private final RestTemplate restTemplate;

    private final Gson gson = new Gson();

    public String getOauthRedirectURL() {
        Map<String, String> params = new HashMap<>();
        params.put("scope", scope);
        params.put("response_type","code");
        params.put("client_id", clientId);
        params.put("redirect_uri", redirectUrl);

        String parameterString = params.entrySet().stream()
                .map(param -> param.getKey() + "=" + param.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL = authorizeUrl +"?"+parameterString;
        return redirectURL;
    }

    @Override
    public ResponseEntity<String> requestToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Accept", "application/json");

        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUrl);
        params.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);
        ResponseEntity<String> strTokensResponseEntity = restTemplate.postForEntity(tokenUrl, request, String.class);

        if(strTokensResponseEntity.getStatusCode() != HttpStatus.OK){
            throw new JsonParseException("strTokensResponseEntity is not found");
        }

        return strTokensResponseEntity;
    }

    public GoogleOAuthToken mapToKakaoToken(ResponseEntity<String> strToken) {
        return gson.fromJson(strToken.getBody(), GoogleOAuthToken.class);
    }

    @Override
    public ResponseEntity<String> requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, request, String.class);
        return response;
    }

    @Override
    public OAuthAttributes getUserInfo(ResponseEntity<String> userInfoResponseEntity) {
        OAuthAttributes googleAttributes = OAuthAttributes.of("google","", gson.fromJson(userInfoResponseEntity.getBody(), Map.class));
        log.info("googleAttributes: {}", googleAttributes);
        return googleAttributes;
    }
}

package numble.mbti.domain.social.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.LoginResponse;
import numble.mbti.domain.social.dto.OAuthAttributes;
import numble.mbti.domain.social.dto.SocialConstant;
import numble.mbti.domain.social.entity.UserSocial;
import numble.mbti.domain.social.service.SocialService;
import numble.mbti.domain.token.service.TokenService;
import numble.mbti.domain.user.entity.User;
import numble.mbti.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SocialController {
    private final SocialService socialService;
    private final UserService userService;
    private final TokenService tokenService;
    /**
     * 소셜로그인창 요구
     * */
    @GetMapping("/api/oauth2/{social}")
    public void socialLogin(@PathVariable String social, HttpServletResponse response) throws IOException {
        log.info("{} 로그인", social);
        SocialConstant.SocialLoginType socialLoginType = SocialConstant.SocialLoginType.valueOf(social.toUpperCase());
        String requestURL = socialService.requestSocialLogin(socialLoginType);
        response.sendRedirect(requestURL);
    }

    /**
     *  로그인 성공후 리다이렉트
     *  code 포함
     *  code로 토큰 발급
     * */
    @Operation(hidden = true)
    @GetMapping("/oauth2/{social}/redirect")
    public ResponseEntity<LoginResponse> redirectLogin(@PathVariable String social, @RequestParam String code) {
        // 소셜로그인 -> OAuthAttributes
        OAuthAttributes oAuthAttributes = socialService.oAuthLogin(code);

        User user;
        if(!socialService.existsByProviderId(oAuthAttributes.getProviderId())) {
            // 회원가입
            user = userService.signup(oAuthAttributes);
            // AT, RT 발급
            return ResponseEntity.ok(tokenService.save(user));
        }else {
            UserSocial userSocial = socialService.findByProviderId(oAuthAttributes.getProviderId());
            user = userSocial.getUser();

            // 유효성 검사후 발급
            LoginResponse loginResponse = new LoginResponse(tokenService.requestToken(user));
            return ResponseEntity.ok(loginResponse);
        }
    }
}

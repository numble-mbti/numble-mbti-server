package numble.mbti.domain.social.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.LoginResponse;
import numble.mbti.domain.social.dto.OAuthAttributes;
import numble.mbti.domain.social.dto.SocialConstant;
import numble.mbti.domain.social.entity.UserSocial;
import numble.mbti.domain.social.service.GoogleOauth;
import numble.mbti.domain.social.service.KakaoOauth;
import numble.mbti.domain.social.service.SocialService;
import numble.mbti.domain.token.service.TokenService;
import numble.mbti.domain.user.dto.UserDto;
import numble.mbti.domain.user.entity.User;
import numble.mbti.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SocialController {
    private final SocialService socialService;
    private final UserService userService;
    private final TokenService tokenService;
    private final GoogleOauth googleOauth;
    private final KakaoOauth kakaoOauth;
    /**
     * 소셜로그인창 요구
     * */
    @Operation(hidden = true)
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
//    @Operation(hidden = true)
//    @GetMapping("/oauth2/{social}/redirect")
    @GetMapping("/afterlogin/{social}")
    public synchronized ResponseEntity<LoginResponse> redirectLogin(@PathVariable String social, @RequestParam String code) {
        // 소셜로그인 -> OAuthAttributes
        log.info("social: {}", social);
        log.info("code: {}", code);
        OAuthAttributes oAuthAttributes;
        if (SocialConstant.SocialLoginType.valueOf(social.toUpperCase()).equals(SocialConstant.SocialLoginType.GOOGLE)){
            oAuthAttributes = socialService.oAuthLogin(code, googleOauth);
        } else {
            oAuthAttributes = socialService.oAuthLogin(code, kakaoOauth);
        }

        User user;
        if(!socialService.existsByProviderId(oAuthAttributes.getProviderId())) {
            // 회원가입
            user = userService.signup(oAuthAttributes);
            // AT, RT 발급
            return ResponseEntity.ok(tokenService.save(user));
        } else {
            UserSocial userSocial = socialService.findByProviderId(oAuthAttributes.getProviderId());
            user = userSocial.getUser();

            if(!oAuthAttributes.getEmail().equals("") && user.getEmail().equals("")){
                log.info("이메일 저장");
                user.setEmail(oAuthAttributes.getEmail());
                userService.updateUser(UserDto.toDto(user));
            }

            // 유효성 검사후 발급
            LoginResponse loginResponse = new LoginResponse(tokenService.requestToken(user), user.getNickname(), user.getEmail());
            return ResponseEntity.ok(loginResponse);
        }
    }
}

package numble.mbti.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.service.SocialService;
import numble.mbti.domain.token.jwt.AuthenticationPrincipal;
import numble.mbti.domain.token.service.TokenService;
import numble.mbti.domain.user.dto.UserDto;
import numble.mbti.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
@Tag(name = "회원 API")
public class UserController {

    private final UserService userService;
    private final SocialService socialService;
    private final TokenService tokenService;

    @Operation(summary = "회원 정보 수정")
    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@Parameter(hidden = true) @AuthenticationPrincipal Long userId, @RequestBody UserDto userDto) {
        log.info("updateUser, userId : {}", userId);

        userDto.setId(userId);
        UserDto userResponse = userService.updateUser(userDto);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "회원 조회")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> findUser(@Parameter(hidden = true) @AuthenticationPrincipal Long userId) {
        log.info("findUser, userId : {}", userId);

        UserDto userDto = userService.findUser(userId);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@Parameter(hidden = true) @AuthenticationPrincipal Long userId) {
        String logoutUrl = "https://kapi.kakao.com/v1/user/logout";
        log.info("logout, userId : {}",userId);

        String token = tokenService.findToken(userId);

        try {
            URL url = new URL(logoutUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "회원 탈퇴 (작업전)")
    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        Long userId = 1L;

        boolean result = false;
        return ResponseEntity.ok(result);
    }
}

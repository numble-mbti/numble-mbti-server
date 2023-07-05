package numble.mbti.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import numble.mbti.domain.token.jwt.AuthenticationPrincipal;
import numble.mbti.domain.user.dto.UserDto;
import numble.mbti.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
@Tag(name = "회원 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 정보 수정")
    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@AuthenticationPrincipal Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
        UserDto userResponse = userService.updateUser(userDto);
        return ResponseEntity.ok(userResponse);
    }

    @Operation(summary = "회원 조회")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> findUser(@AuthenticationPrincipal Long userId) {
        UserDto userDto = userService.findUser(userId);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "회원 탈퇴 (작업전)")
    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        Long userId = 1L;

        boolean result = false;
        return ResponseEntity.ok(result);
    }
}

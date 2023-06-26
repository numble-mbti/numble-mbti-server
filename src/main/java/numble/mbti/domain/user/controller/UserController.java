package numble.mbti.domain.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.token.jwt.AuthenticationPrincipal;
import numble.mbti.domain.user.dto.UserDto;
import numble.mbti.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@AuthenticationPrincipal Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
        UserDto userResponse = userService.updateUser(userDto);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> findUser(@AuthenticationPrincipal Long userId) {
        log.info("profile: {}", userId);
        UserDto userDto = userService.findUser(userId);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {
        Long userId = 1L;

        boolean result = false;
        return ResponseEntity.ok(result);
    }
}

package numble.api.record.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.api.record.service.RecordService;
import numble.mbti.domain.token.jwt.AuthorizationExtractor;
import numble.mbti.domain.token.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "유저 mbti 결과 저장", security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/result")
    public ResponseEntity userMbtiResult(HttpServletRequest request, @RequestBody @Valid UserMbtiResultRequest resultRequest) {

        String token = (String) request.getAttribute("token");
        Long userId = jwtTokenProvider.getUserIdFromToken(token);
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String token = (String) authentication.getCredentials();
        // String token = AuthorizationExtractor.extract(request);
        //Long userId = jwtTokenProvider.getUserIdFromToken(token);

        if (userId != null) {
            recordService.saveUserResult(userId, resultRequest);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}


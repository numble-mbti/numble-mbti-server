package numble.mbti.domain.token.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.LoginResponse;
import numble.mbti.domain.token.entity.AuthToken;
import numble.mbti.domain.token.jwt.JwtTokenProvider;
import numble.mbti.domain.token.repository.TokenJpaRepository;
import numble.mbti.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {
    private final TokenJpaRepository tokenJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long save(String jwtToken, User user) {
        AuthToken authToken = AuthToken.builder()
                .user(user)
                .accessToken(jwtToken)
                .build();
        AuthToken savedToken = tokenJpaRepository.save(authToken);
        return savedToken.getId();
    }

    @Override
    public LoginResponse save(User user) {
        String jwtToken = jwtTokenProvider.createToken(String.valueOf(user.getId()));
        log.info("getPayload {}, token {}", jwtTokenProvider.getPayload(jwtToken), jwtToken);
        AuthToken authToken = AuthToken.builder()
                .user(user)
                .accessToken(jwtToken)
                .build();
        tokenJpaRepository.save(authToken);
        return new LoginResponse(jwtToken);
    }

    @Override
    @Transactional
    public String requestToken(User user) {
        AuthToken authToken = tokenJpaRepository.findByUserId(user.getId()).orElseGet(() -> {
            AuthToken newToken = AuthToken.builder().accessToken(jwtTokenProvider.createToken(String.valueOf(user.getId()))).user(user).build();
            return tokenJpaRepository.save(newToken);
        });

        if(jwtTokenProvider.isValidateToken(authToken.getAccessToken())) {
            return authToken.getAccessToken();
        }else {
            // RT 유효 ? AT 재발급 : AT, RT 재발급
            log.info("RT를 꺼내서 판단");
            String refreshToken = "re";
            String accessToken = jwtTokenProvider.createToken(String.valueOf(user.getId()));
            if(!jwtTokenProvider.isValidateToken(authToken.getAccessToken())) { //temp
                log.info("RT 만료");
                // AT, RT 업데이트
            }
            authToken.setAccessToken(accessToken);
            return authToken.getAccessToken();
        }
    }
}

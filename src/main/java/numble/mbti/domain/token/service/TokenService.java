package numble.mbti.domain.token.service;

import numble.mbti.domain.social.dto.LoginResponse;
import numble.mbti.domain.user.entity.User;

public interface TokenService {
    Long save(String jwtToken, User user);
    LoginResponse save(User user);
    String requestToken(User user);
}

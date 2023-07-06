package numble.mbti.domain.token.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationInterceptor(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("인터셉터 실행: {}", request.getHeader("Authorization"));
        String token = AuthorizationExtractor.extract(request);

        try {
            //jwtTokenProvider.validateToken(token);
            request.setAttribute("token", token); // 토큰을 HttpServletRequest에 저장
            if (CorsUtils.isPreFlightRequest(request)) {
                return true;
            }
            return true;
        } catch (Exception e) {
            // 유효하지 않은 토큰 또는 토큰이 없는 경우 처리 로직 추가
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

}

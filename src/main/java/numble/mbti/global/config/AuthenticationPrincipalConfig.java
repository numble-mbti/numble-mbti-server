package numble.mbti.global.config;

import lombok.RequiredArgsConstructor;
import numble.mbti.domain.token.jwt.AuthenticationInterceptor;
import numble.mbti.domain.token.jwt.AuthenticationPrincipalArgumentResolver;
import numble.mbti.domain.token.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver(jwtTokenProvider));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthenticationInterceptor(jwtTokenProvider))
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/oauth2/**", "/api/categories", "/api/mbti/**", "/api/health", "/api/afterlogin/**");
    }
}


package numble.mbti.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import numble.mbti.domain.token.jwt.AuthenticationInterceptor;
import numble.mbti.domain.token.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(servers = {@Server(url="https://animals-mbti-numble.o-r.kr", description="default server url")})
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${server.port}")
    private String serverPort;

    private final JwtTokenProvider jwtTokenProvider;

    public SwaggerConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        // API 정보 설정
        Info info = new Info()
                .title("MBTI API")
                .description("API Documentation for MBTI")
                .version("1.0.0");

        // 컴포넌트 설정
        Components components = new Components()
                .addSecuritySchemes("bearer-key", new SecurityScheme()
                        .type(Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(In.HEADER)
                        .name("Authorization"));

        // 경로 설정
        Paths paths = new Paths();

        // OpenAPI 설정
        OpenAPI openAPI = new OpenAPI()
                .info(info)
                .components(components)
                .paths(paths);

        return openAPI;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터 설정
        registry.addInterceptor(new AuthenticationInterceptor(jwtTokenProvider))
                .addPathPatterns("/api/users/**");
    }
}

package numble.mbti.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {
    public static final String ALLOWED_METHOD_NAMES = "GET,POST";

    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","));
        registry.addMapping("/oauth2/**")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","));
    }

}

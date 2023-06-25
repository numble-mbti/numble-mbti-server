package numble.mbti.domain.token.jwt;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import numble.mbti.domain.token.exception.ExtractTokenFailException;

import java.util.Enumeration;

@NoArgsConstructor
public class AuthorizationExtractor {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String ACCESS_TOKEN_TYPE = AuthorizationExtractor.class.getSimpleName();
    private static final char COMMA = ',';

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while(headers.hasMoreElements()) {
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(BEARER.toLowerCase())) {
                String authHeaderValue = value.substring(BEARER.length()).trim();
                request.setAttribute(ACCESS_TOKEN_TYPE, value.substring(0, BEARER.length()).trim());
                int commaIndex = authHeaderValue.indexOf(COMMA);
                if(commaIndex > 0) {
                    authHeaderValue = authHeaderValue.substring(0, commaIndex);
                }
                return authHeaderValue;
            }
        }
        throw new ExtractTokenFailException();
    }
}

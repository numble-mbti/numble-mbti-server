package numble.mbti.domain.token.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import numble.mbti.domain.token.exception.ExpiredTokenException;
import numble.mbti.domain.token.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
//    private final long validityInMilliseconds = 1000L * 60L * 60L * 1L; // 1시간
    private final long validityInMilliseconds = 1000L * 10L;
    private final long refreshInMilliseconds = 1000L * 60L * 60L * 24L * 30L; // 1달

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(final String payload) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidateToken(final String token) {
        try{
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException");
            return false;
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    public void validateToken(final String token) {
        try{
            parseClaims(token);
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException(token);
        } catch (Exception e) {
            throw new InvalidTokenException(token);
        }
    }

    private Jws<Claims> parseClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    public String getPayload(final String token) {
        return parseClaims(token).getBody().getSubject();
    }
}

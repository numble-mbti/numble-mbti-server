package numble.mbti.domain.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.user.entity.User;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;

@Getter
@Slf4j
@ToString
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private Long providerId;
    private String nickname;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, Long providerId, String nickname, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.providerId = providerId;
        this.nickname = nickname;
        this.email = email;
    }

    public static OAuthAttributes of(String socialName, String usernameAttributeName, Map<String , Object> attributes) {
        if(SocialConstant.SocialLoginType.KAKAO.name().equals(socialName.toUpperCase())) {
            log.info("OAuthAttributes: {}",socialName);
            return ofKakao(socialName, attributes);
        }
        return ofGoogle(socialName, attributes);
    }

    /**
     * kakao_account.profile
     * */
    private static OAuthAttributes ofKakao(String usernameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
        double providerId = (double) attributes.get("id");
        String email = (String) ObjectUtils.defaultIfNull(kakaoAccount.get("email"), "");
        String nickname = (String) kakaoProfile.get("nickname");
        return OAuthAttributes.builder()
                .nickname(nickname)
                .providerId((long) providerId)
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String usernameAttributeName, Map<String, Object> attributes) {
        double providerId = Double.valueOf(attributes.get("id").toString());
        String email = ObjectUtils.defaultIfNull((String) attributes.get("email"), "");
        String nickname = (String) attributes.get("name");
        return OAuthAttributes.builder()
                .nickname(nickname)
                .providerId((long) providerId)
                .email(email)
                .attributes(attributes)
                .nameAttributeKey(usernameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .email(email)
                .build();
    }


}

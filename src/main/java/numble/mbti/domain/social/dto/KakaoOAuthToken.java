package numble.mbti.domain.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class KakaoOAuthToken extends OAuthToken{
    @SerializedName("token_type")
    @JsonProperty("token_type")
    private String tokenType;
    @SerializedName("refresh_token")
    @JsonProperty("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    @JsonProperty("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token_expires_in")
    @JsonProperty("refresh_token_expires_in")
    private int refreshTokenExpiresIn;
    @JsonProperty("scope")
    private String scope;

    public KakaoOAuthToken(String accessToken) {
        super(accessToken);
    }
}

package numble.mbti.domain.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class KakaoOAuthToken {
    @SerializedName("access_token")
    @JsonProperty("access_token")
    private String accessToken;
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
}

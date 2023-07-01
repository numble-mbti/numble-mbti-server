package numble.mbti.domain.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GoogleOAuthToken extends OAuthToken{
    @SerializedName("expires_in")
    @JsonProperty("expires_in")
    private int expiresIn;
    @SerializedName("scope")
    @JsonProperty("scope")
    private String scope;
    @SerializedName("token_type")
    @JsonProperty("token_type")
    private String tokenType;
    @SerializedName("id_token")
    @JsonProperty("id_token")
    private String idToken;

    public GoogleOAuthToken(String accessToken) {
        super(accessToken);
    }
}

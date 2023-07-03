package numble.mbti.domain.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OAuthToken {
    @SerializedName("access_token")
    @JsonProperty("access_token")
    private String accessToken;
}

package numble.api.mbti.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.domain.features.contstant.FeaturesAttribute;
import numble.mbti.domain.features.entity.Features;

import java.util.Optional;

@Getter
@NoArgsConstructor
@Schema(description = "동물 유형별 MBTI 검사 결과 데이터")
public class MbtiFeaturesResponse {
    @Schema(description = "이름")
    private String name;

    @Schema(description = "위험등급")
    private Integer endangeredGrand;

    @Schema(description = "내용")
    private FeaturesAttribute content;


    public static MbtiFeaturesResponse from(Optional<Features> features) {
        MbtiFeaturesResponse response = new MbtiFeaturesResponse();
        if (features.isPresent()) {
            Features featuresObj = features.get();
            response.name = featuresObj.getName();
            response.endangeredGrand = featuresObj.getGrade();
            response.content = featuresObj.getContent();
        }
        return response;
    }
}

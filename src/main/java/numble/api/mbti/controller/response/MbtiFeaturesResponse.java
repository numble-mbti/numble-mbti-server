package numble.api.mbti.controller.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.domain.features.contstant.FeaturesAttribute;
import numble.mbti.domain.features.entity.Features;

import java.util.Optional;

@Getter
@NoArgsConstructor
@Schema(description = "동물 유형별 mbti 검사  결과 데이터 ")
public class MbtiFeaturesResponse {
    private String name;
    private Integer endangeredGrand;
    private FeaturesAttribute content;

    public static MbtiFeaturesResponse from(Optional<Features> features) {
        MbtiFeaturesResponse response = new MbtiFeaturesResponse();
        response.name = features.get().getName();
        response.endangeredGrand = features.get().getGrade();
        response.content = features.get().getContent();

        return response;
    }

}

package numble.api.category.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import numble.mbti.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "동물별 유형 응답 ")
public class CategoryGetResponse {

    private Long  id;
    private String kind;
    private String title;
    private String content;

    public static CategoryGetResponse from(Category category) {
        CategoryGetResponse response = new CategoryGetResponse();
        response.id = category.getId();
        response.kind = category.getKind();
        response.title = category.getTitle();
        response.content = category.getContent();

        return response;

    }

}

package numble.api.category.controller.response;

import numble.mbti.domain.category.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PRIVATE)
public class CategoryGetResponse {
    private Long  id;

    //Todo : enum 으로 변경 예정
    private String kind;
    private String title;
    private String Content;

    public static CategoryGetResponse from(Category category)
    {
        CategoryGetResponse response = new CategoryGetResponse();
        response.id = category.getId();
        response.kind = category.getKind();
        response.title = category.getTitle();
        response.Content = category.getContent();

        return response;

    }

}

package numble.api.mbti.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import numble.mbti.domain.answer.entity.Answer;
import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.question.constant.MbtiIndicator;
import numble.mbti.domain.question.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "유형별 MBTI 검사 응답")
public class MbtiCheckGetResponse {

    @Schema(description = "유형 1: 새")
    private Long categoryId;
    private List<QuestionResponse> questions;

    public static MbtiCheckGetResponse from(Long categoryId, List<Question> questions) {
        List<QuestionResponse> questionResponses = questions.stream()
                .map(QuestionResponse::of)
                .collect(Collectors.toList());

        MbtiCheckGetResponse response = new MbtiCheckGetResponse();
        response.setCategoryId(categoryId);
        response.setQuestions(questionResponses);
        return response;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(description = "질문 응답")
    private static class QuestionResponse {
        @Schema(description = "MBTI 인디케이터")
        private MbtiIndicator indicator;
        @Schema(description = "질문 내용")
        private String content;
        @Schema(description = "질문에 대한 답변 리스트")
        private List<Answer> answers;

        public static QuestionResponse of(Question question) {
            return new QuestionResponse(question.getIndicator(), question.getContent(), question.getAnswer());
        }
    }
}

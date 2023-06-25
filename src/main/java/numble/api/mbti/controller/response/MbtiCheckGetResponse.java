package numble.api.mbti.controller.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import numble.mbti.domain.answer.entity.Answer;
import numble.mbti.domain.question.constant.MbtiIndicator;
import numble.mbti.domain.question.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "유형별 mbti 검사 응답 ")
public class MbtiCheckGetResponse {

    @Schema(description = "유형 1: 새")
    private Long categoryId;
    private List<QuestionResponse> question;

    public static MbtiCheckGetResponse from(Long id, List<Question> questions) {
        MbtiCheckGetResponse response = new MbtiCheckGetResponse();
        response.categoryId = id;
        response.question = questions.stream()
                .map(question -> MbtiCheckGetResponse.QuestionResponse.of(question))
                .collect(Collectors.toList());

        return  response;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class QuestionResponse {
        private MbtiIndicator indicator;
        private String content;
        private List<Answer> answers;

        public static QuestionResponse of(Question question) {
            return QuestionResponse.builder()
                    .indicator(question.getIndicator())
                    .content(question.getContent())
                    .answers(question.getAnswer())
                    .build();
        }
    }
}



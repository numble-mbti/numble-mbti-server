package numble.api.mbti.controller.response;

import lombok.*;
import numble.mbti.domain.answer.entity.Answer;
import numble.mbti.domain.question.constant.MbtiIndicator;
import numble.mbti.domain.question.entity.Question;

import java.util.List;



@Getter
@NoArgsConstructor
public class MbtiCheckGetResponse {

    private Long  category_id;
    private QuestionResponse question;

    public static MbtiCheckGetResponse from(Question question) {
        MbtiCheckGetResponse response = new MbtiCheckGetResponse();
        response.category_id = question.getCategory().getId();
        response.question = QuestionResponse.of(question);

        return  response;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    private static class QuestionResponse
    {
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



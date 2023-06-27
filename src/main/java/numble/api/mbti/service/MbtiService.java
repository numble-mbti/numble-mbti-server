package numble.api.mbti.service;

import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.mbti.domain.question.service.QuestionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MbtiService {

    private final QuestionCommandService questionCommandService;

    //TODO 예외 처리 로직 추가 ( 질문 없을 경우)
    public MbtiCheckGetResponse getMbtiCheck(Long id) {
        var question = questionCommandService.gets(id);
        return MbtiCheckGetResponse.from(id,question);
    }
//
//    public MbtiCheckGetResponse getMbtiFeatureByCategory(Long categoryId, String type)
//    {
//        var question = questionCommandService.gets(id);
//        return MbtiCheckGetResponse.from(id,question);
//    }
}

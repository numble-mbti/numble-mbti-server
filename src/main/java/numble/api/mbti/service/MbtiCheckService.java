package numble.api.mbti.service;

import numble.api.category.controller.response.CategoryGetResponse;
import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.mbti.domain.question.service.QuestionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MbtiCheckService {

    private final QuestionCommandService questionCommandService;

    //TODO 예외 처리 로직 추가 ( 질문 없을 경우)
    public MbtiCheckGetResponse getMbtiCheck(Long id)
    {
        var question = questionCommandService.get(id).get();
        return MbtiCheckGetResponse.from(question);
    }
}

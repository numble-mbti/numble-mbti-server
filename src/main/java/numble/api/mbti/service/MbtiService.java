package numble.api.mbti.service;

import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.api.mbti.controller.response.MbtiFeaturesResponse;
import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.service.FeaturesCommandService;
import numble.mbti.domain.question.service.QuestionCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MbtiService {

    private final QuestionCommandService questionCommandService;
    private final FeaturesCommandService featuresCommandService;

    //TODO 예외 처리 로직 추가 ( 질문 없을 경우)
    public MbtiCheckGetResponse getMbtiCheck(Long id) {
        var question = questionCommandService.gets(id);
        return MbtiCheckGetResponse.from(id,question);
    }

    public MbtiFeaturesResponse getMbtiFeaturesByCategory(Long categoryId, String type) {
        var features = featuresCommandService.get(categoryId, MbtiType.valueOf(type));
        return MbtiFeaturesResponse.from(features);
    }
}


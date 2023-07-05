package numble.api.mbti.service;

import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.api.mbti.controller.response.MbtiFeaturesResponse;
import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.service.FeaturesCommandService;
import numble.mbti.domain.question.service.QuestionCommandService;
import lombok.RequiredArgsConstructor;
import numble.mbti.global.exception.constant.ErrorCode;
import numble.mbti.global.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MbtiService {

    private final QuestionCommandService questionCommandService;
    private final FeaturesCommandService featuresCommandService;


    public MbtiCheckGetResponse getMbtiCheck(Long id) {
        var questionOptional = questionCommandService.gets(id);
        if (questionOptional.isPresent()) {
            var question = questionOptional.get();
            return MbtiCheckGetResponse.from(id, question);
        } else {
             throw new BusinessException(ErrorCode.NOT_EXISTS_INFO);
        }
    }

    public MbtiFeaturesResponse getMbtiFeaturesByCategory(Long categoryId, String type) {
        var featuresOptional = featuresCommandService.get(categoryId, MbtiType.valueOf(type));
        if (featuresOptional.isPresent()) {
            var features = featuresOptional.get();
            return MbtiFeaturesResponse.from(Optional.of(features));
        } else {
            throw new BusinessException(ErrorCode.NOT_EXISTS_INFO);
        }
    }
}

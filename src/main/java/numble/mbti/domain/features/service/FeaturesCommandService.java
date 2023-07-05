package numble.mbti.domain.features.service;

import lombok.RequiredArgsConstructor;
import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.entity.Features;
import numble.mbti.domain.features.repository.FeaturesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeaturesCommandService {

    private final FeaturesRepository featuresRepository;

    public Optional<Features> get(Long categoryId, MbtiType type) {
        return featuresRepository.findByCategoryIdAndType(categoryId, type);
    }
}

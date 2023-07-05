package numble.mbti.domain.record.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.mbti.domain.category.repository.CategoryRepository;
import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.service.FeaturesCommandService;
import numble.mbti.domain.record.entity.UserRecord;
import numble.mbti.domain.record.repository.RecordRepository;
import numble.mbti.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class RecordCommandService {
    private final RecordRepository recordRepository;
    private final CategoryRepository categoryRepository;
    private final UserJpaRepository userJpaRepository;
    private final FeaturesCommandService featuresCommandService;

    public Optional<UserRecord> saveUserResult(Long userId, UserMbtiResultRequest request) {
        var category = categoryRepository.findById(request.getCategoryId());
        var user = userJpaRepository.findById(userId);
        String resultMbtiType = request.getResultMbtiType();

        if (category.isPresent() && user.isPresent()) {
            MbtiType mbtiType = MbtiType.valueOf(resultMbtiType);
            var features = featuresCommandService.get(userId, mbtiType);

            if (features.isPresent()) {
                UserRecord record = UserRecord.fromUserMbtiResultRequest(request, category.get(), user.get(), features.get());
                UserRecord savedRecord = recordRepository.save(record);
                return Optional.of(savedRecord);
            }
        }

        return Optional.empty();
    }
}

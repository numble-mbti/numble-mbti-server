package numble.mbti.domain.archive.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.mbti.domain.archive.entity.Archive;
import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.service.FeaturesCommandService;
import numble.mbti.domain.user.repository.UserJpaRepository;
import numble.mbti.global.exception.BusinessException;
import numble.mbti.global.exception.constant.ErrorCode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import numble.mbti.domain.archive.dto.ArchiveDto;
import numble.mbti.domain.archive.dto.ArchivesWithCategory;
import numble.mbti.domain.archive.repository.ArchiveRepository;
import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.category.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final CategoryRepository categoryRepository;
    private final UserJpaRepository userJpaRepository;
    private final FeaturesCommandService featuresCommandService;


    // 각 종류의 테스트(카테고리)를 n개씩 가져와주는 메서드
    public List<ArchivesWithCategory> getSomeArchivesWithAllCateogry(Long userId, Integer size) {
        List<ArchivesWithCategory> result = new ArrayList<>();

        List<Category> categories = categoryRepository.findAll();
        // 카테고리(테스트)가 없는 경우

        if (categories.isEmpty()) {
            return result;
        }

        List<ArchiveDto> archives = new ArrayList<>();
        for (Category category : categories) {
            PageRequest pageRequest = PageRequest.of(0, size, Sort.by("createdAt").descending());

            archives = archiveRepository
                    .findByUser_IdAndFeature_CategoryIdOrderByCreatedAtDesc(userId, category.getId(), pageRequest)
                    .stream().map((e) -> new ArchiveDto(e)).toList();

            ArchivesWithCategory recordsWithCategory = ArchivesWithCategory.builder().categoryId(category.getId()).categoryTitle(category.getTitle())
                    .archives(archives).build();
            result.add(recordsWithCategory);

        }

        return result;
    }

    // 한 종류의 테스트(카테고리)를 전부 가져와주는 메서드
    public ArchivesWithCategory getAllArchivesdWithCategory(Long userId, Long categoryId) {
        List<ArchiveDto> archives = archiveRepository
                .findByUser_IdAndFeature_CategoryIdOrderByCreatedAtDesc(userId, categoryId).stream()
                .map((e) -> new ArchiveDto(e)).toList();

        return ArchivesWithCategory.builder().archives(archives).categoryId(categoryId).build();
    }

    public Optional<Archive> saveUserResult(Long userId, UserMbtiResultRequest request) {

        return Optional.ofNullable(userJpaRepository.findById(userId)
                .flatMap(user -> {
                    String resultMbtiType = request.getResultMbtiType();
                    MbtiType mbtiType = MbtiType.valueOf(resultMbtiType);
                    return featuresCommandService.get(request.getCategoryId(), mbtiType)
                            .map(features -> Archive.fromUserMbtiResultRequest(user, features))
                            .map(archiveRepository::save);
                })
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_INFO)));
    }

}

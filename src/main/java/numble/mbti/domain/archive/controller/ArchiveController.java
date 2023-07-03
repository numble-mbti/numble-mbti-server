package numble.mbti.domain.archive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import numble.mbti.domain.archive.dto.ArchivesWithCategories;
import numble.mbti.domain.archive.dto.ArchivesWithCategory;
import numble.mbti.domain.archive.service.ArchiveService;
import numble.mbti.domain.token.jwt.AuthenticationPrincipal;

@RequiredArgsConstructor
@RequestMapping(value = "/api/archives", produces = "application/json")
@RestController
public class ArchiveController {
    // size만큼의 각 테스트(카테고리) 별 기록들을 가져와주는 api
    private final ArchiveService archiveService;

    @Tag(name = "각 테스트별(카테고리별) N개의 기록 목록")
    @Operation(summary = "전체 테스트 기록")
    @GetMapping()
    @ResponseBody
    public ResponseEntity<ArchivesWithCategories> getSomeArchivesWithAllCategory(@AuthenticationPrincipal Long userId,
            @RequestParam(name="size", required = false, defaultValue = "3") Integer size) {
        ArchivesWithCategories archiveResponseDto = ArchivesWithCategories.builder()
                .archivesWithCategories(archiveService.getSomeArchivesWithAllCateogry(userId, size)).build();
        return ResponseEntity.ok(archiveResponseDto);
    }

    // 특정 테스트(카테고리)의 기록들을 가져와주는 api
    @Tag(name = "특정 테스트(카테고리) 전체 기록 목록")
    @GetMapping("/{category_id}")
    @ResponseBody
    public ResponseEntity<ArchivesWithCategory> getAllArchivesdWithCategory(@AuthenticationPrincipal Long userId,
            @PathVariable("category_id") Long categoryId) {
        ArchivesWithCategory archivesWithCategory = archiveService.getAllArchivesdWithCategory(userId, categoryId);
        return ResponseEntity.ok(archivesWithCategory);
    }
}

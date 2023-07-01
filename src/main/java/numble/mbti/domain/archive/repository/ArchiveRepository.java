package numble.mbti.domain.archive.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import numble.mbti.domain.archive.entity.Archive;

public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    // 유저로 테스트 카테고리 별로 기록 n개씩 가져오기
    List<Archive> findByUser_IdAndFeature_CategoryIdOrderByCreatedAtDesc(Long Id, Long categoryId,
            Pageable pageable);

    // 유저로 해당 카테고리의 레코드 전부 갖고오기
    List<Archive> findByUser_IdAndFeature_CategoryIdOrderByCreatedAtDesc(Long Id, Long categoryId);
}

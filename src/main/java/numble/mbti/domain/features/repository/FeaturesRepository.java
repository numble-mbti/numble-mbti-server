package numble.mbti.domain.features.repository;

import numble.mbti.domain.features.contstant.MbtiType;
import numble.mbti.domain.features.entity.Features;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeaturesRepository extends JpaRepository<Features, Long> {
    Features findByCategoryIdAndType(Long categoryId, MbtiType type);
}

package numble.mbti.domain.token.repository;

import numble.mbti.domain.token.entity.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenJpaRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findByUserId(Long id);
}

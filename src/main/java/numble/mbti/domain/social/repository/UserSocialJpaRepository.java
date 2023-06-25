package numble.mbti.domain.social.repository;

import numble.mbti.domain.social.entity.UserSocial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSocialJpaRepository extends JpaRepository<UserSocial, Long> {
    boolean existsByProviderId(Long providerId);
    UserSocial findByProviderId(Long providerId);
}

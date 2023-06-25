package numble.mbti.domain.user.repository;

import numble.mbti.domain.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
}

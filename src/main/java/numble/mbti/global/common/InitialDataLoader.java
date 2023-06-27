package numble.mbti.global.common;

import numble.mbti.domain.user.entity.Role;
import numble.mbti.domain.user.repository.RoleJpaRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class InitialDataLoader implements ApplicationRunner {
    private final RoleJpaRepository roleJpaRepository;

    public InitialDataLoader(RoleJpaRepository roleJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(roleJpaRepository.count() == 0) {
            Role user = Role.builder()
                    .name("ROLE_USER")
                    .useYn(1)
                    .build();
            roleJpaRepository.save(user);
            Role admin = Role.builder()
                    .name("ROLE_ADMIN")
                    .useYn(1)
                    .build();
            roleJpaRepository.save(admin);
        }
    }
}

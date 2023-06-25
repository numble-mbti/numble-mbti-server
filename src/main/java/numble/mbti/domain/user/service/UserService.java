package numble.mbti.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import numble.mbti.domain.social.dto.OAuthAttributes;
import numble.mbti.domain.social.entity.Provider;
import numble.mbti.domain.social.entity.UserSocial;
import numble.mbti.domain.social.repository.UserSocialJpaRepository;
import numble.mbti.domain.token.service.TokenService;
import numble.mbti.domain.user.RoleEnum;
import numble.mbti.domain.user.dto.UserDto;
import numble.mbti.domain.user.entity.Role;
import numble.mbti.domain.user.entity.User;
import numble.mbti.domain.user.repository.RoleJpaRepository;
import numble.mbti.domain.user.repository.UserJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final UserSocialJpaRepository userSocialJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final TokenService tokenService;
    @Transactional
    public User signup(final OAuthAttributes oAuthAttributes){
        Role role = roleJpaRepository.findById(1L).
                orElseGet(()-> {
                    Role newRole = Role.builder().name(RoleEnum.USER.getKey()).useYn(1).build();
                    return roleJpaRepository.save(newRole);
                });

        User user = oAuthAttributes.toEntity();
        user.setRole(role);
        User savedUser = userJpaRepository.save(user);

        UserSocial userSocial = UserSocial.builder()
                .user(savedUser)
                .provider(Provider.KAKAO)
                .providerId(oAuthAttributes.getProviderId())
                .build();

        userSocialJpaRepository.save(userSocial);
        return savedUser;
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User perUser = userJpaRepository.findById(userDto.getId()).orElseThrow();
        perUser.setEmail(userDto.getEmail());
        perUser.setNickname(userDto.getNickname());
        return UserDto.toDto(perUser);
    }

    public UserDto findUser(Long userId) {
        User user = userJpaRepository.findById(userId).orElseThrow();
        return UserDto.toDto(user);
    }
}

package numble.mbti.domain.social.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.domain.user.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSocial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum", length = 20)
    private Provider provider;

    private Long providerId;

    @Builder
    public UserSocial(User user, Provider provider, Long providerId) {
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
    }
}

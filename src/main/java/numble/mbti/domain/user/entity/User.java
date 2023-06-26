package numble.mbti.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.global.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@Table(name ="member" )
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String email;

    @Column(length = 200)
    private String nickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Builder
    public User(String email, String nickname, Role role) {
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

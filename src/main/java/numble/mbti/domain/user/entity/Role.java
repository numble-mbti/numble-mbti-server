package numble.mbti.domain.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.global.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    private int useYn;

    @Builder
    public Role(String name, int useYn) {
        this.name = name;
        this.useYn = useYn;
    }
}

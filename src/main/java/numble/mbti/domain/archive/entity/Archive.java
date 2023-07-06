package numble.mbti.domain.archive.entity;

import java.time.LocalDateTime;

import lombok.*;
import numble.mbti.global.BaseEntity;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import numble.mbti.domain.features.entity.Features;
import numble.mbti.domain.user.entity.User;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class Archive extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long archiveSeq;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "feature_id", nullable = false)
    private Features feature;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static Archive fromUserMbtiResultRequest(User user, Features feature) {
        return Archive.builder()
                .user(user)
                .feature(feature)
                .build();
    }
}

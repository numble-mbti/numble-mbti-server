package numble.mbti.domain.record.entity;

import jakarta.persistence.*;
import lombok.*;
import numble.api.record.controller.response.UserMbtiResultRequest;
import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.features.entity.Features;
import numble.mbti.domain.user.entity.User;

@Getter
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UserRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "features_id", nullable = false)
    private Features features;

    public static UserRecord fromUserMbtiResultRequest(UserMbtiResultRequest request, Category category, User user, Features features) {
        return UserRecord.builder()
                .category(category)
                .user(user)
                .features(features)
                .build();
    }
}

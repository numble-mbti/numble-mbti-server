package numble.mbti.domain.features.entity;

import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.features.contstant.FeaturesAttribute;
import numble.mbti.domain.features.converter.FeaturesContentConvert;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Features {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Convert(converter = FeaturesContentConvert.class)
    private FeaturesAttribute Content;
}

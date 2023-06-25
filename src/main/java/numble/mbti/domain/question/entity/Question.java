package numble.mbti.domain.question.entity;

import numble.mbti.domain.answer.entity.Answer;
import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.question.constant.MbtiIndicator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "question")
    private List<Answer> answer;

    @Enumerated(EnumType.STRING)
    private MbtiIndicator indicator;

    private String content;
}

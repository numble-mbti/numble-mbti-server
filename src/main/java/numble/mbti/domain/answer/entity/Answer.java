package numble.mbti.domain.answer.entity;

import numble.mbti.domain.answer.constant.MbtiType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String question_id;

    @Enumerated(EnumType.STRING)
    private MbtiType type;

    private String content;
}

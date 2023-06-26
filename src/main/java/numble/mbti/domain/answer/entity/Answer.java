package numble.mbti.domain.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import numble.mbti.domain.answer.constant.MbtiType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import numble.mbti.domain.question.entity.Question;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    //TODo Converter 변경 필요
    @Enumerated(EnumType.STRING)
    private MbtiType type;

    private String content;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;


}

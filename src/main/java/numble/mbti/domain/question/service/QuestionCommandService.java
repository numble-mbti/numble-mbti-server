package numble.mbti.domain.question.service;

import numble.mbti.domain.question.entity.Question;
import numble.mbti.domain.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionCommandService {
    private final QuestionRepository questionRepository;

    public Optional<List<Question>> gets(Long questionId) {
        List<Question> questions = questionRepository.findAllByCategoryId(questionId);
        return Optional.ofNullable(questions);
    }

    public List<Question> getsOrEmpty(Long questionId) {
        return gets(questionId).orElse(Collections.emptyList());
    }
}

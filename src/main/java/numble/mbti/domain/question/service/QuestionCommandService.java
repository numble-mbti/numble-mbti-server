package numble.mbti.domain.question.service;

import numble.mbti.domain.question.entity.Question;
import numble.mbti.domain.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionCommandService {
    private final QuestionRepository questionRepository;
    public List<Question> gets(Long questionId) {
        return questionRepository.findAllByCategoryId(questionId);
    }
}

package numble.mbti.domain.category.service;

import numble.mbti.domain.category.entity.Category;
import numble.mbti.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryCommandService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> get(Long categoryId)
    {
        return categoryRepository.findById(categoryId);
    }

    public List<Category> gets(){
        return categoryRepository.findAll();
    }
}

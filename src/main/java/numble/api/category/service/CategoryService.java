package numble.api.category.service;

import numble.api.category.controller.response.CategoryGetResponse;
import numble.mbti.domain.category.service.CategoryCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryCommandService categoryCommandService;

    public List<CategoryGetResponse> gets(){
        var categories = categoryCommandService.gets();
        return categories.stream()
                .map(category -> CategoryGetResponse.from(category))
                .collect(Collectors.toList());
    }
}

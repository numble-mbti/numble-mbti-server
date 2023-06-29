package numble.api.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import numble.api.category.controller.response.CategoryGetResponse;
import numble.api.category.service.CategoryService;
import numble.api.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import numble.api.dto.ApiErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "동물 유형 API")
public class CategoryRestCotroller {
    private  final CategoryService categoryService;

    @GetMapping("/categories")
    @Operation(summary = "카테고리 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리 목록 조회", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryGetResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "카테고리 목록 조회 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class)) )})
    public ResponseEntity<ApiResponseDto<List<CategoryGetResponse>>> gets() {
        final List<CategoryGetResponse> response = categoryService.gets();
        return ResponseEntity.ok(ApiResponseDto.create(response));

    }
}

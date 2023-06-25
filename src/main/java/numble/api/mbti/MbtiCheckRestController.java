package numble.api.mbti;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import numble.api.dto.ApiResponseDto;
import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.api.mbti.service.MbtiCheckService;
import lombok.RequiredArgsConstructor;
import numble.api.dto.ApiErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/mbti")
@RestController
@RequiredArgsConstructor
public class MbtiCheckRestController {

    private final MbtiCheckService mbtiCheckService;

    @GetMapping("/{categoryId}/check")
    @Tag(name = "카테고리별 mbti 검사", description = "카테고리별 mbti 검사 목록 조회 API")
    @Operation(summary = "카테고리별 mbti 검사 목록 조회 / 아직 예외 처리 안됌")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리별 mbti 검사 목록 조회", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MbtiCheckGetResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "카테고리별 mbti 검사 목록 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class)) )})
    public ResponseEntity<ApiResponseDto<MbtiCheckGetResponse>> getMbtiCheck (@Parameter(name = "categoryId", description = "동물 카테고리 id", in = ParameterIn.PATH) @PathVariable Long categoryId)
    {
        final MbtiCheckGetResponse response = mbtiCheckService.getMbtiCheck(categoryId);
        return ResponseEntity.ok(ApiResponseDto.create(response));
    }
}

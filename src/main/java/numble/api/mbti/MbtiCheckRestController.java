package numble.api.mbti;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import numble.api.category.controller.response.CategoryGetResponse;
import numble.api.dto.ApiResponseDto;
import numble.api.mbti.controller.response.MbtiCheckGetResponse;
import numble.api.mbti.service.MbtiCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/mbti")
@RestController
@RequiredArgsConstructor
public class MbtiCheckRestController {

    private final MbtiCheckService mbtiCheckService;

    @GetMapping("/{category_id}/check")
    @Operation(summary = "유형별 mbti 검사 목록 조회 / 아직 예외 처리 안됌")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유형별 mbti 검사 목록 조회", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MbtiCheckGetResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "유형별 mbti 검사 목록 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) )})

    public ResponseEntity<ApiResponseDto<MbtiCheckGetResponse>> getMbtiCheck (@PathVariable Long category_id)
    {
        final MbtiCheckGetResponse response = mbtiCheckService.getMbtiCheck(category_id);
        return ResponseEntity.ok(ApiResponseDto.create(response));
    }
}

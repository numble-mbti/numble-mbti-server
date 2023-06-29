package numble.api.mbti.controller;


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
import numble.api.mbti.controller.response.MbtiFeaturesResponse;
import numble.api.mbti.service.MbtiService;
import lombok.RequiredArgsConstructor;
import numble.api.dto.ApiErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/mbti")
@RestController
@RequiredArgsConstructor
@Tag(name = "MBTI 검사 API")
public class MbtiController {

    private final MbtiService mbtiService;

    @GetMapping("/{categoryId}/check")
    @Operation(summary = "동물 유형 카테고리별 mbti 검사 질문 목록 조회 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카테고리별 mbti 검사 목록 조회", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MbtiCheckGetResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "카테고리별 mbti 검사 목록 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiErrorResponseDto.class)) )})
    public ResponseEntity<ApiResponseDto<MbtiCheckGetResponse>> getMbtiCheck (@Parameter(name = "categoryId", description = "동물 유형 카테고리 id", in = ParameterIn.PATH) @PathVariable Long categoryId)
    {
        var response = mbtiService.getMbtiCheck(categoryId);
        return ResponseEntity.ok(ApiResponseDto.create(response));
    }

    @GetMapping("/{categoryId}/features")
   @Operation(summary = "동물 유형 카테고리별 mbti 검사 결과 목록 조회 ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "동물 유형별 mbti 검사 목록 조회", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MbtiFeaturesResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "동물 유형별  mbti 검사 목록 실패", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MbtiFeaturesResponse.class)) )})
    public ResponseEntity<ApiResponseDto<MbtiFeaturesResponse>> getMbtiFeatureByCategory (@Parameter(name = "categoryId", description = "동물 유형 카테고리 id", in = ParameterIn.PATH) @PathVariable Long categoryId, @Parameter(name = "type", description = "mbti 유형", in = ParameterIn.QUERY) @RequestParam String type)
    {
        var response = mbtiService.getMbtiFeaturesByCategory(categoryId, type);
        return ResponseEntity.ok(ApiResponseDto.create(response));
    }
}

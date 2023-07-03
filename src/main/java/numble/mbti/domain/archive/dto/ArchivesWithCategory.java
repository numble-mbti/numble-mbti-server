package numble.mbti.domain.archive.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

//카테고리에 맞는 기록들을 묶어놓은 DTO 
@Builder
@Data
@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArchivesWithCategory {
    private Long categoryId;
    private String categoryTitle;
    private List<ArchiveDto> archives;
}
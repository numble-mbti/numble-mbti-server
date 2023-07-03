package numble.mbti.domain.archive.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import numble.mbti.domain.archive.entity.Archive;

@Data
@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArchiveDto {
    private Long archiveSeq;
    private String result;
    private String type;
    private LocalDateTime createdAt;

    public ArchiveDto(Archive archive){
        archiveSeq = archive.getArchiveSeq();
        result = archive.getFeature().getName();
        type = archive.getFeature().getType().toString();
        createdAt = archive.getCreatedAt();
    }

}

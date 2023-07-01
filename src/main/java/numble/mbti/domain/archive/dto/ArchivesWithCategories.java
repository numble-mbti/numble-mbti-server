package numble.mbti.domain.archive.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(value=PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArchivesWithCategories {
    private List<ArchivesWithCategory> archivesWithCategories;
}

package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Users articles")
public class ArticlesDto {

    @Schema(description = "List of articles")
    private List<ArticleDto> articles;

    @Schema(description = "Number of articles", example = "2")
    private Integer count;

}

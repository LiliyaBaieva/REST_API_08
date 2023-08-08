package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Added article")
public class NewArticleDto {

    @Schema(description = "Text of article", example = "Text about user...")
    private String text;

    @Schema(description = "User id", example = "1")
    private Long aboutUserId;

    @Schema(description = "Date for publish", example = "2023-03-27")
    private String publishDate;

}

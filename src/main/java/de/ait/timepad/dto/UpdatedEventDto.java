package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data for updating")
public class UpdatedEventDto {

    @Schema(description = "Data of event", example = "01.01.2023")
    private String newDate;

}

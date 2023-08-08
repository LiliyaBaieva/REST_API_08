package de.ait.timepad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(description = "Data to add new event")
@Data
public class NewEventDto {

    @Schema(description = "The title of event", example = "New Year")
    @NotNull
    @NotBlank
    private String title;

    @Schema(description = "The date of event", example = "01.01.2023")
    @NotNull
    @NotBlank
    private String date;
}

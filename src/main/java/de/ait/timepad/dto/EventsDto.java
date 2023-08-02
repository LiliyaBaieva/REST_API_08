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
@Schema(description = "List of events")
public class EventsDto {

    @Schema(description = "Events in system")
    private List<EventDto> events;

    @Schema(description = "Number of event", example = "2")
    private Integer count;
}

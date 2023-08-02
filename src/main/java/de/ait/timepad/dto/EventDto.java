package de.ait.timepad.dto;

import de.ait.timepad.models.Event;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Events in system")
public class EventDto {

    @Schema(description = "Id of event", example = "1")
    private Long id;

    @Schema(description = "Name of event", example = "New Year")
    private String title;

    @Schema(description = "Date of event", example = "01.01.2023")
    private String date;

    public static EventDto from(Event event){
        return EventDto.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .date(event.getDate())
                        .build();
    }

    public static    List<EventDto> from(List<Event> events){
        return events.stream()
                .map(EventDto::from)
                .collect(Collectors.toList());
    }

}











package de.ait.timepad.controllers.api;

import de.ait.timepad.dto.UpdatedEventDto;
import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Events")
})
@RequestMapping("/api/events")
public interface EventsApi {

    @Operation(summary = "Create event", description = "Available only for administrator")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<EventDto> addEvent (@RequestBody @Valid NewEventDto newEvent);

    @Operation(summary = "Get all events", description = "Available for everyone")
    @GetMapping
    EventsDto getAllEvents();

    @Operation(summary = "Delete event", description = "Available only for administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The Event is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "200", description = "The Event is deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
            })
    })
    @DeleteMapping("/{event-id}")
    EventDto deleteEvent (@Parameter(required = true, description = "Id of event", example = "1")
            @PathVariable("event-id") Long eventId);

    @Operation(summary = "Update event", description = "Available only for administrator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The Event is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "200", description = "The Event is updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
            })
    })
    @PutMapping("/{event-id}")
    EventDto updateEvent (@Parameter(required = true, description = "Id of event", example = "1")
            @PathVariable("event-id") Long eventId,
                          @RequestBody UpdatedEventDto updatedEvent);

    @Operation(summary = "Get event", description = "Available for everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "The Event is not found",
                    content = {@Content()}),
            @ApiResponse(responseCode = "200", description = "Get event", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EventDto.class))
            })
    })
    @GetMapping("/{event-id}")
    EventDto getEvent (@Parameter(required = true, description = "Id of event", example = "1")
            @PathVariable("event-id") Long eventId);

}

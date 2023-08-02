package de.ait.timepad.controllers;

import de.ait.timepad.controllers.api.EventsApi;
import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.dto.UpdatedEventDto;
import de.ait.timepad.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class EventController implements EventsApi {
    private final EventService eventService;
    public EventDto addEvent (NewEventDto newEvent){
        return eventService.addEvent(newEvent);
    }

    public EventsDto getAllEvents(){
        return eventService.getAllEvents();
    }

    @Override
    public EventDto deleteEvent(Long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public EventDto updateEvent(Long eventId, UpdatedEventDto updatedEvent) {
        return eventService.updateEvent(eventId, updatedEvent);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return eventService.getEvent(eventId);
    }

}

package de.ait.timepad.services;

import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.dto.UpdatedEventDto;

public interface EventService {
    EventDto addEvent(NewEventDto event);

    EventsDto getAllEvents();

    EventDto deleteEvent(Long eventId);

    EventDto updateEvent(Long eventId, UpdatedEventDto updatedEvent);

    EventDto getEvent(Long eventId);
}

package de.ait.timepad.services;

import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;

public interface EventService {
    EventDto addEvent(NewEventDto event);

    EventsDto getAllEvents();
}

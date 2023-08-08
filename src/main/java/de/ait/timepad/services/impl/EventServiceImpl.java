package de.ait.timepad.services.impl;

import de.ait.timepad.dto.EventDto;
import de.ait.timepad.dto.EventsDto;
import de.ait.timepad.dto.NewEventDto;
import de.ait.timepad.dto.UpdatedEventDto;
import de.ait.timepad.exceptions.NotFoundException;
import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventRepository;
import de.ait.timepad.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.timepad.dto.EventDto.from;


@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public EventDto addEvent(NewEventDto newEvent) {
        Event event = Event.builder()
                            .title(newEvent.getTitle())
                            .date(newEvent.getDate())
                            .build();
        eventRepository.save(event);

        return EventDto.from(event);
    }

    @Override
    public EventsDto getAllEvents() {
        List<Event> events = eventRepository.findAll();

        return EventsDto.builder()
                .events(from(events))
                .count(events.size())
                .build();
    }

    @Override
    public EventDto deleteEvent(Long eventId) {
        Event event = getEventOrThrow(eventId);

        eventRepository.delete(event);

        return from(event);
    }

    private Event getEventOrThrow(Long eventId) {
        Event event = eventRepository.findByID(eventId).orElseThrow(
                ()-> new NotFoundException("Event with id <" + eventId +"> not found")
        );
        return event;
    }

    @Override
    public EventDto updateEvent(Long eventId, UpdatedEventDto updatedEvent) {
        Event event = getEventOrThrow(eventId);

        event.setDate(updatedEvent.getNewDate());

        eventRepository.save(event);

        return from(event);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        return from(getEventOrThrow(eventId));
    }


}














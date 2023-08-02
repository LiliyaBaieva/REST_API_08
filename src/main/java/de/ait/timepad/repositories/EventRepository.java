package de.ait.timepad.repositories;

import de.ait.timepad.models.Event;
import java.util.List;
import java.util.Optional;


public interface EventRepository {
    void save(Event event);

    List<Event> findAll();

    void clear();

    Optional<Event> findById(Long eventId);

    void delete(Event event);
}

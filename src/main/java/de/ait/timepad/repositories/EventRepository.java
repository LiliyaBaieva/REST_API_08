package de.ait.timepad.repositories;

import de.ait.timepad.models.Event;
import java.util.List;


public interface EventRepository {
    void save(Event event);

    List<Event> findAll();

    void clear();
}

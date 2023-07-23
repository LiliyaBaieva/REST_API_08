package de.ait.timepad.repositories.impl;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private static List<Event> events = new ArrayList<>();
    @Override
    public void save(Event event) {
        event.setId((long)events.size() + 1);
        events.add(event);
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void clear() {
        events.clear();
    }
}

package de.ait.timepad.repositories.impl;

import de.ait.timepad.models.Event;
import de.ait.timepad.repositories.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepositoryImpl implements EventRepository {
    private static List<Event> events = new ArrayList<>();
    @Override
    public void save(Event event) {
        if(event.getId() == null){
            event.setId((long)events.size() + 1);
            events.add(event);
        } else {
            //TODO
        }
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public void clear() {
        events.clear();
    }

    @Override
    public Optional<Event> findById(Long eventId) {
        for(Event event : events){
            if(event.getId().equals(eventId)){
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(Event event) {
        events.remove(event);
    }
}











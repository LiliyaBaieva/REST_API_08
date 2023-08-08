package de.ait.timepad.repositories;

import de.ait.timepad.models.Event;

public interface EventRepository extends CrudRepository <Event> {

    void clear();
}

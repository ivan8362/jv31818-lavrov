package org.itstep.msk.app.services;

import org.itstep.msk.app.entities.Event;

public interface EventApi {
    Iterable<Event> getAllEvents();
    Event createEvent(Event event);
}

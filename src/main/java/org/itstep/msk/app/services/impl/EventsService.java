package org.itstep.msk.app.services.impl;

import org.itstep.msk.app.entities.Event;
import org.itstep.msk.app.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    @Autowired
    private EventRepository eventRepository;

    public Iterable<Event> getAllEvents(){
        Iterable<Event> events = this.eventRepository.findAll();
        return events;
    }

    public Event createEvent(Event event){
        eventRepository.save(event);

        return event;
    }

}

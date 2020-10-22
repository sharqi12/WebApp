package com.WEBAPP.WEBAPP.service;

import java.util.List;

import java.util.Optional;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List <Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) {
        this.eventRepository.save(event);
    }

    @Override
    public Event getEventById(long id) {
        Optional < Event > optional = eventRepository.findById(id);
        Event event = null;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException(" Event not found for id :: " + id);
        }
        return event;
    }

    @Override
    public void deleteEventById(long id) {
        this.eventRepository.deleteById(id);
    }
}

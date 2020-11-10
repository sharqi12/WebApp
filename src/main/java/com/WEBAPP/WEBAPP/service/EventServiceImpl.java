package com.WEBAPP.WEBAPP.service;

import java.util.List;

import java.util.Optional;
import java.util.Map;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List <Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(Event event) {
        this.eventRepository.save(event);
    }

    @Override
    @PutMapping
    public Event getEventById(Long id) {
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
    public void deleteEventById(Long id) {
        this.eventRepository.deleteById(id);
    }
}

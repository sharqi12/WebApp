package com.WEBAPP.WEBAPP.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import java.util.Optional;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public List <Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void saveEvent(MultipartFile file, Event event)
    {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            event.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

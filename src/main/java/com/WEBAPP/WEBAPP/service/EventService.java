package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void saveEvent(MultipartFile file, Event event);
    Event getEventById(long id);
    void deleteEventById(long id);
}

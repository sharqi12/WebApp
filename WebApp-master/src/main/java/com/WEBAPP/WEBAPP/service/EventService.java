package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Event;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void saveEvent(Event event);
    Event getEventById(long id);
    void deleteEventById(long id);
}

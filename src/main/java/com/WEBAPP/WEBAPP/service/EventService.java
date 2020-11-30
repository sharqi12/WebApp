package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    void saveEvent(MultipartFile file, Event event);
    Event getEventById(Long id);
    void deleteEventById(Long id);
    Page<Event> getPastEvents(int pageNum);
    Page<Event> getFutureEvents(int pageNum);
    Integer isEventEnded(Long id);
    Integer hasUserBoughtTicket(Integer user_id, Long event_id);
    Integer howManyTicketsTypesByEventId(Long id);
}

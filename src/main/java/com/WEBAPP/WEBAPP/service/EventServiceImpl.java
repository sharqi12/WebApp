package com.WEBAPP.WEBAPP.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import java.util.Optional;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    TicketRepository ticketRepository;

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

    @Override
    public Page<Event> getPastEvents(int pageNum) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return eventRepository.findPastEvents(LocalDate.now().toString().replace('-','/'),pageable);
    }

    @Override
    public Page <Event> getFutureEvents(int pageNum) {
        int pageSize = 6;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return eventRepository.findFutureEvents(LocalDate.now().toString().replace('-','/'),pageable);
    }

    @Override
    public  Integer isEventEnded(Long id){
        return eventRepository.isEventEnded(id, LocalDate.now().toString().replace('-','/'));
    }

    @Override
    public Integer hasUserBoughtTicket(Integer user_id, Long event_id){
        return ticketRepository.isTicketBought(user_id,event_id);
    }

    @Override
    public Integer howManyTicketsTypesByEventId(Long id){
        return ticketRepository.howManyTicketsTypesByEventId(id);
    }
}

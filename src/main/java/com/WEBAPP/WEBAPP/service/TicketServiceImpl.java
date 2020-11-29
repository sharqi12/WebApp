package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.EventTickets;
import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.EventTicketsRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.web.dto.EventTicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventTicketsRepository eventTicketsRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;

    @Override
    public List<Tickets> getAllTickets(Long id) {
        return ticketRepository.findByEventId(id);
    }

    @Override
    public List<Tickets> getAllTicketsByUserId(Integer id) {
        return ticketRepository.findByUserId(id);
    }
    @Override
    public Integer howManyTicketsBoughtByEventId(Long id){
        return ticketRepository.howManyTicketsBoughtByEventId(id);
    }
    @Override
    public Integer sumOfTicketsPriceByEventId(Long id){
        return ticketRepository.sumOfTicketsPriceByEventId(id);
    }

    @Override
    public void saveTicket(Tickets ticket){
        this.ticketRepository.save(ticket);
    }

    @Override
    public List<EventTickets> getAllTypesOfTicketsByEventId(long id){
        return eventTicketsRepository.findTypesByEventId(id);
    }

    @Override
    public Integer howManyTicketsTypesByEventId(Long id){
        return eventTicketsRepository.howManyTicketsTypesByEventId(id);
    }

    @Override
    public void saveTicketType(EventTicketDto eventTicketDto, Long id){
        EventTickets eventTickets = new EventTickets(eventTicketDto);
        eventTickets.setEvent(eventService.getEventById(id));
        eventTicketsRepository.save(eventTickets);
    }

    @Override
    public void deleteTicketTypeById(Long id){
        eventTicketsRepository.deleteById(id);
    }

    @Override
    public String getTicketTypeNameByValue(Integer value, Long event_id){
        return eventTicketsRepository.findNameByValue(value, event_id);
    }

    @Override
    public Integer alreadyTicketBoughtById(Integer userId, Long event_id){
        return ticketRepository.isTicketBought(userId,event_id);
    }

}

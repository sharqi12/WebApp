package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

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
}

package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveTicket(Tickets ticket){
        this.ticketRepository.save(ticket);
    }
}

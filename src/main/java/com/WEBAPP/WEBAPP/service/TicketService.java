package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Tickets;

import java.util.List;

public interface TicketService {
    List<Tickets> getAllTickets(Long id);
    void saveTicket(Tickets ticket);
}

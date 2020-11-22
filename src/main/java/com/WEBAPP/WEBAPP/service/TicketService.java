package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Tickets;

import java.util.List;

public interface TicketService {
    List<Tickets> getAllTickets(Long id);
    List<Tickets> getAllTicketsByUserId(Integer id);
    void saveTicket(Tickets ticket);
    Integer howManyTicketsBoughtByEventId(Long id);
    Integer sumOfTicketsPriceByEventId(Long id);
}

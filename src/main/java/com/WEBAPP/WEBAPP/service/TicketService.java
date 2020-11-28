package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.EventTickets;
import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.web.dto.EventTicketDto;

import java.util.List;

public interface TicketService {
    List<Tickets> getAllTickets(Long id);
    List<Tickets> getAllTicketsByUserId(Integer id);
    void saveTicket(Tickets ticket);
    Integer howManyTicketsBoughtByEventId(Long id);
    Integer sumOfTicketsPriceByEventId(Long id);
    List<EventTickets> getAllTypesOfTicketsByEventId(long id);
    Integer howManyTicketsTypesByEventId(Long id);
    void saveTicketType(EventTicketDto eventTicketDto, Long id);
    void deleteTicketTypeById(Long id);
    String getTicketTypeNameByValue(Integer value, Long event_id);
}

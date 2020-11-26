package com.WEBAPP.WEBAPP.model;

import com.WEBAPP.WEBAPP.web.dto.EventTicketDto;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "event_tickets")
public class EventTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "event_id")
    private Event event;

    private Integer ticketPrice;

    private  String ticketName;

    @Type(type="text")
    private String ticketDescription;

    public EventTickets(){

    }

    public EventTickets(Long id, Event event, Integer ticketPrice, String ticketName, String ticketDescription) {
        this.id = id;
        this.event = event;
        this.ticketPrice = ticketPrice;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
    }

    public EventTickets(EventTicketDto eventTicketDto){
        this.id = eventTicketDto.getId();
        this.event = eventTicketDto.getEvent();
        this.ticketPrice = eventTicketDto.getTicketPrice();
        this.ticketName = eventTicketDto.getTicketName();
        this.ticketDescription = eventTicketDto.getTicketDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }
}
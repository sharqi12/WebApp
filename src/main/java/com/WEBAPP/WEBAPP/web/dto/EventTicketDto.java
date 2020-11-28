package com.WEBAPP.WEBAPP.web.dto;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.EventTickets;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EventTicketDto {

    public  EventTicketDto(){

    }
    public EventTicketDto(EventTickets eventTickets){
        this.id=eventTickets.getId();
        this.event=eventTickets.getEvent();
        this.ticketDescription=eventTickets.getTicketDescription();
        this.ticketName=eventTickets.getTicketName();
        this.ticketPrice=eventTickets.getTicketPrice();
    }

    private Long id;

    private Event event;

    private Integer ticketPrice;

    private  String ticketName;

    private String ticketDescription;

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

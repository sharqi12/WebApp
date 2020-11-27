package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.TicketService;
import com.WEBAPP.WEBAPP.service.TimetableService;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.web.dto.EventTicketDto;
import com.WEBAPP.WEBAPP.web.dto.TimeTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TicketController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;


    @ResponseBody
    @RequestMapping(value = "/acceptedPayment/{value}", method = RequestMethod.POST)
    public String getSearchResultViaAjax(@RequestParam(value = "value") Integer value, @RequestParam(value = "user_id") Integer user_id, @RequestParam(value = "event_id") Long event_id)
    {
        //User user = userRepository.findById(user_id);
        String ticket_name = ticketService.getTicketTypeNameByValue(value, event_id);
        Tickets ticket = new Tickets(userService.getUserById(user_id), eventService.getEventById(event_id), value, ticket_name);
        ticketService.saveTicket(ticket);

        //System.out.println("Cena: "+value+" UserId: "+user_id+" EventId: "+ event_id);
        return "redirect:/description/{event_id}";
    }
    @GetMapping("/showTicketsForEvent/{id}")
    public String showTicketsForEvent(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3, Model model4) {
        model.addAttribute("listTickets", ticketService.getAllTickets(id));
        model2.addAttribute("howManyTickets", ticketService.howManyTicketsBoughtByEventId(id));
        model3.addAttribute("sumOfTicketsValue", ticketService.sumOfTicketsPriceByEventId(id));
        model4.addAttribute("event", eventService.getEventById(id));
        return "ticketList";
    }

    @GetMapping("/showTicketsTypesForEvent/{id}")
    public String showTimetableForm(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3, Model model4, Model model5,  Principal principal ){
        if (principal != null)
            model2.addAttribute("activeUser", userRepository.findByEmail(principal.getName()));
        else model2.addAttribute("activeUser", null);
        model.addAttribute("event", eventService.getEventById(id));
        model3.addAttribute("eventTicketList", ticketService.getAllTypesOfTicketsByEventId(id));
        model4.addAttribute("howManyTypesTicket", ticketService.howManyTicketsTypesByEventId(id));
        model5.addAttribute("eventTicket", new EventTicketDto());
        return "eventTickets";
    }


    @PostMapping("/saveNewTicket/{id}")
    public String saveTimetable(@ModelAttribute("eventTicket") @Valid EventTicketDto eventTicketDto, @PathVariable(value = "id") Long id, Errors errors, Principal principal) {
        //ZABEZPIECZENIE PRZED NIEAUTORYZOWANYM DODANIEM TYPU BILETU
        if(userRepository.findByEmail(principal.getName()).getId() == eventService.getEventById(id).getUser().getId()) {
            if (errors.hasErrors()) {
                return "redirect:/showTicketsTypesForEvent/" + id;
            } else {
                ticketService.saveTicketType(eventTicketDto,id);
                return "redirect:/showTicketsTypesForEvent/" + id;
            }
        }
        else return "redirect:/showTicketsTypesForEvent/" + id;
    }

    @GetMapping("/deleteTicketType/{id}/{id2}")
    public String deleteEvent(@PathVariable(value = "id") Long id, @PathVariable(value = "id2") Long eventId, Principal principal ) {

        //ZABEZPIECZENIE PRZED NIEAUTORYZOWANYM USUNIĘCIEM TYPU BILETU
        if(userRepository.findByEmail(principal.getName()).getId()== eventService.getEventById(eventId).getUser().getId()){
            ticketService.deleteTicketTypeById(id);
            return "redirect:/showTicketsTypesForEvent/"+eventId;
        }
        return "redirect:/showTicketsTypesForEvent/"+eventId;
    }
}

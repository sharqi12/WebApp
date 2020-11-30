package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.*;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.TicketService;
import com.WEBAPP.WEBAPP.service.TimetableService;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.web.dto.TimeTableDto;
import com.lowagie.text.DocumentException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TimetableService timetableService;


    @GetMapping("/listOfFutureEvents/{pageNum}")
    public String viewHomePage(@PathVariable(name = "pageNum") int pageNum, Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("activeUser", userRepository.findByEmail(principal.getName()));
        else model.addAttribute("activeUser", null);

        Page<Event> page = eventService.getFutureEvents(pageNum);
        List<Event> listEvents = page.getContent();
        model.addAttribute("listEvents", listEvents);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalEvents", page.getTotalElements());

        return "list";
    }
    @GetMapping("/listOfPastEvents/{pageNum}")
    public String viewListOfPastEvents(@PathVariable(name = "pageNum") int pageNum, Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("activeUser", userRepository.findByEmail(principal.getName()));
        else model.addAttribute("activeUser", null);

        Page<Event> page = eventService.getPastEvents(pageNum);
        List<Event> listEvents = page.getContent();
        model.addAttribute("listEvents", listEvents);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalEvents", page.getTotalElements());
        return "listOfPastEvents";
    }

    @GetMapping("/showNewEventForm")
    public String showNewEventForm(Model model) {
        // create model attribute to bind form data
        Event event = new Event();
        model.addAttribute("event", event);

        return "new_event";
    }


    @PostMapping("/saveEvent")
    public String saveEvent(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Event event, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            return "new_event";
        } else {
            User user = userRepository.findByEmail(principal.getName());
            event.setUser(user);
            eventService.saveEvent(file, event);
            return "redirect:/list";
        }
    }

    @PostMapping("/saveEvent2")
    public String saveEvent2(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Event event, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            return "update_event";
        } else {
            User user = userRepository.findByEmail(principal.getName());
            event.setUser(user);
            eventService.saveEvent(file, event);
            return "redirect:/list";
        }
    }

    @GetMapping("/showAdressForEvent/{id}")
    public String showTimetableForm(@PathVariable(value = "id") Long id, Model model){
        Event event = eventService.getEventById(id);
        model.addAttribute("event", event);
        return "eventAdress";
    }



    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

        // get event from the service
        Event event = eventService.getEventById(id);

        // set event as a model attribute to pre-populate the form
        model.addAttribute("event", event);
        return "update_event";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable(value = "id") Long id) {

        // call delete event method
        this.eventService.deleteEventById(id);
        return "redirect:/list";
    }

    @GetMapping("/showDescription/{id}")
    public String showDescription(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3, Model model4, Model model5,Model model6, Principal principal) {
        // get event from the service
        Event event = eventService.getEventById(id);
        Comment comment = new Comment();
        // set event as a model attribute to pre-populate the form
        model.addAttribute("event", event);
        model2.addAttribute("comment", comment);
        model3.addAttribute("allComments", commentRepository.findByEventId(id));
        model4.addAttribute("isEventEnded", eventService.isEventEnded(id));
        if(principal == null){
            model5.addAttribute("hasUserBoughtTicket", 0);
        }else{
            model5.addAttribute("hasUserBoughtTicket", eventService.hasUserBoughtTicket(userRepository.findByEmail(principal.getName()).getId(),id));
        }
        model6.addAttribute("howManyTicketTypesForEvent", eventService.howManyTicketsTypesByEventId(id));
        return "description";
    }


    @GetMapping("/users/export/pdf/{id}")
    public void exportToPDF(@PathVariable(value = "id") Long id, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Tickets_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Tickets> listTickets =  ticketService.getAllTickets(id);

        TicketPDFExporter exporter = new TicketPDFExporter(listTickets, eventService.getEventById(id).getName());
        exporter.export(response);

    }
}


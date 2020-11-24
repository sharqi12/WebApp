package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.TicketRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.TicketService;
import com.WEBAPP.WEBAPP.service.TimetableService;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.web.dto.TimeTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TimetableController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TimetableService timetableService;



    @GetMapping("/showTimetableForm/{id}")
    public String showTimetableForm(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3, Model model4, Principal principal ){
        if (principal != null)
            model3.addAttribute("activeUser", userRepository.findByEmail(principal.getName()));
        else model3.addAttribute("activeUser", null);
        Event event = eventService.getEventById(id);
        model4.addAttribute("timetable", new TimeTableDto());
        model2.addAttribute("listTimetables", timetableService.getTimetableByEventId(id));
        model.addAttribute("event", event);
        return "timetable";
    }

    @PostMapping("/saveTimetable/{id}")
    public String saveTimetable(@ModelAttribute("timetable") @Valid TimeTableDto timetable, @PathVariable(value = "id") Long id, Errors errors, Principal principal) {
        //ZABEZPIECZENIE PRZED NIEAUTORYZOWANYM DODANIEM HARMONOGRAMU
        if(userRepository.findByEmail(principal.getName()).getId() == eventService.getEventById(id).getUser().getId()) {
            if (errors.hasErrors()) {
                return "timetable";
            } else {
                timetableService.saveTimetable(timetable, id);
                return "redirect:/showTimetableForm/" + id;
                }
        }
        else return "redirect:/showTimetableForm/" + id;
    }

    @GetMapping("/deleteTimeline/{id}/{id2}")
    public String deleteEvent(@PathVariable(value = "id") Long id, @PathVariable(value = "id2") Long eventId, Principal principal ) {

        //ZABEZPIECZENIE PRZED NIEAUTORYZOWANYM USUNIĘCIEM HARMONOGRAMU
        if(userRepository.findByEmail(principal.getName()).getId()== eventService.getEventById(eventId).getUser().getId()){
            timetableService.deleteTimetable(id);
            return "redirect:/showTimetableForm/"+eventId;
        }
        return "redirect:/showTimetableForm/"+eventId;
    }
}

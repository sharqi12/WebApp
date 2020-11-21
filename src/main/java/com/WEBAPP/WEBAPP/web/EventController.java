package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.EventService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    // display list of employees
    @GetMapping("/list")
    public String viewHomePage(Model model, Principal principal) {
        if (principal != null)
            model.addAttribute("activeUser", userRepository.findByEmail(principal.getName()));
        else model.addAttribute("activeUser", null);
        ;
        model.addAttribute("listEvents", eventService.getAllEvents());
        return "list";
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
    public String saveEvent2(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return "update_event";
        } else {
            eventService.saveEvent(file, event);
            return "redirect:/list";
        }
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
    public String showDescription(@PathVariable(value = "id") Long id, Model model, Model model2, Model model3) {
        // get event from the service
        Event event = eventService.getEventById(id);
        Comment comment = new Comment();
        // set event as a model attribute to pre-populate the form
        model.addAttribute("event", event);
        model2.addAttribute("comment", comment);
        model3.addAttribute("allComments", commentRepository.findByEventId(id));
        return "description";
    }

    @ResponseBody
    @RequestMapping(value = "/acceptedPayment/{value}", method = RequestMethod.POST)
    public String getSearchResultViaAjax(@RequestParam(value = "value") Integer value, @RequestParam(value = "user_id") Integer user_id, @RequestParam(value = "event_id") Long event_id)
    {

        System.out.println("Cena: "+value+" UserId: "+user_id+" EventId: "+ event_id);
        return String.valueOf(value);
    }

}


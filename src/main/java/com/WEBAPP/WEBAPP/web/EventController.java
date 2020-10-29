package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    // display list of employees
    @GetMapping("/list")
    public String viewHomePage(Model model) {
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
    public String saveEvent(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid Event event, Errors errors) {
        if(errors.hasErrors()){
            return "update_event";
        } else {
        eventService.saveEvent(file, event);
        return "redirect:/list";
        }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get event from the service
        Event event = eventService.getEventById(id);

        // set event as a model attribute to pre-populate the form
        model.addAttribute("event", event);
        return "update_event";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable (value = "id") long id) {

        // call delete event method
        this.eventService.deleteEventById(id);
        return "redirect:/list";
    }

    @GetMapping("/showDescription/{id}")
    public String showDescription(@PathVariable( value = "id") long id, Model model) {

        // get event from the service
        Event event = eventService.getEventById(id);

        // set event as a model attribute to pre-populate the form
        model.addAttribute("event", event);
        return "description";
    }
}

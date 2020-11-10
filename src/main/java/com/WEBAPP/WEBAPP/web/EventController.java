package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.service.CommentService;
import com.WEBAPP.WEBAPP.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private CommentRepository commentRepository;

    // display list of events
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
    public String saveEvent(@ModelAttribute @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            return "new_event";
        } else {
            eventService.saveEvent(event);
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
    /*@GetMapping("/showDescription/{id}/comments")
    public List <Comment> getCommentByEvent(@PathVariable(value = "id") Long id){
        return commentRepository.findByEvent_Id(id);
    }*/
}

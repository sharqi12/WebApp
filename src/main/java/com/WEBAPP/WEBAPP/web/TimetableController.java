package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Timetable;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TimetableRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TimetableController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/showTimetableForm")
    public String showTimetableForm(Model model) {
        // create model attribute to bind form data
        Timetable timetable = new Timetable();
        model.addAttribute("timetable", timetable);
        return "timetable";
    }


    @PostMapping("/saveTimetable")
    public String saveTimetable(@ModelAttribute @Valid Timetable timetable, Errors errors,Model model) {
        if (errors.hasErrors()) {
            return "timetable";
        } else {
            model.addAttribute("timetable", timetable);
            timetableService.saveTimetable(timetable);
            return "redirect:/list";
        }
    }
}

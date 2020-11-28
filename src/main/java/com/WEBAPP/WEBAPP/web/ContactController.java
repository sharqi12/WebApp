package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Contact;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.service.ContactService;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.UserService;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping("/showNewContactForm")
    public String showNewContactForm(Model model) {
        // create model attribute to bind form data
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        List<String> listCategory = Arrays.asList("Działanie strony", "Bilety i płatności", "Administracja", "Inne");
        model.addAttribute("listCategory", listCategory);
        return "new_contact";
    }

    @PostMapping("/saveContact")
    public String saveContact(@ModelAttribute @Valid Contact contact, Errors errors, Model model) {
        if(errors.hasErrors()){
            List<String> listCategory = Arrays.asList("Działanie strony", "Bilety i płatności", "Administracja", "Inne");
            model.addAttribute("listCategory", listCategory);
            return "new_contact";
        } else {
            contactService.saveContact(contact);
            return "index";
        }
    }

    @GetMapping("/problemsList")
    public String viewProblemsList(Model model, Model model2, Principal principal) {
        /*if (principal != null)
            model.addAttribute("activeUser", userService.loadUserByUsername(principal.getName()));
        else model.addAttribute("activeUser", null);
        */
        model.addAttribute("listProblemsUnsolved", contactService.findUnsolved());
        model2.addAttribute("listProblemsSolved", contactService.findSolved());
        return "problemsList";
    }

    @GetMapping("/showProblem/{id}")
    public String showProblemById(@PathVariable(value = "id") Long id, Model model, Model model2, Principal principal ){
        if (principal != null)
            model2.addAttribute("activeUser", userService.loadUserByUsername(principal.getName()));
        else model2.addAttribute("activeUser", null);
        model.addAttribute("problem", contactService.getContactById(id));
        return "problemDescription";
    }
    @GetMapping("/solveProblem/{id}")
    public String solveProblem(@PathVariable(value = "id") Long id) {
        Contact contact = contactService.getContactById(id);
        contact.setSolved(true);
        contactService.saveContact(contact);
        return "redirect:/problemsList";
    }

}

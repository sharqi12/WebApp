package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Contact;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.service.ContactService;
import com.WEBAPP.WEBAPP.service.EventService;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
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
import java.util.Arrays;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

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
}

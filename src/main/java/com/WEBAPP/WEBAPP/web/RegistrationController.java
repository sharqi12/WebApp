package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;

@Controller // This means that this class is a Controller
@RequestMapping("/registration") // This means URL's start with /registration (after Application path)
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto() ;
    }

    @GetMapping
    public String showRegistrationForm(){ return "registration"; }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationDto registrationDto){
        userService.save(registrationDto);
        return "/registrationsuccess";
    }

}
package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.MyUserPrincipal;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.MyUserDetailsService;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.service.UserServiceImpl;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CurrentUserControllerAdvice {

    //@Autowired
    //private UserRepository userRepository;
/*
    MyUserDetailsService userDetails =
            (MyUserDetailsService) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();*/

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public CurrentUserControllerAdvice(UserService userService) {
        super();
        this.userService = userService;
    }

    @RequestMapping("/profile")
    public String showProfilePage(Model model, Principal principal){

        //String email = principal.getName();
        // User user = userService.loadUserByUsername(email);
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "profile";
    }

    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

    @RequestMapping("/change_user_password")
    public String showChangeUserPasswordFormPage(Model model, Principal principal){

        //String email = principal.getName();
        // User user = userService.loadUserByUsername(email);
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "change_user_password";
    }

    @RequestMapping("/update_profile")
    public String showUpdateProfilePage(Model model, Principal principal){

        //String email = principal.getName();
        // User user = userService.loadUserByUsername(email);
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "update_profile";
    }

    @RequestMapping(value = "/update_nick", method = RequestMethod.POST)
    //public String update_nick(@ModelAttribute("user")UserRegistrationDto user, Principal principal){
    public String update_nick(@ModelAttribute UserRegistrationDto user, Principal principal, Model model) {
        // model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        //user.setNick("bghcvbcv");
        this.userService.save(user);
        return "index";
    }
}
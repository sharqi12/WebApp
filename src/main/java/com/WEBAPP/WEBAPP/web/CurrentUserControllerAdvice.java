package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.MyUserPrincipal;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.MyUserDetailsService;
import com.WEBAPP.WEBAPP.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private UserServiceImpl userService;

    @RequestMapping("/profile")
    public String showProfilePage(Model model, Principal principal){

        //String email = principal.getName();
       // User user = userService.loadUserByUsername(email);
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "/profile";
    }


    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }

}
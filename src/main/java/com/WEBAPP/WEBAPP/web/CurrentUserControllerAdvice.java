package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.MyUserPrincipal;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.MyUserDetailsService;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.service.UserServiceImpl;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

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

    //@Autowired
    private UserService userService;

    //@Autowired
    //private UserRepository userRepository;

    public CurrentUserControllerAdvice(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/show_profile")
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

    @GetMapping("/show_update_profile")
    public String showUpdateProfilePage(Model model, Principal principal){

        //String email = principal.getName();
        //User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "update_profile";
    }

    @PostMapping("/saveNick")
    public String saveNick(@ModelAttribute("user") User user, Principal principal){

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        userService.save(user);
        return "/profile";
    }
    @PostMapping("/savePassword")
    public String savePassword(@ModelAttribute("user") User user, Errors errors, Principal principal){
        if(errors.hasErrors()){
            return "change_user_password";
        } else {

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            userService.save(user);
            return "/profile";
        }
    }

    /*

    @RequestMapping(value = "/update_nick", method = RequestMethod.POST)
    //public String update_nick(@ModelAttribute("user")UserRegistrationDto user, Principal principal){
    public String update_nick(@ModelAttribute UserRegistrationDto user, Principal principal, Model model) {
       // model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        //user.setNick("bghcvbcv");
        this.userService.save(user);
    return "index";
    }*/

}
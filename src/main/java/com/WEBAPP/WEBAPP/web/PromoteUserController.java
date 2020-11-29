package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.service.UserService;
import com.WEBAPP.WEBAPP.service.UserServiceImpl;
import com.WEBAPP.WEBAPP.web.dto.UserPromoDto;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller // This means that this class is a Controller
// This means URL's start with /registration (after Application path)
public class PromoteUserController {

    //@Autowired
    private UserService userService;

    //@Autowired
    //private UserRepository userRepository;

    public PromoteUserController(UserServiceImpl userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/creatorProm")
    public String showCreatorPromPage(Model model){
        model.addAttribute("user", new UserPromoDto());
        return "creatorProm";
    }

    @PostMapping("promoteUser")
    public String changeRoleForCreator(@ModelAttribute("user") UserPromoDto user){

        User user1 = new User();
        user1 = (User) userService.loadUserByUsername(user.getEmail());
        Integer userId = user1.getId();
        userService.promoToCreator(userId);

        return "/index";
    }
}
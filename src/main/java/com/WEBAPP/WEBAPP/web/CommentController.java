package com.WEBAPP.WEBAPP.web;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.CommentService;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Timestamp;
import java.net.Authenticator;
import java.util.List;
import java.util.Optional;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;

import static java.lang.System.currentTimeMillis;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;



    @PostMapping("/saveComment/{id}")
    public String saveComment(@PathVariable( value = "id")  Long id, @ModelAttribute @Valid Comment comment,
                              Authentication authentication, Errors errors, Principal principal) {
        // save comment to database
        //model.addAttribute("event", event);

        if(errors.hasErrors()){
            return "redirect:/list";
        } else {

            Event event = eventService.getEventById(id);
            comment.setEvent(event);
            User user = userRepository.findByEmail(principal.getName());
            comment.setUser(user);
            //comment.setUser(userService.findByNick(authentication.getName()));
            comment.setIdC(null);
            comment.setDateCreated(new Timestamp(currentTimeMillis()));
            commentService.saveComment(comment);
            return "redirect:/showDescription/{id}";
        }



    }
    @Transactional
    @GetMapping ("/deleteComment/{idC}/{id}")
    public String deleteComment(@PathVariable( value = "idC")  Long idC, @PathVariable( value = "id")  Long id,
                                Authentication authentication, Principal principal) {

        Comment comment=commentService.findOne(idC);

      /*  if (!authentication.getName().equals(comment.getUser().getNick())) {
            return "redirect:/list";
        }*/
        comment.setEvent(null);
        comment.setUser(null);
        commentService.delete(comment);

        return "redirect:/showDescription/{id}";
    }


}





package com.WEBAPP.WEBAPP.web;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.service.CommentService;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.validation.Errors;
import javax.validation.Valid;


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



    @PostMapping("/saveComment/{id}")
    public String saveComment(@PathVariable( value = "id")  Long id, @ModelAttribute @Valid Comment comment,  Errors errors) {
        // save comment to database
        //model.addAttribute("event", event);

        if(errors.hasErrors()){
            return "redirect:/list";
        } else {
            Event event = eventService.getEventById(id);
            comment.setEvent(event);
            //comment.setUser(user);
            comment.setId(null);
            commentService.saveComment(comment);
            return "redirect:/showDescription/{id}";
        }



    }
    @GetMapping ("/deleteComment/{idC}/event/{id}")
    public String deleteComment(@PathVariable( value = "idC")  Long idC, @PathVariable( value = "id")  Long id) {

        //this.commentRepository.findByIdAndEventId(idC, id).map(comment->commentRepository.delete(comment););
        return "redirect:/showDescription/{id}";

    }


}





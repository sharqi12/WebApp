package com.WEBAPP.WEBAPP.web;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


/*@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/newComment")
    public String newComment(Model model) {
        // create model attribute to bind form data
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "new_comment";
    }
    @GetMapping("/showComment/{id}")
    public String showComment(@PathVariable( value = "id") long id, Model model) {

        // get comment from the service
        Comment comment = commentService.getCommentById(id);

        // set comment as a model attribute to pre-populate the form
        model.addAttribute("comment", comment);
        return "opinion";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment)
    {
        commentService.saveComment(comment);
        return "redirect:/list";
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable (value="id") long id)
    {
        this.commentService.deleteCommentById(id);
        return "delete_comment";
    }*/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


   /* @GetMapping("/listComments")
    public String viewHome(Model model) {
        model.addAttribute("listComments", commentService.getAllComment());
        return "all_opinion";
    }*/

    @GetMapping("/newComment")
    public String newComment(Model model) {

        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        //return "new_comment"
        return "redirect:/list";
    }

    @PostMapping("/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        // save comment to database
        commentService.saveComment(comment);
        return "redirect:/list";
    }

    @GetMapping("/showCommentForUpdate/{id}")
    public String showCommentForUpdate(@PathVariable( value = "id") long id, Model model) {

        // get comment from the service
        Comment comment = commentService.getCommentById(id);

        // set comment as a model attribute to pre-populate the form
        model.addAttribute("comment", comment);
        //return "update_comment"
         return "redirect:/list";
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable (value = "id") long id) {

        // call delete comment method
        this.commentService.deleteCommentById(id);
        return "redirect:/list";
    }

    @GetMapping("/showComment/{id}")
    public String showComment(@PathVariable( value = "id") long id, Model model) {

        // get comment from the service
        Comment comment = commentService.getCommentById(id);

        // set comment as a model attribute to pre-populate the form
        model.addAttribute("comment", comment);
        return "redirect:/list";
    }
}





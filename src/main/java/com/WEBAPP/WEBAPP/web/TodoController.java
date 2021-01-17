package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Todo;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.TodoRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/todo")
    public String viewTodoPage(Model model, Model model2, Principal principal) {
        Todo todo = new Todo();
        model.addAttribute("todo", todo);
        User user = userRepository.findByEmail(principal.getName());
        todo.setUser(user);
        int id=todo.getUser().getId();
        model2.addAttribute("allTodo", todoRepository.findByUserId(id));

        return "todo";
    }
    @PostMapping("/saveAct")
    public String saveAct( @ModelAttribute @Valid Todo todo,
                           Authentication authentication, Errors errors, Principal principal) {
        if (errors.hasErrors()) {
            return "redirect:/list";
        } else {

            User user = userRepository.findByEmail(principal.getName());
            todo.setUser(user);
            todo.setIdT(null);
            todoService.saveAct(todo);
            return "redirect:/todo";
        }

    }
    @Transactional
    @GetMapping ("/deleteAct/{idT}")
    public String deleteAct(@PathVariable( value = "idT")  Long idT) {

        Todo todo=todoService.findOne(idT);
        todo.setUser(null);
        todoService.delete(todo);
        return "redirect:/todo";
    }
}

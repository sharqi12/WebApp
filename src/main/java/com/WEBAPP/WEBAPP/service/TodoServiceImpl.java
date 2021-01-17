package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Todo;
import com.WEBAPP.WEBAPP.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void saveAct(Todo todo) {
        this.todoRepository.save(todo);
    }

    @Override
    public Todo findOne(Long idT) {
        return todoRepository.findByIdT(idT).get();
    }

    @Override
    public void delete(Long idT) {
        delete(findOne(idT));
    }

    @Override
    public void delete(Todo todo) {
        todoRepository.delete(todo);
    }


}

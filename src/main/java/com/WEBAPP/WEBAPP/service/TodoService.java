package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Todo;

import java.util.List;

public interface TodoService {
    void saveAct(Todo todo);
    Todo findOne(Long idT);
    void delete(Long idT);
    void delete(Todo todo);
}

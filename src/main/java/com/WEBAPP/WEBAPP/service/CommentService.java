package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.ui.Model;

import java.util.List;
import java.lang.Long;

public interface CommentService {
    void saveComment(Comment comment);
    void deleteCommentById(Long idC);
    Comment findOne(Long idC);
    void delete(Long idC);
    void delete(Comment comment);
    void deleteCommentsByEventId(Long id);
}

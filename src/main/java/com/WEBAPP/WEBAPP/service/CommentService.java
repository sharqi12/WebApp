package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.ui.Model;

import java.util.List;
import java.lang.Long;

public interface CommentService {
    void saveComment(Comment comment);
    void deleteCommentById(Long idC);
}


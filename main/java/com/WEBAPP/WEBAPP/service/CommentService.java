package com.WEBAPP.WEBAPP.service;
import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.ui.Model;

import java.util.List;

/*public interface CommentService {
    void newComment(Comment comment);
    void saveComment(Comment comment);
    void deleteCommentById(long id);
    Comment getCommentById(long id);

}*/
public interface CommentService {
    List<Comment> getAllComment();
    void saveComment(Comment comment);
    Comment getCommentById(long id);
    void deleteCommentById(long id);
}


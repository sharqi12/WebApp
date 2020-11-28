package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/*@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment getCommentById(long id) {
        Optional< Comment > optional = commentRepository.findById(id);
        Comment comment = null;
        if (optional.isPresent()) {
            comment = optional.get();
        } else {
            throw new RuntimeException(" Comment not found for id :: " + id);
        }
        return comment;
    }
    @Override
    public void newComment(Comment comment) {
        this.commentRepository.save(comment);
    }
    @Override
    public void saveComment(Comment comment) {
        this.commentRepository.save(comment);
    }
    @Override
    public void deleteCommentById(long id) {
        this.commentRepository.deleteById(id);
    }
}*/


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void saveComment(Comment comment) {
        this.commentRepository.save(comment);
    }


    @Override
    public void deleteCommentById(Long idC) {
        this.commentRepository.deleteById(idC);
    }

    @Override
    public Comment findOne(Long idC) {
        return commentRepository.findByIdC(idC).get();
    }

    @Override
    public void delete(Long idC) {
        delete(findOne(idC));
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public void deleteCommentsByEventId(Long id) {

        commentRepository.deleteCommentsByEventId(id);
    }
}

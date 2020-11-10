package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.lang.Long;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    //@Query(value = "SELECT * FROM comments where rating=5", nativeQuery = true)

    List<Comment> findByEventId(Long id);
    //Optional<Comment> findByIdAndEventId(Long idC, Long id);
}

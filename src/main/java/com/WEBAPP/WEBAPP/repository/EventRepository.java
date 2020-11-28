package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    @Query(value = "SELECT id_user from events WHERE id =:em",nativeQuery = true)
    Integer findByUserId(@Param("em")Integer id);
    int deleteEventById(Long id);
}

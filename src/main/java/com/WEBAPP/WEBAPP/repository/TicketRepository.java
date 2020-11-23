package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.WEBAPP.WEBAPP.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {


    @Modifying
    @Query(value = "SELECT * FROM tickets WHERE user_id =:em",nativeQuery = true)
    List <Tickets> findByUserId(@Param("em")Integer id);

    @Modifying
    @Query(value = "SELECT * FROM tickets WHERE event_id =:em",nativeQuery = true)
    List <Tickets> findByEventId(@Param("em")Long id);


}

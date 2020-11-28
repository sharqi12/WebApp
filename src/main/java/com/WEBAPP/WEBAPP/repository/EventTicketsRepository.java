package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.EventTickets;
import com.WEBAPP.WEBAPP.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTicketsRepository extends JpaRepository<EventTickets, Long> {

    @Query(value = "SELECT * FROM event_tickets WHERE event_id =:em",nativeQuery = true)
    List<EventTickets> findTypesByEventId(@Param("em")Long id);

    @Query(value = "SELECT COUNT(id) FROM event_tickets WHERE event_id =:em",nativeQuery = true)
    Integer howManyTicketsTypesByEventId(@Param("em")Long id);

    @Query(value = "SELECT ticket_name FROM event_tickets WHERE ticket_price =:em AND event_id =:emm",nativeQuery = true)
    String findNameByValue(@Param("em")Integer value, @Param("emm")Long event_id);

}

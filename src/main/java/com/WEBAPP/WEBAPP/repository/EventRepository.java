package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    @Query(value = "SELECT id_user from events WHERE id =:em",nativeQuery = true)
    Integer findByUserId(@Param("em")Integer id);

    @Query(value = "SELECT * from events WHERE date <:emm",nativeQuery = true)
    Page<Event> findPastEvents(@Param("emm") String data, Pageable pageable);

    @Query(value = "SELECT * from events WHERE date >:emm",nativeQuery = true)
    Page<Event> findFutureEvents(@Param("emm") String data, Pageable pageable);

    @Query(value = "SELECT COUNT(id) from events WHERE id =:em and date <:emm",nativeQuery = true)
    Integer isEventEnded(@Param("em")Long id,@Param("emm") String data);
}

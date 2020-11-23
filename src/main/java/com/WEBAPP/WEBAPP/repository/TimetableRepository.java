package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value = "SELECT * FROM timetables WHERE id_event =:em",nativeQuery = true)
    Integer findByEventId(@Param("em")Integer id);
}

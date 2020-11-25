package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    @Query(value = "SELECT * FROM timetables WHERE id_events =:em ORDER BY begin",nativeQuery = true)
    List<Timetable> findByEventId(@Param("em")Long id);


}

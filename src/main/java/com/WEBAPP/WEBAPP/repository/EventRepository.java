package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}

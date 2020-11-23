package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Tickets;
import com.WEBAPP.WEBAPP.model.Timetable;
import com.WEBAPP.WEBAPP.web.dto.TimeTableDto;

import java.util.List;

public interface TimetableService {
    List<Timetable> getTimetableByEventId(Long id);
    void saveTimetable(Timetable timetable);
    void deleteTimetable(Timetable timetable);
    void saveTimetable(TimeTableDto timeTableDto, Long id);
}

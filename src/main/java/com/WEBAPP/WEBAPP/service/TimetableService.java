package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Timetable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TimetableService {
    List<Timetable> getAllTimetables();
    void saveTimetable(Timetable timetable);
    Timetable getTimetableById(Long id);
    void deleteTimetableById(Long id);
}

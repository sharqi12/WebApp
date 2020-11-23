package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Timetable;
import com.WEBAPP.WEBAPP.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableRepository timetableRepository;

    @Override
    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    @Override
    public void saveTimetable(Timetable timetable) {
        this.timetableRepository.save(timetable);
    }

    @Override
    public Timetable getTimetableById(Long id) {
        Optional< Timetable > optional = timetableRepository.findById(id);
        Timetable timetable = null;
        if (optional.isPresent()) {
            timetable = optional.get();
        } else {
            throw new RuntimeException(" Timetable not found for id :: " + id);
        }
        return timetable;
    }

    @Override
    public void deleteTimetableById(Long id) {
        this.timetableRepository.deleteById(id);
    }
}

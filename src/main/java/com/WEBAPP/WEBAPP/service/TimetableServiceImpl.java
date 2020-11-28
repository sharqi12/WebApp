package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Timetable;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.TimetableRepository;
import com.WEBAPP.WEBAPP.web.dto.TimeTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService{

    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private EventService eventService;


    @Override
    public List<Timetable> getTimetableByEventId(Long id){
        return timetableRepository.findByEventId(id);
    }

    @Override
    public void saveTimetable(Timetable timetable){
        timetableRepository.save(timetable);
    }

    @Override
    public void deleteTimetable(Long id){
        timetableRepository.deleteById(id);
    }

    @Override
    public void saveTimetable(TimeTableDto timeTableDto, Long id) {
        Timetable timetable = new Timetable(timeTableDto);
        timetable.setEvent(eventService.getEventById(id));
        timetableRepository.save(timetable);
    }

    @Override
    public Integer howManyTimetablesByEventId(Long id){
        return timetableRepository.howManyTimetablesByEventId(id);
    }

}

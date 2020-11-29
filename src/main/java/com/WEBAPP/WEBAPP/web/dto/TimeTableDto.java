package com.WEBAPP.WEBAPP.web.dto;

import com.WEBAPP.WEBAPP.model.Timetable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class TimeTableDto {

    public TimeTableDto(){
    }

    public TimeTableDto(Timetable timetable){
        this.stage=timetable.getStage();
        this.begin=timetable.getBegin();
        this.end=timetable.getEnd();
        this.exhibit=timetable.getExhibit();
    }

    private Long id;

    private String stage;

    private String exhibit;

    private String begin;

    private String end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getExhibit() {
        return exhibit;
    }

    public void setExhibit(String exhibit) {
        this.exhibit = exhibit;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}

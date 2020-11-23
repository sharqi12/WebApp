package com.WEBAPP.WEBAPP.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "timetables")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stage")
    @NotBlank(message = "Podaj nazwe sali!")
    private String stage;

    @Column(name = "exhibit")
    @NotBlank(message = "Podaj nazwe wystawy!")
    private String exhibit;

    @Column(name = "begin")
    @NotBlank(message = "Podaj godzinę rozpoczęcia!")
    private String begin;

    @Column(name = "end")
    @NotBlank(message = "Podaj godzinę zakończenia!")
    private String end;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_events", referencedColumnName = "id")
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

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

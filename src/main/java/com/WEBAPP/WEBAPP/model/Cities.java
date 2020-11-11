package com.WEBAPP.WEBAPP.model;

import javax.persistence.*;
import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "cities")
public class Cities {



    public Long getIdCities() {
        return idCities;
    }

    public void setIdCity(Long idCities) {
        this.idCities = idCities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCities;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Column(name = "regionName")
    private String regionName;

    public Cities() {
    }
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eve_city")
    private Event event;
}

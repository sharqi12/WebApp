package com.WEBAPP.WEBAPP.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idC;
    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private int rating;


    @Column(name= "dateCreated")
    private Timestamp dateCreated;



    @ManyToOne(cascade = {CascadeType.ALL})
    //@JoinColumn(name = "eve_com")
    private Event event;



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_nick")
    private User user;

    @PreRemove
    public void checkUserAssociations(){
        setUser(null);
        setEvent(null);
    }
    public Comment() {
    }


    public Long getIdC() {
        return idC;
    }

    public void setIdC(Long idC) {
        this.idC = idC;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
    @Override
    public String toString() {
        return "Comment{" +

                ", text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", rating=" + rating +

                '}';
    }


}
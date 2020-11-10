package com.WEBAPP.WEBAPP.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
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




    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eve_com")
    private Event event;



   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "eve_usr" ,nullable = false)
    private User user;*/


    public Comment() {
    }


    public Long getId() {
        return idC;
    }

    public void setId(Long idC) {
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
    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Override
    public String toString() {
        return "Comment{" +

                ", text='" + text + '\'' +
                ", rating=" + rating +

                '}';
    }
}
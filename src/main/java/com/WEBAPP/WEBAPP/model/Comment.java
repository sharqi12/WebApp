package com.WEBAPP.WEBAPP.model;

import javax.persistence.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "text")
    private String text;
    @Column(name = "rating")
    private int rating;


    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comments", referencedColumnName = "id")
    private Event event;*/

    public Comment() {
    }

   /* public Comment(String text) {
        super();
        this.text = text;
    }*/
    public long getId() {return id;}
    public void setId(long id) {this.id = id;}
    public String getText() {return text; }
    public void setText(String text) {this.text = text;}
    public int getRating() {return rating; }
    public void setRating(int rating) {this.rating = rating;}

}
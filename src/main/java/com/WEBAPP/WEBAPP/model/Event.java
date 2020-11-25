package com.WEBAPP.WEBAPP.model;

import org.hibernate.annotations.Type;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min=2, max=40, message = "Wprowadz nazwe!")
    private String name;

    @Column(name = "city")
    @Size(min = 2, message = "Za malo liter!")
    @Pattern(regexp = "^([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)$", message = "Wprowadz miasto!")
    private String city;

    @Column(name = "date")
    @NotBlank(message = "Podaj date!")
    private String date;

    @Column(name = "description")
    @Size(min = 10, message = "Wprowadz opis!")
    @Type(type="text")
    private String description;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Column(name = "ticket1")
    @NotNull
    private Integer ticket1;

    @Column(name = "ticket2")
    @NotNull
    private Integer ticket2;

    @Column(name = "adress")
    @NotNull
    private String adress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_events", referencedColumnName = "id")
    List<Comment> comments = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() { return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Comment> getComments() { return comments;}
    public void setComments(List<Comment> comments) {this.comments = comments;}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTicket1() {
        return ticket1;
    }

    public void setTicket1(Integer ticket1) {
        this.ticket1 = ticket1;
    }

    public Integer getTicket2() {
        return ticket2;
    }

    public void setTicket2(Integer ticket2) {
        this.ticket2 = ticket2;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

}

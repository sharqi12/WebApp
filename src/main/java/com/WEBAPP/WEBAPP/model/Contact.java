package com.WEBAPP.WEBAPP.model;

import jdk.jfr.Name;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    //@Pattern(regexp = "[a-zA-Z]*[\\s]{1}[a-zA-Z].*",  message = "wprowadz imie i nazwisko!") ZAKOMENTOWANE BO NIE POZWALA WPROWADZAĆ POLSKICH ZNAKÓW
    @Size(min=6, message = "Minimum 6 znakow!")
    private String name;

    @Column(name = "email")
    @Email(regexp = "^(.+)@(.+)$", message = "Nieprawidłowy email!")
    @Size(min=8, message = "Minimum 8 znakow!")
    private String email;

    @Column(name = "category")
    private String category;


    @Column(name = "note")
    @Size(min = 10, message = "Opisz problem!")
    private String note;

    private boolean solved;

    public Contact() {
    }

    public Contact(long id, @Pattern(regexp = "[a-zA-Z]*[\\s]{1}[a-zA-Z].*", message = "wprowadz imie i nazwisko!") @Size(min = 6, message = "Minimum 6 znakow!") String name, @Email(regexp = "^(.+)@(.+)$", message = "Nieprawidłowy email!") @Size(min = 8, message = "Minimum 8 znakow!") String email, @Size(min = 10, message = "Opisz problem!") String note) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.category = category;
        this.note = note;
        this.solved = false;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

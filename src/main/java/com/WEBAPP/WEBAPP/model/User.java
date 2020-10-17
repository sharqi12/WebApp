package com.WEBAPP.WEBAPP.model;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email")) //Email musi byc unikalny - jeden email do jednego uzytkownika
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nick;

    private String name;

    private String email;

    private String password;

    public User() {
    }

    public User(String nick, String name, String email, String password) {
        super();
        this.nick = nick;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getNick(){
        return nick;
    }
    public void setNick(String nick){
        this.nick=nick;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getPassword(){return email;}
    public void setPassword(String password){
        this.password=password;
    }
}

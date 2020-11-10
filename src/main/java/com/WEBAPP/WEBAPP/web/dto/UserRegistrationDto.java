package com.WEBAPP.WEBAPP.web.dto;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationDto {
    @Size(min=2, max=40, message = "Wprowadź nick!")
    private String nick;
    @Size(min=2, max=40, message = "Wprowadź imię i nazwisko!")
    private String name;
    @Pattern(regexp = "^(.+)@(.+)$", message = "Nieprawidłowy email!")
    @Size(min=7, max=40, message = "Minimum 7 znakow!")
    private String email;

    private String password;
    @Transient
    private String passwordConfirmation;


    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String nick, String name, String email, String password, String passwordConfirmation) {
        super();
        this.nick = nick;
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}

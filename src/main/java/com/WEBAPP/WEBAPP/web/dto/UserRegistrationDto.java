package com.WEBAPP.WEBAPP.web.dto;

public class UserRegistrationDto {
    private String nick;
    private String name;
    private String email;
    private String password;


    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String nick, String name, String email, String password) {
        this.nick = nick;
        this.name = name;
        this.email = email;
        this.password = password;
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
}

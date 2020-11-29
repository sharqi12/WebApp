package com.WEBAPP.WEBAPP.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email")) //Email musi byc unikalny - jeden email do jednego uzytkownika
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nick;

    private String name;

    private String email;

    private String password;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @Transient
    private String passwordConfirmation;

    @Transient
    private String oldPassword;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))

    //private Collection<Role> roles;
    private Collection<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String nick, String name, String email, String password, Collection<Role> roles, String passwordConfirmation) {
        super();
        this.nick = nick;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.passwordConfirmation = passwordConfirmation;
    }

    public User(User user){
        this.id=user.getId();
        this.nick=user.getNick();
        this.name=user.getName();
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.roles=user.getRoles();
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
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    public String getPassword(){ return password; }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.WEBAPP.WEBAPP.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

// You want to extend your User class here
public class MyUserPrincipal extends User implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;

    public MyUserPrincipal(User user) {
        super(user);
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // You don't talk about UserRoles, so return ADMIN for everybody or implement roles.
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // just for example
        return  true;
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    } //NIE WIEM CZY TU MA BYĆ .getUsername() CZY .getEmail()

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    public String getName(){return this.user.getName();}

    public String getNick(){return this.user.getNick();}

    // Just an example to put some addititional Data to your logged in user

    public String getUserDatabase() {
        return "usertable" + Integer.toString(1 + this.user.getId());
    }


}



/*
public class MyUserPrincipal implements UserDetails {
    private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    public String getNick(){
        return this.user.getNick();
    }

    public String getName(){
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}*/
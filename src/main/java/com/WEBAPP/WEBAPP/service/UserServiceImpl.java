package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Role;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import  com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getNick(),
                registrationDto.getName(), registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")), registrationDto.getPasswordConfirmation());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Niepoprawna nazwa lub hasło");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Override
    @PutMapping
    public User getUserById(String email) {
        Optional < User > optional = Optional.ofNullable(userRepository.findByEmail(email));
        User user = null;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException(" User not found for mail :: " + email);
        }
        return user;
    }
}

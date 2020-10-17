package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import  com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getNick(),registrationDto.getName(),registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Niepoprawna nazwa lub hasło");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), null);
    }
}

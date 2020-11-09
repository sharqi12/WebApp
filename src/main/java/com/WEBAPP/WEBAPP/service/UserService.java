package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User save(User user);
}

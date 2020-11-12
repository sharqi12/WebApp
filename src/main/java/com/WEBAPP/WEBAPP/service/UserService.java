package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    User save(MultipartFile file, UserRegistrationDto registrationDto);
    User save(User user);
    User saveWithouPassword(MultipartFile file, User user);
}

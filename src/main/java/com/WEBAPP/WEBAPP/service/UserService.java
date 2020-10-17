package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.web.dto.UserRegistrationDto;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}

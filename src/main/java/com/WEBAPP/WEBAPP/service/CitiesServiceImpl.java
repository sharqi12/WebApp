package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitiesServiceImpl implements CitiesService{
    @Autowired
    private CitiesRepository citiesRepository;

}

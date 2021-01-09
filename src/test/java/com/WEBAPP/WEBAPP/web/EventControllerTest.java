package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Role;
import com.WEBAPP.WEBAPP.model.User;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class EventControllerTest {

    @Test
    void saveEventTest() {
        //given
        EventController eventController = mock(EventController.class);
        given(eventController.getEventList()).willReturn(prepareMockDataEventList());
        //when
        List<Event> events = eventController.getEventList();
        //then
        Assert.assertThat(events, Matchers.hasSize(1));
    }


    private List<Event> prepareMockDataEventList(){
        User user = new User("nick","name","email@wp.pl","password", Arrays.asList(new Role("ROLE_CREATOR")), "password");

        List<Event> events = new ArrayList<>();
        events.add(new Event((long) 1,"Testowy Event", "Lublin", java.time.LocalDateTime.now().toString() , "Testowy opis eventu", null ,"Zemborzcyka 12, Lublin", user));
        return events;
    }

}
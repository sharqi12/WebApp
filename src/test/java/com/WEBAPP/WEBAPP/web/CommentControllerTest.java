package com.WEBAPP.WEBAPP.web;

import com.WEBAPP.WEBAPP.model.Comment;
import com.WEBAPP.WEBAPP.model.Event;
import com.WEBAPP.WEBAPP.model.Role;
import com.WEBAPP.WEBAPP.model.User;
import com.WEBAPP.WEBAPP.repository.CommentRepository;
import com.WEBAPP.WEBAPP.repository.EventRepository;
import com.WEBAPP.WEBAPP.repository.UserRepository;
import com.WEBAPP.WEBAPP.service.CommentService;
import com.WEBAPP.WEBAPP.service.CommentServiceImpl;
import com.WEBAPP.WEBAPP.service.EventService;
import com.WEBAPP.WEBAPP.service.EventServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CommentControllerTest {

    @Mock
    CommentRepository commentRepository;



    @BeforeEach
    public void init(){
        given(commentRepository.findAll()).willReturn(prepare());
    }

    @Test
    void saveComment() {

        Assert.assertThat(commentRepository.findAll(), Matchers.hasSize(1));
    }

    private List<Comment> prepare(){

        User user = new User("nick","name","email@wp.pl","password", Arrays.asList(new Role("ROLE_CREATOR")), "password");
        List<User> users = new ArrayList<>();
        users.add(user);
        List<Event> events = new ArrayList<>();
        events.add(new Event((long) 1,"Testowy Event", "Lublin", java.time.LocalDateTime.now().toString() , "Testowy opis eventu", null ,"Zemborzcyka 12, Lublin", user));
        Comment comment = new Comment("Super wydarzenie", 5, events.get(0), users.get(0));
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        return comments;
    }






}
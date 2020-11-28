package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Contact;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query(value = "SELECT * from contact WHERE solved ='0'",nativeQuery = true)
    List<Contact> findUnsolved();

    @Query(value = "SELECT * from contact WHERE solved ='1'",nativeQuery = true)
    List<Contact> findSolved();

}

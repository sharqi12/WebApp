package com.WEBAPP.WEBAPP.repository;

import com.WEBAPP.WEBAPP.model.Contact;
import com.WEBAPP.WEBAPP.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

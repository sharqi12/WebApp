package com.WEBAPP.WEBAPP.service;

import com.WEBAPP.WEBAPP.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContacts();
    void saveContact(Contact contact);
    Contact getContactById(long id);
    void deleteContactById(long id);
}

package com.scm.SCM20.services;

import com.scm.SCM20.entities.Contact;
import com.scm.SCM20.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface ContactService {
    // Add contact related methods here
     Optional<Contact> getContactById(String id);


     Contact saveContact(Contact contact);
     void deleteContact(String id);
     Optional<Contact> updateContact(Contact contact);
     List<Contact> getAllContacts();

     List<Contact> searchContacts(String name, String email, String phoneNumber);

     List<Contact> getByUserId(String userId);


    List<Contact> getContactsByUser(User user);
}

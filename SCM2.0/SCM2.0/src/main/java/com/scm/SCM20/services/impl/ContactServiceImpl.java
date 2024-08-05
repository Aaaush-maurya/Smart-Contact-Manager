package com.scm.SCM20.services.impl;

import com.scm.SCM20.entities.Contact;
import com.scm.SCM20.entities.User;
import com.scm.SCM20.helper.ResourceNotFoundException;
import com.scm.SCM20.repositories.ContactRepo;
import com.scm.SCM20.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;


    @Override
    public Optional<Contact> getContactById(String id) {
        return contactRepo.findById(id);
    }

    @Override
    public Contact saveContact(Contact contact) {

        contact.setId(UUID.randomUUID().toString());

        return contactRepo.save(contact);
    }

    @Override
    public void deleteContact(String id) {

        var contact1 = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("contact not found"));
        contactRepo.delete(contact1);
    }

    @Override
    public Optional<Contact> updateContact(Contact contact) {
        return Optional.empty();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepo.findAll();
    }

    @Override
    public List<Contact> searchContacts(String name, String email, String phoneNumber) {
        return null;
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return null;
    }

    @Override
    public List<Contact> getContactsByUser(User user) {
        return contactRepo.findByUser(user);
    }
}

package com.scm.SCM20.controller;

import com.scm.SCM20.dto.ContactDto;
import com.scm.SCM20.entities.Contact;
import com.scm.SCM20.services.ContactService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/contacts")
public class ApiController {
@Autowired
    private ContactService contactService;

private ModelMapper modelMapper = new ModelMapper();

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    @GetMapping("/{contactId}")
    public ContactDto getContact(Model model, @PathVariable String contactId) {
     Optional<Contact> contact = contactService.getContactById(contactId);
        ContactDto contactDto = modelMapper.map(contact , ContactDto.class);
        logger.info("Contact : {}", contactDto);
         model.addAttribute("contact", contactDto);
        return contactDto;
    }
}

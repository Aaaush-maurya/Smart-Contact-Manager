package com.scm.SCM20.controller;

import com.scm.SCM20.entities.Contact;
import com.scm.SCM20.entities.User;
import com.scm.SCM20.forms.ContactForm;
import com.scm.SCM20.services.ImageService;
import com.scm.SCM20.services.impl.ContactServiceImpl;
import com.scm.SCM20.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {



    private Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ContactServiceImpl contactService;

    @Autowired
    private ImageService imageService;

    private ModelMapper mapper = new ModelMapper();
    @RequestMapping("/add")
    public String listContacts(Model model) {

        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @PostMapping("/add")
    public String submitContact(@ModelAttribute ContactForm contactForm, Authentication authentication) {

        String username = authentication.getName();
        User user = userService.getUserByEmail(username);



        Contact contact = mapper.map(contactForm, Contact.class);
        contact.setUser(user);

        // image upload
        String fileURL = imageService.uploadImage(contactForm.getContactImage());

          contact.setPicture(fileURL);
          contactService.saveContact(contact);

        return "redirect:/user/contacts/add";
    }

    @RequestMapping
    public String contactList(Model model ,Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByEmail(username);
        model.addAttribute("contacts", contactService.getContactsByUser(user));

        return "user/contact";
    }
}

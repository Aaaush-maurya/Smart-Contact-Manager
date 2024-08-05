package com.scm.SCM20.controller;

import com.scm.SCM20.entities.User;
import com.scm.SCM20.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserServiceImpl userService;
    private Logger log = LoggerFactory.getLogger(UserController.class);

    @ModelAttribute
    public void addLoggedInUser(Model model, Principal principal){
        if(principal == null){
            return;
        }
        String username = principal.getName();
        log.info("User Email: {}", username);

        // getting user data from database
        User user = userService.getUserByEmail(username);
        model.addAttribute("loggedInUser", user);
    }

}

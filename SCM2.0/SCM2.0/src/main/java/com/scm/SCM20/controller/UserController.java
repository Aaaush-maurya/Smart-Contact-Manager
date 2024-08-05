package com.scm.SCM20.controller;

import com.scm.SCM20.entities.User;
import com.scm.SCM20.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


//    @Autowired
//    private UserServiceImpl userService;
//    private Logger log = LoggerFactory.getLogger(UserController.class);

//    @ModelAttribute
//    public void addLoggedInUser(Model model,Principal principal){
//        String username = principal.getName();
//        log.info("User Email: {}", username);
//
//        // getting user data from database
//        User user = userService.getUserByEmail(username);
//        model.addAttribute("loggedInUser", user);
//    }
    // user dashboard page
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }


    // user profile page
    @RequestMapping("/profile")
    public String userProfile() {

        return "user/profile";
    }
}

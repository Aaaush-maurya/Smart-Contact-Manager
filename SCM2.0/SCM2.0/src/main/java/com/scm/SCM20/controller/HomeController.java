package com.scm.SCM20.controller;

import com.scm.SCM20.entities.User;
import com.scm.SCM20.forms.UserForm;
import com.scm.SCM20.helper.Message;
import com.scm.SCM20.helper.MessageType;
import com.scm.SCM20.services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    private UserServiceImpl userService;

    private ModelMapper modelMapper = new ModelMapper();

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {

        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        UserForm userform = new UserForm();
        model.addAttribute("userform", userform);
        return "signup";
    }

    @PostMapping("/do-processing")
    public String processingForm(@ModelAttribute UserForm userForm, HttpSession session) {


        // saving data in database
        User user =  modelMapper.map(userForm, User.class);
        userService.saveUser(user);

        // showing the message
        Message message =Message.builder().content("Registration successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/login";
    }
}

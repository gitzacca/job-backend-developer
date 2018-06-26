package br.com.intelipost.application;

import br.com.intelipost.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public String save(@ModelAttribute(value = "userParams") UserParams userParams, BindingResult errors, Model model) {
        User user = new User(userParams.getName(), new Email(userParams.getEmail()),
                new Credentials(userParams.getEmail(), new Password(userParams.getPassword())));
        userService.save(user);
        return "/login";
    }

}

package br.com.intelipost.application;

import br.com.intelipost.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/user/register")
    public String registerRender(Model model) {
        model.addAttribute("userParams", new UserParams());
        return "/register";
    }

    @PostMapping("/user/register")
    public String save(@ModelAttribute(value = "userParams") UserParams userParams, BindingResult errors, Model model) {
        User user = new User(userParams.getName(),
                new Email(userParams.getEmail()),
                new Credentials(userParams.getUsername(),
                        new Password(userParams.getPassword())));
        userService.save(user);
        return "/login";
    }

    @PostMapping("/success")
    public String sucessRender(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "/index";
    }
}

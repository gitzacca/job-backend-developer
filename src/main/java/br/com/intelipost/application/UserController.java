package br.com.intelipost.application;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @GetMapping("/user/register")
    public String registerRender(Model model) {
        model.addAttribute("userParams", new UserParams());
        return "/register";
    }

    @PostMapping("/user/register")
    public String save(@ModelAttribute(value = "userParams") UserParams userParams, BindingResult errors, Model model) {
        System.out.println(userParams.getName());
        return "/login";
    }
}

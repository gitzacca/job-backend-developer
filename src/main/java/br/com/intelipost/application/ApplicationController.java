package br.com.intelipost.application;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller responsável por reenderizar as páginas do sistema.
 *
 */
@Controller
public class ApplicationController {

    @GetMapping("/register")
    public String registerRender(Model model) {
        model.addAttribute("userParams", new UserParams());
        return "/register";
    }

    @PostMapping("/success")
    public String sucessRender(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "/index";
    }

}

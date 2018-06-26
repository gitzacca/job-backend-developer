package br.com.intelipost.application;

import br.com.intelipost.domain.Email;
import br.com.intelipost.domain.User;
import br.com.intelipost.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserService userService;

    @Autowired
    public ApplicationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerRender(Model model) {
        model.addAttribute("userParams", new UserParams());
        return "/register";
    }

    @PostMapping("/success")
    public String sucessRender(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userService.findBy(new Email(email));
        model.addAttribute("user", user);

        return "/index";
    }

}

package br.com.intelipost.application;

import br.com.intelipost.domain.User;
import br.com.intelipost.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public Page<User> list(Pageable pageable) {
        Page<User> pageResult = userService.findAll(pageable);
        return pageResult;
    }
}

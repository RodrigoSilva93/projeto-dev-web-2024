package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.service.UserService;
import br.edu.utfpr.pb.project.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return GenericResponse.builder().message("User saved.").build();
    }
}

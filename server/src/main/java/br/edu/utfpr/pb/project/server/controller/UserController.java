package br.edu.utfpr.pb.project.server.controller;

import br.edu.utfpr.pb.project.server.dto.AddressDto;
import br.edu.utfpr.pb.project.server.dto.UserDto;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import br.edu.utfpr.pb.project.server.service.UserService;
import br.edu.utfpr.pb.project.server.shared.GenericResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("users")

public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    public UserController(ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
            this.modelMapper = modelMapper;
            this.userService = userService;
            this.userRepository = userRepository;
    }

    @PostMapping
    public GenericResponse createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return GenericResponse.builder().message("User saved.").build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();


            User user = userRepository.findByEmail((String) principal);
            if (user != null) {
                UserDto userDto = convertToDto(user);
                return ResponseEntity.ok(userDto);
            }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found or invalid token");
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().intValue());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(null); // Nunca retornar a senha
        userDto.setBirthDate(user.getBirthDate());
        userDto.setGender(user.getGender());
        userDto.setCpf(user.getCpf());
        userDto.setPhone(user.getPhone());
        userDto.setAddresses(
                user.getAddresses().stream()
                        .map(address -> {
                            AddressDto addressDto = new AddressDto();
                            addressDto.setId(address.getId());
                            addressDto.setCep(address.getCep());
                            addressDto.setStreet(address.getStreet());
                            addressDto.setNumber(address.getNumber());
                            addressDto.setComplement(address.getComplement());
                            addressDto.setDistrict(address.getDistrict());
                            addressDto.setCity(address.getCity());
                            addressDto.setState(address.getState());
                            addressDto.setUser(null);
                            return addressDto;
                        })
                        .collect(Collectors.toList())
        );

        return userDto;
    }
}

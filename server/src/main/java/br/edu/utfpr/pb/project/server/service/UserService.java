package br.edu.utfpr.pb.project.server.service;

import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService implements ICrudService<User, Long> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user) {
        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()) );
        return userRepository.save(user);
    }

}
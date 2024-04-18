package br.edu.utfpr.pb.project.server.validation;

import br.edu.utfpr.pb.project.server.annotation.UniqueEmail;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository.findByEmail(s) == null) {
            return true;
        }
        return false;
    }
}

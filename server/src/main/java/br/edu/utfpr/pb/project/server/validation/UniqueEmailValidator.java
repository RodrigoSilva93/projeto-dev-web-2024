package br.edu.utfpr.pb.project.server.validation;

import br.edu.utfpr.pb.project.server.annotation.UniqueEmail;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    private long userId;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.userId = constraintAnnotation.userId();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) return true;

        User user = userRepository.findByEmail(email);

        if (user == null) return true;

        return user.getId().equals(userId);
    }
}

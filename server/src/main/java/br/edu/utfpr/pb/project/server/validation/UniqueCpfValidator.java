package br.edu.utfpr.pb.project.server.validation;

import br.edu.utfpr.pb.project.server.annotation.UniqueCpf;
import br.edu.utfpr.pb.project.server.model.User;
import br.edu.utfpr.pb.project.server.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

    @Autowired
    UserRepository userRepository;

    private long userId;

    @Override
    public void initialize(UniqueCpf constraintAnnotation) {
        this.userId = constraintAnnotation.userId();
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        if (cpf == null || cpf.isEmpty()) return true;

        User user = userRepository.findByCpf(cpf);

        if (user == null) return true;

        return user.getId().equals(userId);
    }
}

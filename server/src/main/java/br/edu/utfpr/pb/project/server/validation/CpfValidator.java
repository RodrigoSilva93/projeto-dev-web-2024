package br.edu.utfpr.pb.project.server.validation;

import br.edu.utfpr.pb.project.server.annotation.Cpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {


    //TODO implementar validação CPF
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

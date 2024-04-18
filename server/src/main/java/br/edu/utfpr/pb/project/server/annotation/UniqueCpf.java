package br.edu.utfpr.pb.project.server.annotation;

import br.edu.utfpr.pb.project.server.validation.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {

    String message() default "Cpf must be 14 characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

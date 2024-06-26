package br.edu.utfpr.pb.project.server.annotation;

import br.edu.utfpr.pb.project.server.validation.UniqueCpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCpfValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCpf {

    String message() default "{br.edu.utfpr.pb.project.server.user.cpf.Unique}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

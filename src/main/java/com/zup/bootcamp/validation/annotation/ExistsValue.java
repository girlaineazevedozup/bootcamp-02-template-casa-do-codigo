package com.zup.bootcamp.validation.annotation;

import com.zup.bootcamp.validation.ExistsValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = ExistsValueValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ExistsValue {

	String message() default "Valor não existe.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName();

	Class<?> domainClass();
}

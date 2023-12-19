package st.sergey.minsky.shop2doordelivers.annotations;

import st.sergey.minsky.shop2doordelivers.validations.PasswordMatchersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchersValidator.class)
@Documented
public @interface PasswordMatchers {
    String message() default "Password mismatch";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

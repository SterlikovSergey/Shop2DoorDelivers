package st.sergey.minsky.shop2doordelivers.validations;

import st.sergey.minsky.shop2doordelivers.annotations.PasswordMatchers;
import st.sergey.minsky.shop2doordelivers.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers, Object> {
    @Override
    public void initialize(PasswordMatchers constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest signupRequest = (SignupRequest) obj;
        return signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
    }
}

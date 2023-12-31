package st.sergey.minsky.shop2doordelivers.validations;

import st.sergey.minsky.shop2doordelivers.annotations.PasswordMatchers;
import st.sergey.minsky.shop2doordelivers.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchersValidator implements ConstraintValidator<PasswordMatchers, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof UserDto userSignupRequest) {
            String password = userSignupRequest.getPassword();
            String confirmPassword = userSignupRequest.getConfirmPassword();

            return password != null && password.equals(confirmPassword);
        }
        return false;
    }
}

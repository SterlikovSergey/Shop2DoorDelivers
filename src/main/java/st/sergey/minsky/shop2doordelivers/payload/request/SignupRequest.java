package st.sergey.minsky.shop2doordelivers.payload.request;

import lombok.Data;
import st.sergey.minsky.shop2doordelivers.annotations.PasswordMatchers;
import st.sergey.minsky.shop2doordelivers.annotations.ValidEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatchers
public class SignupRequest {

    @ValidEmail
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}

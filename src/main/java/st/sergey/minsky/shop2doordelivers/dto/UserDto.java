package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.access.method.P;
import st.sergey.minsky.shop2doordelivers.annotations.PasswordMatchers;
import st.sergey.minsky.shop2doordelivers.annotations.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@PasswordMatchers
public class UserDto {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @ValidEmail
    private String email;

    @NotBlank(message = "Please enter username")
    private String username;

    @NotBlank(message = "Please enter password")
    private String password;
    private String confirmPassword;
}

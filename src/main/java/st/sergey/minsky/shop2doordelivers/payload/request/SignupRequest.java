package st.sergey.minsky.shop2doordelivers.payload.request;

import lombok.*;
import st.sergey.minsky.shop2doordelivers.annotations.PasswordMatchers;
import st.sergey.minsky.shop2doordelivers.annotations.ValidEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/*@PasswordMatchers*/
@Data
public class SignupRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    /*@ValidEmail*/
    private String email;

    @NotEmpty(message = "Please enter your name")
    private String firstname;

    @NotEmpty(message = "Please enter your lastname")
    private String lastname;

    @NotEmpty(message = "Please enter your username")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 3)
    private String password;
    private String confirmPassword;

}

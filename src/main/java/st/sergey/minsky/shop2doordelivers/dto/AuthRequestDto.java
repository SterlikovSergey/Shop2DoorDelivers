package st.sergey.minsky.shop2doordelivers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AuthRequestDto {

    @NotBlank(message = "Please enter username")
    private String username;

    @NotBlank(message = "Please enter password")
    private String password;
}
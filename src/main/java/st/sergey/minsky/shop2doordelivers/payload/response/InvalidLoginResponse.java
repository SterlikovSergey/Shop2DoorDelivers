package st.sergey.minsky.shop2doordelivers.payload.response;

import lombok.Getter;


@Getter
public class InvalidLoginResponse {
    private  String username;
    private  String password;
    public InvalidLoginResponse() {
        this.username = "Invalid username (Email) ";
        this.password = "Invalid password";
    }
}

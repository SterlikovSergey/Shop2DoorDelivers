package st.sergey.minsky.shop2doordelivers.exception.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class ErrorDetails {

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private String timestamp;
    private String message;
    private String errorDetailsMethodName;


    public ErrorDetails(String timestamp, String message, String errorDetailsMethodName) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.errorDetailsMethodName = errorDetailsMethodName;

    }
}

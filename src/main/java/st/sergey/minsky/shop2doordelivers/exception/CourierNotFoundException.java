package st.sergey.minsky.shop2doordelivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourierNotFoundException extends RuntimeException {
    public CourierNotFoundException() {
        super();
    }

    public CourierNotFoundException(String message) {
        super(message);
    }

    public CourierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourierNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CourierNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

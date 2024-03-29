package st.sergey.minsky.shop2doordelivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CourierExistException extends RuntimeException {
    public CourierExistException() {
        super();
    }

    public CourierExistException(String message) {
        super(message);
    }

    public CourierExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CourierExistException(Throwable cause) {
        super(cause);
    }

    protected CourierExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

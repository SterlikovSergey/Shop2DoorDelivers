package st.sergey.minsky.shop2doordelivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StoreExistException extends RuntimeException {
    public StoreExistException() {
        super();
    }

    public StoreExistException(String message) {
        super(message);
    }

    public StoreExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreExistException(Throwable cause) {
        super(cause);
    }

    protected StoreExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

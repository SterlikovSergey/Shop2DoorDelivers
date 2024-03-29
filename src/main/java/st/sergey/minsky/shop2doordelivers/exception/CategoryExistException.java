package st.sergey.minsky.shop2doordelivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryExistException extends RuntimeException {
    public CategoryExistException() {
        super();
    }

    public CategoryExistException(String message) {
        super(message);
    }

    public CategoryExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryExistException(Throwable cause) {
        super(cause);
    }

    protected CategoryExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

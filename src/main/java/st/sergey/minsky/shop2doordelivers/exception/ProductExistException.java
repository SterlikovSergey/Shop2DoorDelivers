package st.sergey.minsky.shop2doordelivers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductExistException extends RuntimeException {
    public ProductExistException() {
        super();
    }

    public ProductExistException(String message) {
        super(message);
    }

    public ProductExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductExistException(Throwable cause) {
        super(cause);
    }

    protected ProductExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

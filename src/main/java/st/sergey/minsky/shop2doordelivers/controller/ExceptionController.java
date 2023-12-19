package st.sergey.minsky.shop2doordelivers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import st.sergey.minsky.shop2doordelivers.exception.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }
}

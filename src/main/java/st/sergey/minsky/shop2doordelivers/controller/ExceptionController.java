package st.sergey.minsky.shop2doordelivers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import st.sergey.minsky.shop2doordelivers.exception.*;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotFoundException(UserNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userExistException(UserExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String categoryNotFoundException(CategoryNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(CourierNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String courierNotFoundException(CourierNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ImageNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String imageNotFoundException(ImageNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String orderNotFoundException(OrderNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String productNotFoundException(ProductNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(StoreNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String storeNotFoundException(StoreNotFoundException e) {
        return e.getMessage();
    }
}

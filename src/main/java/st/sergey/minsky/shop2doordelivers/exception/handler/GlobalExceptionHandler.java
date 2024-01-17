package st.sergey.minsky.shop2doordelivers.exception.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import st.sergey.minsky.shop2doordelivers.exception.*;
import st.sergey.minsky.shop2doordelivers.utils.DataTimeUtil;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
    private final DataTimeUtil dataTimeUtil;
    private static final String ERROR_OCCURRED_IN_METHOD = "Error occurred in method: ";
    private static final String IN_FILE_NAME = " in file name: ";

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Object> handleUserExistException(UserExistException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoreExistException.class)
    public ResponseEntity<Object> handleStoreExistException(StoreExistException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                 + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<Object> handleStoreNotFoundException(StoreNotFoundException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<Object> handleProductExistException(ProductExistException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException e){
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(),errorDetails);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourierExistException.class)
    public ResponseEntity<Object> handleCourierExistException(CourierExistException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourierNotFoundException.class)
    public ResponseEntity<Object> handleCourierNotFoundException(CourierNotFoundException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryExistException.class)
    public ResponseEntity<Object> handleCategoryExistException(CategoryExistException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException e) {
        String errorDetails = ERROR_OCCURRED_IN_METHOD + e.getStackTrace()[0].getMethodName()
                + IN_FILE_NAME + e.getStackTrace()[1].getFileName();
        ErrorDetails response = new ErrorDetails(dataTimeUtil.localDateTimeFormatter(LocalDateTime.now()),
                e.getMessage(), errorDetails);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}

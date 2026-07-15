package seongmin0302.exception.exhandler.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import seongmin0302.exception.exception.UserException;
import seongmin0302.exception.exhandler.ErrorResult;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult IllegalArgumentExceptionHandle(IllegalArgumentException ex) {
        log.error("@Exceptionhandle IllegalAccessException 예외 처리", ex);
        return new ErrorResult("BAD", ex.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> UserExceptionHandleI(UserException ex) {
        log.error("@Exceptionhandle UserException 예외 처리", ex);
        ErrorResult errorResult = new ErrorResult("USER-EX", ex.getMessage());
        return new ResponseEntity<ErrorResult>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exceptionHandler(Exception ex) {
        log.error("@Exceptionhandle Exception 예외 처리", ex);
        return new ErrorResult("EX", ex.getMessage());
    }
}

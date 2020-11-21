package br.com.projetointegrador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ResponseException> genericException(HttpServletRequest request, GenericException exception) {
        return new ResponseEntity<>(new ResponseException(request, exception), exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseException> genericException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ResponseException(request, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseException> genericException(HttpServletRequest request, ConstraintViolationException exception) {
        return new ResponseEntity<>(new ResponseException(request, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseException> genericException(HttpServletRequest request, MissingServletRequestParameterException exception) {
        return new ResponseEntity<>(new ResponseException(request, exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> genericException(HttpServletRequest request) {
        return new ResponseEntity<>(new ResponseException(request), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package br.com.projetointegrador.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ResponseException {
    public final String path;
    public final List<?> errors;

    public ResponseException(HttpServletRequest path) {
        this.path = path.getRequestURI();
        this.errors = Collections.singletonList("Erro não tratado.");
    }

    public ResponseException(HttpServletRequest path, GenericException ex) {
        this.path = path.getRequestURI();
        this.errors = Collections.singletonList(ex.getMensagem());
    }

    public ResponseException(HttpServletRequest path, MethodArgumentNotValidException exception) {
        this.path = path.getRequestURI();
        this.errors = trataMensagem(exception.getBindingResult().getAllErrors());
    }

    public ResponseException(HttpServletRequest path, ConstraintViolationException exception) {
        this.path = path.getRequestURI();
        this.errors = trataMensagem(exception.getConstraintViolations());
    }

    public ResponseException(HttpServletRequest path, MissingServletRequestParameterException exception) {
        this.path = path.getRequestURI();
        this.errors = Collections.singletonList(exception.getMessage());
    }

    private List<String> trataMensagem(List<ObjectError> errors){
        return errors.stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
    }

    private List<String> trataMensagem(Set<ConstraintViolation<?>> errors){
        return errors.stream().map(x -> x.getMessage()).collect(Collectors.toList());
    }
}
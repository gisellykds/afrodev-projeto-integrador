package br.com.projetointegrador.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class GenericException extends ResponseStatusException {

    private String mensagem;

    public GenericException(HttpStatus status, String mensagem) {
        super(status);
        this.mensagem = mensagem;
    }
}

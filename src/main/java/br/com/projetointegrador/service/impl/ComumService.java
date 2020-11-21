package br.com.projetointegrador.service.impl;

import br.com.projetointegrador.exception.GenericException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class ComumService {

    public static void verificaIsEmpty(List<?> lista, String mensagem){
        if (Objects.isNull(lista) || lista.isEmpty())
            throw new GenericException(HttpStatus.BAD_REQUEST, mensagem);
    }

    public static void verificaIsOptional(Optional<?> objeto, String mensagem){
        if (!objeto.isPresent()){
            throw new GenericException(HttpStatus.BAD_REQUEST, mensagem);
        }
    }

    public static void verificaIsNotOptional(Optional<?> objeto, String mensagem){
        if (objeto.isPresent()){
            throw new GenericException(HttpStatus.BAD_REQUEST, mensagem);
        }
    }

}

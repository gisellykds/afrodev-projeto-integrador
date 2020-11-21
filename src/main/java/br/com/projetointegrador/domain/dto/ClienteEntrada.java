package br.com.projetointegrador.domain.dto;

import br.com.projetointegrador.domain.enums.StatusCadastro;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ClienteEntrada {

    @CPF(message = "cpf inválido")
    @NotNull(message = "cpf não pode estar em branco")
    @JsonProperty("cpf")
    private String cpf;

    @NotBlank(message = "nome_completo não pode estar em branco")
    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonIgnore
    private StatusCadastro status;

    @JsonIgnore
    private LocalDateTime dataCadastro;

    public ClienteEntrada(){
        this.status = StatusCadastro.ATIVO;
        this.dataCadastro = LocalDateTime.now();
    }
}

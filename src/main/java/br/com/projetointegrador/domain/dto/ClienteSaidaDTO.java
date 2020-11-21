package br.com.projetointegrador.domain.dto;

import br.com.projetointegrador.domain.enums.StatusCadastro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClienteSaidaDTO {

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonProperty("status")
    private StatusCadastro status;

    @JsonProperty("data_cadastro")
    private LocalDateTime dataCadastro;

}

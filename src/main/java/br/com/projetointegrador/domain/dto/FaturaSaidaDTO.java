package br.com.projetointegrador.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FaturaSaidaDTO {

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("fatura")
    private FaturaItemSaidaDTO faturaItemSaida;

}

package br.com.projetointegrador.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Getter
public class FaturaEntrada {
    @NotNull(message = "data não pode estar em branco")
    @JsonProperty("data")
    private YearMonth anoMes;

    @NotNull(message = "leitura não pode estar em branco")
    @JsonProperty("leitura")
    private Long leitura;
}

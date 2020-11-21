package br.com.projetointegrador.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
public class FaturaItemSaidaDTO {

    @JsonProperty("codigo")
    private Long codigo;

    @JsonProperty("data")
    private YearMonth anoMes;

    @JsonProperty("leitura_inicial")
    private Long leituraAnterior;

    @JsonProperty("leitura_atual")
    private Long leituraAtual;

    @JsonProperty("consumo")
    private Long consumo;
}

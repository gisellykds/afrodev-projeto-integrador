package br.com.projetointegrador.domain.mapper;

import br.com.projetointegrador.domain.dto.FaturaEntrada;
import br.com.projetointegrador.domain.dto.FaturaItemSaidaDTO;
import br.com.projetointegrador.domain.dto.FaturaSaidaDTO;
import br.com.projetointegrador.domain.entity.FaturaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FaturaMapper {

    public static FaturaSaidaDTO mapToSaida(FaturaEntity faturaEntity, Long leituraAnterior){
        FaturaItemSaidaDTO fatura = new FaturaItemSaidaDTO();

        fatura.setAnoMes(faturaEntity.getAnoMes());
        fatura.setLeituraAtual(faturaEntity.getLeitura());
        fatura.setLeituraAnterior(leituraAnterior);
        fatura.setConsumo(calculaConsumoPeriodo(leituraAnterior, faturaEntity.getLeitura()));
        fatura.setCodigo(faturaEntity.getId());
        return new FaturaSaidaDTO(faturaEntity.getCpf(), fatura);
    }

    public static FaturaEntity mapToEntity(FaturaEntrada entrada, String cpf){
        FaturaEntity faturaEntity = new FaturaEntity();

        faturaEntity.setAnoMes(entrada.getAnoMes());
        faturaEntity.setLeitura(entrada.getLeitura());
        faturaEntity.setCpf(cpf);

        return faturaEntity;
    }

    public static FaturaEntity mapToUpdate(FaturaEntity faturaEntity, Long leituraAtual) {
        faturaEntity.setLeitura(leituraAtual);
        return faturaEntity;
    }

    private static Long calculaConsumoPeriodo(Long leituraAnterior, Long leituraAtual){
        return leituraAnterior == 0 ? leituraAtual : (leituraAtual - leituraAnterior);
    }
}

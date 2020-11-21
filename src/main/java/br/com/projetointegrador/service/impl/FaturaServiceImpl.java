package br.com.projetointegrador.service.impl;

import br.com.projetointegrador.domain.dto.FaturaEntrada;
import br.com.projetointegrador.domain.dto.FaturaSaidaDTO;
import br.com.projetointegrador.domain.entity.FaturaEntity;
import br.com.projetointegrador.domain.mapper.FaturaMapper;
import br.com.projetointegrador.exception.GenericException;
import br.com.projetointegrador.repository.FaturaRepository;
import br.com.projetointegrador.service.FaturaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.projetointegrador.service.impl.ComumService.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FaturaServiceImpl implements FaturaService {

    private FaturaRepository faturaRepository;

    @Override
    public FaturaSaidaDTO salvar(FaturaEntrada entrada, String cpf) {
        validaFaturaEntrada(entrada, cpf);
        FaturaEntity entity = FaturaMapper.mapToEntity(entrada, cpf);
        entity = faturaRepository.save(entity);
        return FaturaMapper.mapToSaida(entity, buscaLeituraFaturaAnterior(entity.getAnoMes(), cpf));
    }

    @Override
    public List<FaturaSaidaDTO> buscaFaturasPorCpf(String cpf) {
        List<FaturaEntity> faturaEntities = faturaRepository.findByCpf(cpf);
        verificaIsEmpty(faturaEntities, "Nenhuma fatura encontrada para o cpf informado.");
        return faturaEntities.stream().map(x -> FaturaMapper.mapToSaida(x, buscaLeituraFaturaAnterior(x.getAnoMes(), x.getCpf()))).collect(Collectors.toList());
    }

    @Override
    public FaturaSaidaDTO buscaFaturaAtualPorCpf(String cpf, YearMonth anoMes) {
        anoMes = Objects.isNull(anoMes) ? YearMonth.now() : anoMes;
        Optional<FaturaEntity> entity = faturaRepository.findByCpfAndAnoMes(cpf, anoMes);
        verificaIsOptional(entity, String.format("Fatura do mês %s não cadastrada para o cliente informado.", anoMes));
        return FaturaMapper.mapToSaida(entity.get(), buscaLeituraFaturaAnterior(entity.get().getAnoMes(), cpf));
    }

    @Override
    public void atualizaLeituraAtual(String cpf, Long leituraAtual) {
        Optional<FaturaEntity> faturaOptional = faturaRepository.findByCpfAndAnoMes(cpf, YearMonth.now());
        verificaIsOptional(faturaOptional, "Fatura do mês atual não cadastrada para o cliente informado.");
        FaturaEntity entity = FaturaMapper.mapToUpdate(faturaOptional.get(), leituraAtual);
        faturaRepository.save(entity);
    }

    @Override
    public void deletaPorCpfAnoMes(String cpf, YearMonth anoMes) {
        buscaFaturaAtualPorCpf(cpf, anoMes);
        faturaRepository.deleteByCpfAndAnoMes(cpf, anoMes);
    }

    private void verificaFaturaJaCadastrada(YearMonth anoMes, String cpf) {
        Optional<FaturaEntity> faturaEntity = faturaRepository.findByCpfAndAnoMes(cpf, anoMes);
        verificaIsNotOptional(faturaEntity, "Fatura do mês informado já se encontra cadastrada.");
    }

    private Long buscaLeituraFaturaAnterior(YearMonth anoMes, String cpf) {
        Optional<FaturaEntity> faturaOptional = faturaRepository.findByCpfAndAnoMes(cpf, anoMes.minusMonths(1));
        return faturaOptional.orElse(new FaturaEntity()).getLeitura();
    }

    private void verificaDataEntreRangePermitido(YearMonth anoMes) {
        YearMonth atual = YearMonth.now();
        YearMonth proximo = atual.plusMonths(1);
        if (!atual.equals(anoMes) && !proximo.equals(anoMes)) {
            throw new GenericException(HttpStatus.BAD_REQUEST, String.format("Data da fatura deve estar entre: %s e %s", atual, proximo));
        }
    }

    private void verificaLeituraEntreRangePermitido(YearMonth anoMes, String cpf, Long leitura) {
        Long leituraPassada = buscaLeituraFaturaAnterior(anoMes, cpf);
        if (leitura < leituraPassada) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Leitura da fatura deve ser maior que a última leitura. Detalhes: ultima_leitura igual a " + leituraPassada);
        }
    }

    private void validaFaturaEntrada(FaturaEntrada faturaEntrada, String cpf) {
        YearMonth anoMes = faturaEntrada.getAnoMes();
        verificaDataEntreRangePermitido(anoMes);
        verificaFaturaJaCadastrada(anoMes, cpf);
        verificaLeituraEntreRangePermitido(anoMes, cpf, faturaEntrada.getLeitura());
    }

}

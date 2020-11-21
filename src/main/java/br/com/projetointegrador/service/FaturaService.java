package br.com.projetointegrador.service;

import br.com.projetointegrador.domain.dto.FaturaEntrada;
import br.com.projetointegrador.domain.dto.FaturaSaidaDTO;

import java.time.YearMonth;
import java.util.List;

public interface FaturaService {

    FaturaSaidaDTO salvar(FaturaEntrada entrada, String cpf) throws Exception;

    List<FaturaSaidaDTO> buscaFaturasPorCpf(String cpf) throws Exception;

    FaturaSaidaDTO buscaFaturaAtualPorCpf(String cpf, YearMonth anoMes) throws Exception;

    void atualizaLeituraAtual(String cpf, Long leituraAtual) throws Exception;

    void deletaPorCpfAnoMes(String cpf, YearMonth anoMes);
}

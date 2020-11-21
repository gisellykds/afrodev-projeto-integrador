package br.com.projetointegrador.controller;

import br.com.projetointegrador.domain.dto.FaturaEntrada;
import br.com.projetointegrador.domain.dto.FaturaSaidaDTO;
import br.com.projetointegrador.service.ClienteService;
import br.com.projetointegrador.service.FaturaService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("fatura/v1")
@Validated
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FaturaController {

    private FaturaService faturaService;
    private ClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id_cliente}/salva")
    public FaturaSaidaDTO salvar(@RequestBody @Valid FaturaEntrada entrada,
                                 @PathVariable("id_cliente") @CPF String cpf) throws Exception {
        clienteService.verificaExistenciaCPFStatusAtivo(cpf);
        return faturaService.salvar(entrada, cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id_cliente}/atualiza-leitura-atual")
    public void atualiza(@PathVariable("id_cliente") @CPF String cpf,
                         @RequestParam("leitura_atual") @NotNull(message = "leitura_atual não pode estar em branco")
                         Long leituraAtual) throws Exception {
        clienteService.verificaExistenciaCPFStatusAtivo(cpf);
        faturaService.atualizaLeituraAtual(cpf, leituraAtual);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id_cliente}/todas")
    public List<FaturaSaidaDTO> buscaTodas(@PathVariable("id_cliente") @CPF String cpf) throws Exception {
        clienteService.verificaExistenciaCPF(cpf);
        return faturaService.buscaFaturasPorCpf(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id_cliente}/especifica")
    public FaturaSaidaDTO buscaAtual(@PathVariable("id_cliente") @CPF String cpf,
                                     @RequestParam(value = "data", required = false) YearMonth anoMes) throws Exception {
        clienteService.verificaExistenciaCPF(cpf);
        return faturaService.buscaFaturaAtualPorCpf(cpf, anoMes);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id_cliente}/especifica-deleta")
    public void deleta(@PathVariable("id_cliente") @CPF String cpf,
                                 @RequestParam(value = "data") @NotNull(message = "data não pode estar em branco") YearMonth anoMes) throws Exception {
        clienteService.verificaExistenciaCPF(cpf);
        faturaService.deletaPorCpfAnoMes(cpf, anoMes);
    }
}

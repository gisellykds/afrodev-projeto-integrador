package br.com.projetointegrador.controller;

import br.com.projetointegrador.domain.dto.ClienteEntrada;
import br.com.projetointegrador.domain.dto.ClienteSaidaDTO;
import br.com.projetointegrador.domain.enums.StatusCadastro;
import br.com.projetointegrador.service.ClienteService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cliente/v1")
@Validated
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteController {

    private ClienteService clienteService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/salva")
    public ClienteSaidaDTO salvar(@RequestBody @Valid ClienteEntrada entrada){
        return clienteService.salvar(entrada);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id_cliente}")
    public ClienteSaidaDTO buscarEspecifico(@PathVariable("id_cliente") @CPF String cpf){
        return clienteService.buscarEspecifico(cpf);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/todos")
    public List<ClienteSaidaDTO> buscarTodos(@RequestParam(value = "status", required = false, defaultValue = "DEFAULT") StatusCadastro statusCadastro){
        return clienteService.buscarTodos(statusCadastro);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id_cliente}/inativa")
    public void inativaEspecifico(@PathVariable("id_cliente") @CPF String cpf){
        clienteService.inativar(cpf);
    }
}

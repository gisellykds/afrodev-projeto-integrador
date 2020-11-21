package br.com.projetointegrador.service;

import br.com.projetointegrador.domain.dto.ClienteEntrada;
import br.com.projetointegrador.domain.dto.ClienteSaidaDTO;
import br.com.projetointegrador.domain.entity.ClienteEntity;
import br.com.projetointegrador.domain.enums.StatusCadastro;

import java.util.List;

public interface ClienteService {

    ClienteEntity verificaExistenciaCPF(String cpf) throws Exception;

    void verificaExistenciaCPFStatusAtivo(String cpf);

    ClienteSaidaDTO salvar(ClienteEntrada entrada);

    List<ClienteSaidaDTO> buscarTodos(StatusCadastro statusCadastro);

    ClienteSaidaDTO buscarEspecifico(String cpf);

    void inativar(String cpf);
}

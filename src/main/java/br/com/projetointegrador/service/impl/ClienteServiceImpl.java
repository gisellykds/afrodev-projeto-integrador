package br.com.projetointegrador.service.impl;

import br.com.projetointegrador.domain.dto.ClienteEntrada;
import br.com.projetointegrador.domain.dto.ClienteSaidaDTO;
import br.com.projetointegrador.domain.entity.ClienteEntity;
import br.com.projetointegrador.domain.enums.StatusCadastro;
import br.com.projetointegrador.domain.mapper.ClienteMapper;
import br.com.projetointegrador.exception.GenericException;
import br.com.projetointegrador.repository.ClienteRepository;
import br.com.projetointegrador.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.projetointegrador.service.impl.ComumService.verificaIsEmpty;
import static br.com.projetointegrador.service.impl.ComumService.verificaIsNotOptional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteEntity verificaExistenciaCPF(String cpf){
        Optional<ClienteEntity> clienteOptional = clienteRepository.findByCpf(cpf);
        if (!clienteOptional.isPresent())
            throw new GenericException(HttpStatus.BAD_REQUEST, "Cliente não localizado com CPF informado");
        return clienteOptional.get();
    }

    public void verificaExistenciaCPFStatusAtivo(String cpf){
        ClienteEntity entity = verificaExistenciaCPF(cpf);
        if (StatusCadastro.INATIVO.equals(entity.getStatus())){
            throw new GenericException(HttpStatus.BAD_REQUEST, "Cliente com CPF informado se encontra INATIVO.");
        }
    }

    @Override
    public ClienteSaidaDTO salvar(ClienteEntrada entrada) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findByCpf(entrada.getCpf());
        verificaIsNotOptional(clienteOptional, "Já existe um cadastro com o CPF informado.");
        ClienteEntity entity = ClienteMapper.mapToEntity(entrada);
        return ClienteMapper.mapToSaida(clienteRepository.save(entity));
    }

    @Override
    public List<ClienteSaidaDTO> buscarTodos(StatusCadastro statusCadastro) {
        List<ClienteEntity> clienteEntities = statusCadastro.equals(StatusCadastro.DEFAULT) ? clienteRepository.findAll() : clienteRepository.findByStatus(statusCadastro);
        verificaIsEmpty(clienteEntities, "Nenhum cadastro encontrado.");
        return clienteEntities.stream().map(ClienteMapper::mapToSaida).collect(Collectors.toList());
    }

    @Override
    public ClienteSaidaDTO buscarEspecifico(String cpf) {
        ClienteEntity entity = verificaExistenciaCPF(cpf);
        return ClienteMapper.mapToSaida(entity);
    }

    @Override
    public void inativar(String cpf) {
        verificaExistenciaCPFStatusAtivo(cpf);
        clienteRepository.updateStatusCadastro(cpf);
    }
}

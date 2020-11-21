package br.com.projetointegrador.domain.mapper;

import br.com.projetointegrador.domain.dto.ClienteEntrada;
import br.com.projetointegrador.domain.dto.ClienteSaidaDTO;
import br.com.projetointegrador.domain.entity.ClienteEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {
    public static ClienteSaidaDTO mapToSaida(ClienteEntity clienteEntity){
        ClienteSaidaDTO saida = new ClienteSaidaDTO();

        saida.setCpf(clienteEntity.getCpf());
        saida.setDataCadastro(clienteEntity.getDataCadastro());
        saida.setNomeCompleto(clienteEntity.getNome());
        saida.setStatus(clienteEntity.getStatus());

        return saida;
    }

    public static ClienteEntity mapToEntity(ClienteEntrada entrada){
        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setCpf(entrada.getCpf());
        clienteEntity.setDataCadastro(entrada.getDataCadastro());
        clienteEntity.setStatus(entrada.getStatus());
        clienteEntity.setNome(entrada.getNomeCompleto());

        return clienteEntity;
    }

}

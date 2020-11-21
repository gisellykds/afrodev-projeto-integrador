package br.com.projetointegrador.repository;

import br.com.projetointegrador.domain.entity.ClienteEntity;
import br.com.projetointegrador.domain.enums.StatusCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);

    @Modifying
    @Transactional
    @Query("UPDATE ClienteEntity AS c SET c.status = '1' WHERE c.cpf = :cpf")
    void updateStatusCadastro(String cpf);

    List<ClienteEntity> findByStatus(StatusCadastro statusCadastro);

}

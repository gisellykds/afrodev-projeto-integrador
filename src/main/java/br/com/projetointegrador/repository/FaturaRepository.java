package br.com.projetointegrador.repository;

import br.com.projetointegrador.domain.entity.FaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<FaturaEntity, Long> {

    List<FaturaEntity> findByCpf(String cpf);

    Optional<FaturaEntity> findByCpfAndAnoMes(String cpf, YearMonth now);

    @Modifying
    @Transactional
    void deleteByCpfAndAnoMes(String cpf, YearMonth anoMes);

}

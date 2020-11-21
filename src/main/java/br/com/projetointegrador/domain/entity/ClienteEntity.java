package br.com.projetointegrador.domain.entity;

import br.com.projetointegrador.domain.enums.StatusCadastro;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cliente_tb")
@Getter
@Setter
public class ClienteEntity {
    @Id
    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private StatusCadastro status;

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "cpf")
    private List<FaturaEntity> faturaEntities;

}

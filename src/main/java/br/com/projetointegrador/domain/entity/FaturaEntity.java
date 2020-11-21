package br.com.projetointegrador.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.YearMonth;

@Entity
@Table(name = "fatura_tb")
@Getter @Setter
public class FaturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private YearMonth anoMes;

    @Column(nullable = false)
    private Long leitura = 0L; //quando criar uma leitura para o mes pegar o valor da ultima leitura

}

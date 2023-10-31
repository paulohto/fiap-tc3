package com.tc3.parquimetro.dominio.condutor.entidade;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_condutor")
public class Condutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String cpf;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Condutor(Long id, String cpf, String nome, Endereco endereco) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Condutor() {
    }
}

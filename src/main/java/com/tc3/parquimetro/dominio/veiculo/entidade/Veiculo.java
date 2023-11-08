package com.tc3.parquimetro.dominio.veiculo.entidade;

import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    @ManyToOne
    @JoinColumn(name = "condutor_id")
    private Condutor condutor;
}

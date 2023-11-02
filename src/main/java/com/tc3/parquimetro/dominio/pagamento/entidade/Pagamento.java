package com.tc3.parquimetro.dominio.pagamento.entidade;

import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    private String placa;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "condutor_id")
    private Condutor condutor;

    private String formaDePagamento;

}

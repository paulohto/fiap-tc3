package com.tc3.parquimetro.dominio.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.pagamento.entidade.Pagamento;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PagamentoDto {


    @JsonProperty
    @NotNull(message = "A placa do Veiculo não pode  ser nulo ou vazia.")
    @NotBlank
    private String placa;

    private String formaDePagamento;

    private Veiculo veiculo;

    private Condutor condutor;

    public PagamentoDto() {
    }

    public PagamentoDto(String placa, String formaDePagamento, Veiculo veiculo, Condutor condutor) {
        this.placa = placa;
        this.formaDePagamento = formaDePagamento;
        this.veiculo = veiculo;
        this.condutor = condutor;
    }

    public PagamentoDto(Pagamento pagamento) {
        this.veiculo = pagamento.getVeiculo();
        this.formaDePagamento = pagamento.getFormaDePagamento();
        this.condutor = pagamento.getCondutor();
    }
}

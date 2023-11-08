package com.tc3.parquimetro.dominio.veiculo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VeiculoDto {


    @JsonProperty
    @NotNull(message = "A Placa do Veiculo não pode  ser nulo ou vazia")
    @NotBlank
    private String placa;

    private Condutor condutor;

    public VeiculoDto(String placa, Condutor condutor) {
        this.placa = placa;
        this.condutor = condutor;
    }

    public VeiculoDto(Veiculo veiculo) {
        this.placa = veiculo.getPlaca();
        this.condutor = veiculo.getCondutor();
    }

}

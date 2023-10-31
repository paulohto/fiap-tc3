package com.tc3.parquimetro.dominio.condutor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.condutor.entidade.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CondutorDto {


    @JsonProperty
    @NotNull(message = "O CPF do Condutor não pode  ser nulo ou vazio")
    @NotBlank
    private String cpf;

    private String nome;

    private Endereco endereco;

    public CondutorDto(String cpf, Endereco endereco) {
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public CondutorDto(Condutor condutor) {
        this.cpf = condutor.getCpf();
        this.endereco = condutor.getEndereco();
    }

}

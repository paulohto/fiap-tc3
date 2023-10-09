package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TempoDto {

    private Long id;
    @JsonProperty
    private LocalDateTime inicio;
    @JsonProperty
    private LocalDateTime fim;

    public TempoDto(){}
    public TempoDto(Long id, LocalDateTime inicio, LocalDateTime fim){
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
    }

    public TempoDto(Tempo entidade){
        this.id = entidade.getId();
        this.inicio = entidade.getInicio();
        this.fim = entidade.getFim();

    }

}

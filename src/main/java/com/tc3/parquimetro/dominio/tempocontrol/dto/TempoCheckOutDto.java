package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoCheckOutDto {

    private Long id;
    @JsonProperty
    @NotNull(message = "O Tipo do Tempo não pode  ser nulo, declare: fixo ou variavel")
    private TipoTempo tipoTempo; // FIXO ou VARIAVEL
    @JsonProperty
    private LocalDateTime inicio;
    @JsonProperty
    private LocalDateTime fim;
    @JsonProperty
    private Double bill;

    public TempoCheckOutDto(Tempo entidade){
        this.id = entidade.getId();
        this.tipoTempo = entidade.getTipoTempo();
        this.inicio = entidade.getInicio();
        this.fim = entidade.getFim();
        this.bill = entidade.getBill();
    }
}

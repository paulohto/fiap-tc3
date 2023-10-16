package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoDto {

    private Long id;
    @JsonProperty
    private Tempo.TipoTempo tipoTempo; // Seleciona o tipo de Tempo se FIXO ou VARIAVEL
    @JsonProperty
    private LocalDateTime inicio;
    @JsonProperty
    private LocalDateTime fim;
    @JsonProperty
    private int tempoContratado;

    public enum TipoTempo {
        fixo,
        variavel
    }

    public TempoDto(int tempoContratado){
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();
        this.tempoContratado = tempoContratado;
        this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);
    }

    public TempoDto(Long id, int tempoContratado, Tempo.TipoTempo tipoTempo){
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();


        if (tipoTempo == Tempo.TipoTempo.fixo) {
            this.tempoContratado = tempoContratado;
            this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);
        } else if (tipoTempo == Tempo.TipoTempo.variavel) {
            // Lógica para tempo variável (caso necessário).
        }
    }

    public TempoDto(Tempo entidade){
        this.id = entidade.getId();
        this.tipoTempo = entidade.getTipoTempo();
        this.inicio = entidade.getInicio();
        this.fim = entidade.getFim();
        this.tempoContratado = entidade.getTempoContratado();
    }


}

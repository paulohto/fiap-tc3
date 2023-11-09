package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoDto {

    private Long id;
    @JsonProperty
    private TipoTempo tipoTempo; // Seleciona o tipo de Tempo se FIXO ou VARIAVEL
    @JsonProperty
    private LocalDateTime inicio;
    @JsonProperty
    private LocalDateTime fim;
    @JsonProperty
    private int tempoContratado;

    @JsonProperty
    private LocalDateTime fimAdd; // CAMPO PRA ADICIONAR TOTAL DE TEMPO: CONTRATADO FIXO + ADICIONAIS

    public TempoDto(Long id, int tempoContratado, TipoTempo tipoTempo){
        this.id = id;
        this.tipoTempo = tipoTempo;

        if ( tipoTempo == TipoTempo.FIXO ) {
            this.inicio = LocalDateTime.now();
            this.tempoContratado = tempoContratado;
              this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS); // OK

        } else if (tipoTempo == TipoTempo.VARIAVEL) {
            this.inicio = LocalDateTime.now();
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

package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoDto {

    private Long id;
    @JsonProperty
    @NotNull(message = "O Tipo do Tempo não pode  ser nulo, declare: fixo ou variavel")
    //@NotEmpty (message = "O Tipo do Tempo não pode  estar em branco, declare: fixo ou variavel")
    private TipoTempo tipoTempo; // Seleciona o tipo de Tempo se FIXO ou VARIAVEL
    @JsonProperty
    private LocalDateTime inicio;
    @JsonProperty
    private LocalDateTime fim;
    @JsonProperty
    private int tempoContratado;
    @JsonProperty
    private int tempoAdicional;


    public TempoDto(int tempoContratado){
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();
        this.tempoContratado = tempoContratado;
        this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);
        this.tempoAdicional = tempoAdicional;
    }

    public TempoDto(Long id, int tempoContratado, TipoTempo tipoTempo, int tempoAdicional){
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();

        if ( tipoTempo == TipoTempo.FIXO ) {
            this.tempoContratado = tempoContratado;
            this.tempoAdicional = tempoAdicional;
            this.fim = inicio.plus(tempoContratado + tempoAdicional, ChronoUnit.HOURS);
        } else if (tipoTempo == TipoTempo.VARIAVEL) {
            // Lógica para tempo variável (caso necessário)
        }
    }

    public TempoDto(Tempo entidade){
        this.id = entidade.getId();
        this.tipoTempo = entidade.getTipoTempo();
        this.inicio = entidade.getInicio();
        this.fim = entidade.getFim();
        this.tempoContratado = entidade.getTempoContratado();
        this.tempoAdicional = entidade.getTempoAdicional();
    }
}

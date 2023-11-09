package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoAddTempoDto {
    private Long id;
    @JsonProperty
    private LocalDateTime novoInicio;
    @JsonProperty
    private LocalDateTime novoFim;
    @JsonProperty
    private int tempoAdicional;
    private TempoDto tempo;

    public TempoAddTempoDto(){}

    public TempoAddTempoDto(Long id, LocalDateTime novoInicio, LocalDateTime novoFim, int tempoAdicional, TempoDto tempo) {
        this.id = id;
        this.novoInicio = tempo.getFim();
        this.novoFim = novoInicio.plus(tempoAdicional, ChronoUnit.HOURS);
        this.tempoAdicional = tempoAdicional;
        this.tempo = tempo;
    }

    public static TempoAdd paraEntidade(TempoAddTempoDto dto, Tempo tempo){
        return new TempoAdd(dto, tempo);
    }

    public static TempoAddTempoDto daEntidade(TempoAdd tempoAdd){
        return new TempoAddTempoDto(
                tempoAdd.getId(),
                tempoAdd.getNovoInicio(),
                tempoAdd.getNovoFim(),
                tempoAdd.getTempoAdicional(),
                tempoAdd.getTempo() != null ? new TempoDto(tempoAdd.getTempo()) : null
        );
    }

    public static TempoAdd mapperDtoParaEntidade(
            TempoAddTempoDto dto,
            TempoAdd tempoAdd,
            Tempo tempo
    ) {
        tempoAdd.setTempoAdicional(dto.getTempoAdicional());
        tempoAdd.setNovoInicio(tempo.getFim()); // TESTE
        tempoAdd.setNovoFim(dto.getNovoFim());
        tempoAdd.setTempo(tempo);
        return tempoAdd;
    }

}

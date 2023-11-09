package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter @Setter
public class TempoAddDto {
    private Long id;
    @JsonProperty
    private LocalDateTime novoInicio;
    @JsonProperty
    private LocalDateTime novoFim;
    @JsonProperty
    private int tempoAdicional;

    private static TempoDto tempo;

    public TempoAddDto(){}

    public TempoAddDto(Long id, /*LocalDateTime novoInicio, LocalDateTime novoFim,*/ int tempoAdicional) {
        this.id = id;
        this.tempoAdicional = tempoAdicional;
        this.novoInicio = tempo.getFim();
        this.novoFim = novoInicio.plus(tempoAdicional, ChronoUnit.HOURS);
    }
    public TempoAddDto(TempoAdd entidade){
        this.id = entidade.getId();
        this.tempoAdicional = entidade.getTempoAdicional();
        this.novoInicio = entidade.getNovoInicio();
        this.novoFim = entidade.getNovoFim();
    }

    public  static  TempoAddDto deEntidade(TempoAdd tempoAdd) {
        return new TempoAddDto(
                tempoAdd.getId(),
                tempoAdd.getTempoAdicional()
        );
    }

    public static TempoAdd paraEntidade(TempoAddDto tempoAddDto) {
        return  new TempoAdd(
                tempoAddDto.novoInicio,
                tempoAddDto.novoFim,
                tempoAddDto.tempoAdicional
        );
    }

    public static  TempoAdd mapperDtoParaEntidade(
            TempoAddDto tempoAddDto,
            TempoAdd tempoAdd
    ) {
        tempoAdd.setNovoInicio(tempoAddDto.novoInicio);
        tempoAdd.setNovoFim(tempoAddDto.novoFim);
        tempoAdd.setTempoAdicional(tempoAddDto.tempoAdicional);
        return tempoAdd;
    }

    public void add(TempoAddDto tempoAddDto) {
    }
}

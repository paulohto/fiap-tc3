package com.tc3.parquimetro.dominio.tempocontrol.dto;

import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TempoTempoAddDto {

    private Long id;

    private TipoTempo tipoTempo; // Seleciona o tipo de Tempo se FIXO ou VARIAVEL
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int tempoContratado;
    private List<TempoAddDto> tempoAdd;

    public TempoTempoAddDto() {
    }

    public TempoTempoAddDto(Long id, TipoTempo tipoTempo, LocalDateTime inicio, LocalDateTime fim, int tempoContratado, List<TempoAddDto> tempoAdd) {
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = inicio;
        this.fim = fim;
        this.tempoContratado = tempoContratado;
        this.tempoAdd = tempoAdd;
    }

    public static TempoTempoAddDto daEntidade(Tempo tempo) {
        TempoTempoAddDto tempoDto = new TempoTempoAddDto();
        tempoDto.setId(tempo.getId());
        tempoDto.setTipoTempo(tempo.getTipoTempo());
        tempoDto.setInicio(tempo.getInicio());
        tempoDto.setFim(tempo.getFim());
        tempoDto.setTempoContratado(tempo.getTempoContratado());

        List<TempoAddDto> tempoAddDtoList = new ArrayList<>();
        for (TempoAdd tempoAdd : tempo.getTempoAdd()) {
            tempoAddDtoList.add(new TempoAddDto(tempoAdd));
        }

        tempoDto.setTempoAdd(tempoAddDtoList);
        return tempoDto;
    }


}

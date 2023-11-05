package com.tc3.parquimetro.dominio.tempocontrol.entidade;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "tb_tempoadd")
public class TempoAdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime novoInicio; // DEVE SER IGUAL AO FIM DA CLASS TEMPODTO

    private LocalDateTime novoFim;
    private int tempoAdicional;

    @ManyToOne
    @JoinColumn(name = "tempo_id")
    private Tempo tempo;

    public TempoAdd(){}

    public TempoAdd(LocalDateTime novoInicio, LocalDateTime novoFim, int tempoAdicional) {
        this.tempoAdicional = tempoAdicional;
        //this.novoInicio = novoInicio;
        this.novoInicio = tempo.getFim();
        this.novoFim = novoFim;
    }

    public TempoAdd(TempoAddDto dto){
        this.id = dto.getId();
        this.tempoAdicional = dto.getTempoAdicional();
        this.novoInicio = dto.getNovoInicio();
        this.novoFim = dto.getNovoFim();
    }

    public TempoAdd(TempoAddTempoDto dto, Tempo tempo){
        this.id = dto.getId();
        this.tempoAdicional = dto.getTempoAdicional();
        this.novoInicio = dto.getNovoInicio();
        this.novoFim = dto.getNovoFim();
        this.tempo = tempo;
    }
}

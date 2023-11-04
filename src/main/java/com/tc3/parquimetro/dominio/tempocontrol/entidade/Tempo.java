package com.tc3.parquimetro.dominio.tempocontrol.entidade;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "tb_tempo")
public class Tempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoTempo tipoTempo; // Seleciona o tipo de Tempo se FIXO ou VARIAVEL
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private int tempoContratado; // Horas contratadas no modelo Tempo Fixo
    //private int tempoAdicional;
    //private LocalDateTime fimAdd;

    //@OneToMany
    //@JoinColumn(name = "tempoadd_id")
    //private List<TempoAdd> tempoAdd;
    @OneToMany(mappedBy = "tempo")
    private List<TempoAdd> tempoAdd;

    public Tempo(){}
    public Tempo(Long id, int tempoContratado, TipoTempo tipoTempo){
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();
        this.tempoContratado = tempoContratado;
        this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);

        //this.tempoContratado = tempoAdicional;
        //this.tempoAdd = tempoAdd;
        //this.fimAdd = fim.plus( tempoAdicional, ChronoUnit.HOURS );
    }

    /*public Tempo(TempoDto dto){
        this.id = dto.getId();
        this.tipoTempo = dto.getTipoTempo();
        this.inicio = dto.getInicio();
        this.fim = dto.getFim();
        this.tempoContratado = dto.getTempoContratado();
        //this.tempoAdicional = dto.getTempoAdicional();
        //this.fimAdd = dto.getFimAdd();
    }*/

    /*public Tempo(TempoAddTempoDto dto, TempoAdd tempoAdd){
        this.id = dto.getId();
        //this.tipoTempo = dto.getTipoTempo();
        //this.inicio = dto.getInicio();
        //this.fim = dto.getFim();
        //this.tempoContratado = dto.getTempoContratado();
        //this.tempoAdicional = dto.getTempoAdicional();
        //this.fimAdd = dto.getFimAdd();
        //this.tempoAdd = tempoAdd;
        if (this.tempoAdd == null) {
            this.tempoAdd = new ArrayList<>();
        }
        this.tempoAdd.add(tempoAdd);
    }*/


    public Tempo(TempoAddTempoDto dto, Tempo tempo) {

    }
}

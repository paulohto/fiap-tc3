package com.tc3.parquimetro.dominio.tempocontrol.entidade;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.emun.TipoTempo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @OneToMany(mappedBy = "tempo")
    private List<TempoAdd> tempoAdd;

    public Tempo(){}
    public Tempo(Long id, int tempoContratado, TipoTempo tipoTempo){
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();
        this.tempoContratado = tempoContratado;
        this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);
    }

    public Tempo(TempoAddTempoDto dto, Tempo tempo) {

    }
}

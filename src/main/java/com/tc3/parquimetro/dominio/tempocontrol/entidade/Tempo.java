package com.tc3.parquimetro.dominio.tempocontrol.entidade;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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

    public enum TipoTempo {
        fixo,
        variavel
    }

    public Tempo(){}
    public Tempo(Long id, int tempoContratado, TipoTempo tipoTempo){
        this.id = id;
        this.tipoTempo = tipoTempo;
        this.inicio = LocalDateTime.now();
        this.tempoContratado = tempoContratado;
        this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);

        /*if (tipoTempo == TipoTempo.fixo) {
            this.tempoContratado = tempoContratado;
            this.fim = inicio.plus(tempoContratado, ChronoUnit.HOURS);
        } else if (tipoTempo == TipoTempo.variavel) {
            // Lógica para tempo variável (caso necessário).
        }*/

    }

    public Tempo(TempoDto dto){
        this.id = dto.getId();
        this.tipoTempo = dto.getTipoTempo();
        this.inicio = dto.getInicio();
        this.fim = dto.getFim();
        this.tempoContratado = dto.getTempoContratado();
    }

}

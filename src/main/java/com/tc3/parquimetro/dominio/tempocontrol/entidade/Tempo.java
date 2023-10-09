package com.tc3.parquimetro.dominio.tempocontrol.entidade;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "tb_tempo")
public class Tempo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Tempo(Long id, LocalDateTime inicio, LocalDateTime fim){
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
    }

    public Tempo(TempoDto dto){
        this.id = dto.getId();
        this.inicio = dto.getInicio();
        this.fim = dto.getFim();
    }

    public Tempo() {

    }
}

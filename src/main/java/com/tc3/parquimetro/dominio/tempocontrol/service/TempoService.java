package com.tc3.parquimetro.dominio.tempocontrol.service;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoCheckOutDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TempoService {

    @Autowired
    private ITempoRepositorio repoTempo;

    @Transactional
    public TempoDto save(TempoDto tempo){
        Tempo entidade = new Tempo();
        mapperDtoParaEntidade(tempo, entidade);
        var tempoSalvo = repoTempo.save(entidade);
        return new TempoDto(tempoSalvo);
    }

    @Transactional
    public TempoDto update(Long id, TempoDto tempo){
        try {
            Tempo entidade = repoTempo.getReferenceById(id);
            mapperDtoParaEntidadeTempoAdd(tempo, entidade);

            return new TempoDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Id não encontrado, id: " + id);
        }
    }

    @Transactional
    public TempoCheckOutDto checkOut(Long id) {
        try {
            Tempo entidade = repoTempo.getReferenceById(id);
            entidade.setFim(LocalDateTime.now());
            // ToDo verificar se o tempo é FIXO ou VARIAVEL
            entidade.setBill(TempoCheckOut.getBill(entidade));
            var tempoSalvo = repoTempo.save(entidade);
            return new TempoCheckOutDto(tempoSalvo);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Id não encontrado, id: " + id);
        }
    }

    public void delete(Long id){}

    private void mapperDtoParaEntidade(TempoDto dto, Tempo entidade){
        entidade.setTipoTempo(dto.getTipoTempo());
        entidade.setInicio(LocalDateTime.now());
        entidade.setFim(dto.getFim());
        entidade.setTempoContratado(dto.getTempoContratado());
    }

    private void mapperDtoParaEntidadeTempoAdd(TempoDto dto, Tempo entidade){
        entidade.setTipoTempo(entidade.getTipoTempo()); // fixado
        entidade.setInicio(entidade.getInicio()); // fixado
        entidade.setFim(dto.getFim());
        entidade.setTempoContratado(entidade.getTempoContratado()); // fixado
        entidade.setTempoAdicional(dto.getTempoAdicional());
    }
}

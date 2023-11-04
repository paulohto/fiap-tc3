package com.tc3.parquimetro.dominio.tempocontrol.service;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoTempoAddDto;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TempoService {

    @Autowired
    private ITempoRepositorio repoTempo;

    @Transactional(readOnly = true)
    public Page<TempoTempoAddDto> findAll(PageRequest page){
        Page<Tempo> tempos = repoTempo.findAll(page);
        return tempos.map(TempoTempoAddDto::daEntidade);
    }
    //public TempoDto findById(Long id){}

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
            //entidade.setTempoAdicional(tempo.getTempoAdicional());
            //entidade.setTipoTempo(entidade.getTipoTempo());

            return new TempoDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Id não encontrado, id: " + id);
        }
    }

    public void delete(Long id){}

    private void mapperDtoParaEntidade(TempoDto dto, Tempo entidade){
        entidade.setTipoTempo(dto.getTipoTempo());
        entidade.setInicio(dto.getInicio());
        entidade.setFim(dto.getFim());
        entidade.setTempoContratado(dto.getTempoContratado());
        //entidade.setFimAdd(dto.getFimAdd());
        //entidade.setTempoAdicional(dto.getTempoAdicional());
    }

    private void mapperDtoParaEntidadeTempoAdd(TempoDto dto, Tempo entidade){
        entidade.setTipoTempo(entidade.getTipoTempo()); // fixado
        entidade.setInicio(entidade.getInicio()); // fixado
        //entidade.setFim(dto.getFim());
        entidade.setFim(entidade.getFim()); // fixado
        entidade.setTempoContratado(entidade.getTempoContratado()); // fixado
        //entidade.setTempoAdicional(dto.getTempoAdicional());
        //entidade.setFimAdd(dto.getFimAdd());
    }

}

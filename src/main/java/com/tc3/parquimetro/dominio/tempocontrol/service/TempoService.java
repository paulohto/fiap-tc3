package com.tc3.parquimetro.dominio.tempocontrol.service;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.Tempo;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TempoService {

    @Autowired
    private ITempoRepositorio repoTempo;

    //public Page<TempoDto> findAll(PageRequest page){}

    //public TempoDto findById(Long id){}

    @Transactional
    public TempoDto save(TempoDto tempo){
        Tempo entidade = new Tempo();
        mapperDtoParaEntidade(tempo, entidade);
        var tempoSalvo = repoTempo.save(entidade);
        return new TempoDto(tempoSalvo);
    }

    //public TempoDto upDate(Long id, TempoDto tempo){}
    public void delete(Long id){}

    private void mapperDtoParaEntidade(TempoDto dto, Tempo entidade){
        entidade.setInicio(dto.getInicio());
        entidade.setFim(dto.getFim());
    }


}

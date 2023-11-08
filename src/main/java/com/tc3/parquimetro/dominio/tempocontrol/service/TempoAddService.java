package com.tc3.parquimetro.dominio.tempocontrol.service;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.entidade.TempoAdd;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoAddRepositorio;
import com.tc3.parquimetro.dominio.tempocontrol.repositorio.ITempoRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import com.tc3.parquimetro.excecoes.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TempoAddService {

    @Autowired
    private ITempoAddRepositorio repoTempoAdd;
    @Autowired
    private ITempoRepositorio repoTempo;

    /*public TempoAddDto save(TempoAddDto tempoadd) {
        var entidade =TempoAddDto.paraEntidade(tempoadd);
        mapperDtoParaEntidade(tempoadd, entidade);
        var tempoAddSalvo =repoTempoAdd.save(entidade);
        return TempoAddDto.deEntidade(tempoAddSalvo);
    }*/

    @Transactional(readOnly = true)
    public Page<TempoAddTempoDto> findAll(PageRequest page){
        Page<TempoAdd>tempoAdds = repoTempoAdd.findAll(page);
        return tempoAdds.map(TempoAddTempoDto::daEntidade);
    }

    @Transactional(readOnly = true)
    public TempoAddTempoDto findById(Long id){
        var tempoAdd = repoTempoAdd.findById(id).orElseThrow(() -> new ControllerNotFoundException("Tempo Adicional não encontrado."));
        return TempoAddTempoDto.daEntidade(tempoAdd);
    }

    @Transactional
    public TempoAddTempoDto save(TempoAddTempoDto tempoadd) {
        try {
            var tempo = repoTempo.getReferenceById(tempoadd.getTempo().getId());
            var entidade = TempoAddTempoDto.paraEntidade(tempoadd, tempo);
            var tempoAddSalvo = repoTempoAdd.save(entidade);
            return TempoAddTempoDto.daEntidade(tempoAddSalvo);
        }catch (DataIntegrityViolationException e){
            throw  new DatabaseException("Tempo não encontrado");
        }
    }

    @Transactional
    public void delete(Long id){
        try {
            repoTempoAdd.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Tempo Adicional não encontrada, id:" + id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade da base");
        }
    }

    public void mapperDtoParaEntidade(TempoAddDto dto, TempoAdd entidade){
        entidade.setId(dto.getId());
        entidade.setNovoInicio(dto.getNovoInicio());
        entidade.setNovoFim(dto.getNovoFim());
        entidade.setTempoAdicional(dto.getTempoAdicional());
    }

}

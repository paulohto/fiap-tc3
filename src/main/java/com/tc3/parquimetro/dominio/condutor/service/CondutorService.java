package com.tc3.parquimetro.dominio.condutor.service;

import com.tc3.parquimetro.dominio.condutor.dto.CondutorDto;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.condutor.repositorio.CondutorRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import com.tc3.parquimetro.excecoes.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepositorio repoCondutor;

    @Transactional(readOnly = true)
    public Page<CondutorDto> findAllPaged(Pageable pageable){
        Page<Condutor> list = repoCondutor.findAll(pageable);
        return list.map(x -> new CondutorDto(x));
    }
    @Transactional(readOnly = true)
    public CondutorDto findById(Long id){

        Optional<Condutor> obj = repoCondutor.findById(id);
        Condutor entity = obj.orElseThrow(() -> new ControllerNotFoundException("Condutor não encontrado, id: " + id));
        return new CondutorDto(entity);
    }
    @Transactional
    public CondutorDto save(CondutorDto condutor){
        Condutor entidade = new Condutor();
        mapperDtoParaEntidade(condutor, entidade);
        var condutorSalvo = repoCondutor.save(entidade);
        return new CondutorDto(condutorSalvo);
    }
    @Transactional
    public CondutorDto update(String cpf, CondutorDto condutor){
        try {
            Condutor entidade = repoCondutor.getByCpf(cpf);

            mapperDtoParaEntidade(condutor, entidade);
            entidade = repoCondutor.save(entidade);

            return new CondutorDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Condutor não encontrado, cpf: " + cpf);
        }
    }
    public void delete(Long id){
        try {
            repoCondutor.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ControllerNotFoundException("Condutor não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void mapperDtoParaEntidade(CondutorDto dto, Condutor entidade){
        entidade.setEndereco(dto.getEndereco());
    }
}

package com.tc3.parquimetro.dominio.condutor.service;

import com.tc3.parquimetro.dominio.condutor.dto.CondutorDto;
import com.tc3.parquimetro.dominio.condutor.entidade.Condutor;
import com.tc3.parquimetro.dominio.condutor.repositorio.CondutorRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepositorio repoCondutor;

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
            //Todo logica para atualizar um novo condutor.

            return new CondutorDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Condutor não encontrado, cpf: " + cpf);
        }
    }

    public void delete(Long id){}

    private void mapperDtoParaEntidade(CondutorDto dto, Condutor entidade){
        entidade.setEndereco(dto.getEndereco());
    }
}

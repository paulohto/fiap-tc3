package com.tc3.parquimetro.dominio.veiculo.service;

import com.tc3.parquimetro.dominio.veiculo.dto.VeiculoDto;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import com.tc3.parquimetro.dominio.veiculo.repositorio.VeiculoRepositorio;
import com.tc3.parquimetro.excecoes.ControllerNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepositorio repoVeiculo;

    @Transactional
    public VeiculoDto save(VeiculoDto veiculo){
        Veiculo entidade = new Veiculo();
        mapperDtoParaEntidade(veiculo, entidade);
        var veculoSalvo = repoVeiculo.save(entidade);
        return new VeiculoDto(veculoSalvo);
    }

    @Transactional
    public VeiculoDto update(String placa, VeiculoDto veiculo){
        try {
            Veiculo entidade = repoVeiculo.getByPlaca(placa);
            //Todo logica para atualizar um novo veiculo.

            return new VeiculoDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Veiculo não encontrado, placa: " + placa);
        }
    }

    public void delete(Long id){}

    private void mapperDtoParaEntidade(VeiculoDto dto, Veiculo entidade){
        entidade.setCondutor(dto.getCondutor());
    }
}

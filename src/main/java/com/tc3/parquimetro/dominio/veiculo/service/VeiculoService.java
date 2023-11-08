package com.tc3.parquimetro.dominio.veiculo.service;

import com.tc3.parquimetro.dominio.veiculo.dto.VeiculoDto;
import com.tc3.parquimetro.dominio.veiculo.entidade.Veiculo;
import com.tc3.parquimetro.dominio.veiculo.repositorio.VeiculoRepositorio;
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
public class VeiculoService {

    @Autowired
    private VeiculoRepositorio repoVeiculo;

    @Transactional(readOnly = true)
    public Page<VeiculoDto> findAllPaged(Pageable pageable){
        Page<Veiculo> list = repoVeiculo.findAll(pageable);
        return list.map(x -> new VeiculoDto(x));
    }
    @Transactional(readOnly = true)
    public VeiculoDto findById(Long id){

        Optional<Veiculo> obj = repoVeiculo.findById(id);
        Veiculo entity = obj.orElseThrow(() -> new ControllerNotFoundException("Veiculo não encontrado, id: " + id));
        return new VeiculoDto(entity);
    }
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

            mapperDtoParaEntidade(veiculo, entidade);
            entidade = repoVeiculo.save(entidade);

            return new VeiculoDto(entidade);
        } catch(EntityNotFoundException e){
            throw new ControllerNotFoundException("Veiculo não encontrado, placa: " + placa);
        }
    }
    public void delete(Long id){
        try {
            repoVeiculo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ControllerNotFoundException("Veiculo não encontrado, placa: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }
    private void mapperDtoParaEntidade(VeiculoDto dto, Veiculo entidade){
        entidade.setCondutor(dto.getCondutor());
    }
}

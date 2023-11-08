package com.tc3.parquimetro.dominio.veiculo.controller;

import com.tc3.parquimetro.dominio.veiculo.dto.VeiculoDto;
import com.tc3.parquimetro.dominio.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping(value = "/veiculo")
    public ResponseEntity<Page<VeiculoDto>> findAll(Pageable pageable){

        Page<VeiculoDto> list = veiculoService.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/veiculo/{id}")
    public ResponseEntity<VeiculoDto> findById(@PathVariable Long id){

        VeiculoDto dto = veiculoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping("/veiculo")
    public ResponseEntity<VeiculoDto> save(@Valid @RequestBody VeiculoDto veiculo){
        var veiculoSaved = veiculoService.save(veiculo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{placa}").buildAndExpand((veiculoSaved.getPlaca())).toUri();
        return ResponseEntity.created(uri).body(veiculoSaved);
    }
    @PutMapping("/veiculo/{placa}")
    public ResponseEntity<VeiculoDto> update(@RequestBody VeiculoDto veiculo, @PathVariable String placa){
        var veiculoUpdated = veiculoService.update(placa,veiculo);
        return ResponseEntity.ok(veiculoUpdated);
    }
    @DeleteMapping(value = "/veiculo/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

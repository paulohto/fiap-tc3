package com.tc3.parquimetro.dominio.veiculo.controller;

import com.tc3.parquimetro.dominio.veiculo.dto.VeiculoDto;
import com.tc3.parquimetro.dominio.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

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

}

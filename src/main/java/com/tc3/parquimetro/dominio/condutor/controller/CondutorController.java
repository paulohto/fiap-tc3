package com.tc3.parquimetro.dominio.condutor.controller;

import com.tc3.parquimetro.dominio.condutor.dto.CondutorDto;
import com.tc3.parquimetro.dominio.condutor.service.CondutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @PostMapping("/condutor")
    public ResponseEntity<CondutorDto> save(@Valid @RequestBody CondutorDto condutor){
        var condutorSaved = condutorService.save(condutor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cpf}").buildAndExpand((condutorSaved.getCpf())).toUri();
        return ResponseEntity.created(uri).body(condutorSaved);
    }

    @PutMapping("/condutor/{cpf}")
    public ResponseEntity<CondutorDto> update(@RequestBody CondutorDto condutor, @PathVariable String cpf){
        var condutorUpdated = condutorService.update(cpf, condutor);
        return ResponseEntity.ok(condutorUpdated);
    }

}

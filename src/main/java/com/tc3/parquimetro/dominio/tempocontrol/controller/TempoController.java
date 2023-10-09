package com.tc3.parquimetro.dominio.tempocontrol.controller;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.service.TempoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TempoController {

    @Autowired
    private TempoService tempoService;

    @PostMapping("/tempo")
    public ResponseEntity<TempoDto> save(@Valid @RequestBody TempoDto tempo){
        var tempoSaved = tempoService.save(tempo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((tempoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(tempoSaved);
    }
}

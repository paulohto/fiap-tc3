package com.tc3.parquimetro.dominio.tempocontrol.controller;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoTempoAddDto;
import com.tc3.parquimetro.dominio.tempocontrol.service.TempoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TempoController {

    @Autowired
    private TempoService tempoService;

    @GetMapping("/tempo")
    public ResponseEntity<Page<TempoTempoAddDto>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var tempos = tempoService.findAll(pageRequest);
        return ResponseEntity.ok(tempos);
    }
    @PostMapping("/tempo")
    public ResponseEntity<TempoDto> save(@Valid @RequestBody TempoDto tempo){
        var tempoSaved = tempoService.save(tempo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((tempoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(tempoSaved);
    }
    @PutMapping("/tempo/{id}")
    public ResponseEntity<TempoDto> update(@RequestBody TempoDto tempo, @PathVariable Long id){
        var tempoUpdated =tempoService.update(id,tempo);
        return ResponseEntity.ok(tempoUpdated);
    }
}

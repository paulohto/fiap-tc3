package com.tc3.parquimetro.dominio.tempocontrol.controller;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoAddTempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.service.TempoAddService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TempoAddController {
    @Autowired
    public TempoAddService tempoAddService;

    @GetMapping("/tempoadd")
    public ResponseEntity<Page<TempoAddTempoDto>> findAll(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho", defaultValue = "10") Integer tamanho
    ){
        PageRequest pageRequest = PageRequest.of(pagina, tamanho);
        var tempoAdds = tempoAddService.findAll(pageRequest);
        return ResponseEntity.ok(tempoAdds);
    }

    @GetMapping("/tempoadd/{id}")
    public ResponseEntity<TempoAddTempoDto> findById(@PathVariable Long id){
        var tempoAdd = tempoAddService.findById(id);
        return ResponseEntity.ok(tempoAdd);
    }

    @PostMapping("/tempoadd")
    public ResponseEntity<TempoAddTempoDto> save(@Valid @RequestBody TempoAddTempoDto dto){
        var tempoAddSalvo = tempoAddService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tempoAddSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(tempoAddSalvo);
    }

    @DeleteMapping("/tempoadd/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        tempoAddService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

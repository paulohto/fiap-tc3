package com.tc3.parquimetro.dominio.tempocontrol.controller;

import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoCheckOutDto;
import com.tc3.parquimetro.dominio.tempocontrol.dto.TempoDto;
import com.tc3.parquimetro.dominio.tempocontrol.service.SmsService;
import com.tc3.parquimetro.dominio.tempocontrol.service.TempoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/tempo")
public class TempoController {

    @Autowired
    private TempoService tempoService;

    @Autowired
    private SmsService smsService;

    @PostMapping
    public ResponseEntity<TempoDto> save(@Valid @RequestBody TempoDto tempo){
        var tempoSaved = tempoService.save(tempo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand((tempoSaved.getId())).toUri();
        return ResponseEntity.created(uri).body(tempoSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TempoDto> update(@RequestBody TempoDto tempo, @PathVariable Long id){
        var tempoUpdated =tempoService.update(id,tempo);
        return ResponseEntity.ok(tempoUpdated);
    }
    @PostMapping("/{id}/exit")
    public ResponseEntity<TempoCheckOutDto> checkOut(@PathVariable Long id) {
        // ToDo verificar se já não esta fechado e lançar exceção
        var tempoCheckOut = tempoService.checkOut(id);
        return ResponseEntity.ok(tempoCheckOut);
    }
    @GetMapping("/{id}/notification")
    public void notifySms(@PathVariable Long id){
        smsService.sendSms(id);
    }
}

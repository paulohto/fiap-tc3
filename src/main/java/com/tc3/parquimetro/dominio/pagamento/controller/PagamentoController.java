package com.tc3.parquimetro.dominio.pagamento.controller;

import com.tc3.parquimetro.dominio.pagamento.dto.PagamentoDto;
import com.tc3.parquimetro.dominio.pagamento.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoDto> save(@Valid @RequestBody PagamentoDto pagamento){
        var pagamentoSaved = pagamentoService.save(pagamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{placa}").buildAndExpand((pagamentoSaved.getPlaca())).toUri();
        return ResponseEntity.created(uri).body(pagamentoSaved);
    }

   @GetMapping("/pagamento/{placa}")
    public ResponseEntity<PagamentoDto> getPagamento(@PathVariable String placa){
        var pagamentoGet = pagamentoService.getPagamento(placa);
        return ResponseEntity.ok(pagamentoGet);
    }
}

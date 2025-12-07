package com.gestaometas.controller;

import com.gestaometas.model.Habito;
import com.gestaometas.service.HabitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitos")
public class HabitoController {
    
    @Autowired
    private HabitoService habitoService;
    
    @GetMapping
    public List<Habito> listarTodos() {
        return habitoService.getAllHabitos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Habito> buscarPorId(@PathVariable Long id) {
        return habitoService.getHabitoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Habito criar(@RequestBody Habito habito) {
        return habitoService.createHabito(habito);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Habito> atualizar(@PathVariable Long id, @RequestBody Habito habito) {
        return habitoService.updateHabito(id, habito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (habitoService.deleteHabito(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/{id}/registrar")
    public ResponseEntity<Habito> registrarDia(
            @PathVariable Long id,
            @RequestParam LocalDate data,
            @RequestParam boolean realizado) {
        return habitoService.registrarDia(id, data, realizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

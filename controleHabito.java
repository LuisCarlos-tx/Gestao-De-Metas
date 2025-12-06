package com.gestaometas.controller;

import com.gestaometas.model.Habito;
import com.gestaometas.service.HabitoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/habitos")
@Tag(name = "Hábitos", description = "Operações para gerenciamento de hábitos")
public class HabitoController {
    
    @Autowired
    private HabitoService habitoService;
    
    @GetMapping
    @Operation(summary = "Listar todos os hábitos")
    public ResponseEntity<List<Habito>> listarTodos() {
        return ResponseEntity.ok(habitoService.getAllHabitos());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar hábito por ID")
    public ResponseEntity<Habito> buscarPorId(@PathVariable Long id) {
        return habitoService.getHabitoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Criar novo hábito")
    public ResponseEntity<Habito> criar(@RequestBody Habito habito) {
        return ResponseEntity.ok(habitoService.createHabito(habito));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar hábito")
    public ResponseEntity<Habito> atualizar(@PathVariable Long id, @RequestBody Habito habito) {
        return habitoService.updateHabito(id, habito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir hábito")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (habitoService.deleteHabito(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/{id}/registrar")
    @Operation(summary = "Registrar dia do hábito")
    public ResponseEntity<Habito> registrarDia(
            @PathVariable Long id,
            @RequestParam LocalDate data,
            @RequestParam boolean realizado) {
        
        return habitoService.registrarDia(id, data, realizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

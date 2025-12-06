package com.gestaometas.controller;

import com.gestaometas.model.Meta;
import com.gestaometas.service.MetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
@Tag(name = "Metas", description = "Operações para gerenciamento de metas")
public class MetaController {
    
    @Autowired
    private MetaService metaService;
    
    @GetMapping
    @Operation(summary = "Listar todas as metas")
    public ResponseEntity<List<Meta>> listarTodas() {
        return ResponseEntity.ok(metaService.getAllMetas());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar meta por ID")
    public ResponseEntity<Meta> buscarPorId(@PathVariable Long id) {
        return metaService.getMetaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Criar nova meta")
    public ResponseEntity<Meta> criar(@RequestBody Meta meta) {
        return ResponseEntity.ok(metaService.createMeta(meta));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar meta")
    public ResponseEntity<Meta> atualizar(@PathVariable Long id, @RequestBody Meta meta) {
        return metaService.updateMeta(id, meta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir meta")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (metaService.deleteMeta(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @PatchMapping("/{id}/progresso")
    @Operation(summary = "Atualizar progresso da meta")
    public ResponseEntity<Meta> atualizarProgresso(
            @PathVariable Long id,
            @RequestParam int progresso) {
        
        return metaService.atualizarProgresso(id, progresso)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

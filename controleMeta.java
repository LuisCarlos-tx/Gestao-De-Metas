package com.gestaohabitos.controller;

import com.gestaohabitos.model.Meta;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/metas")
public class MetaController {
    
    private final List<Meta> metas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping
    public List<Meta> getAllMetas() {
        return metas;
    }

    @GetMapping("/{id}")
    public Meta getMetaById(@PathVariable Long id) {
        return metas.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Meta createMeta(@RequestBody Meta meta) {
        meta.setId(counter.getAndIncrement());
        metas.add(meta);
        return meta;
    }

    @PutMapping("/{id}")
    public Meta updateMeta(@PathVariable Long id, @RequestBody Meta metaAtualizada) {
        for (int i = 0; i < metas.size(); i++) {
            Meta meta = metas.get(i);
            if (meta.getId().equals(id)) {
                metaAtualizada.setId(id);
                metas.set(i, metaAtualizada);
                return metaAtualizada;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteMeta(@PathVariable Long id) {
        boolean removed = metas.removeIf(m -> m.getId().equals(id));
        return removed ? "Meta excluída com sucesso" : "Meta não encontrada";
    }

    @PatchMapping("/{id}/progresso")
    public Meta atualizarProgresso(@PathVariable Long id, @RequestParam int progresso) {
        Meta meta = getMetaById(id);
        if (meta != null) {
            meta.setProgresso(progresso);
        }
        return meta;
    }

    @GetMapping("/{id}/status")
    public String getStatus(@PathVariable Long id) {
        Meta meta = getMetaById(id);
        if (meta == null) {
            return "Meta não encontrada";
        }
        
        String status = meta.isConcluida() ? "CONCLUÍDA" : "EM ANDAMENTO";
        if (meta.estaVencida()) {
            status = "VENCIDA";
        }
        
        return String.format("Status: %s | Progresso: %d%% | Dias restantes: %d", 
                           status, meta.getProgresso(), meta.diasRestantes());
    }
}

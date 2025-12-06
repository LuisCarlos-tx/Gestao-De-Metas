package com.gestaometas.repository;

import com.gestaometas.model.Habito;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HabitoRepository {
    private final List<Habito> habitos = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Habito> findAll() {
        return new ArrayList<>(habitos);
    }

    public Optional<Habito> findById(Long id) {
        return habitos.stream()
                .filter(h -> h.getId() != null && h.getId().equals(id))
                .findFirst();
    }

    public Habito save(Habito habito) {
        if (habito.getId() == null) {
            habito.setId(idGenerator.getAndIncrement());
            habitos.add(habito);
        } else {
            // Atualiza existente
            habitos.removeIf(h -> h.getId().equals(habito.getId()));
            habitos.add(habito);
        }
        return habito;
    }

    public boolean deleteById(Long id) {
        return habitos.removeIf(h -> h.getId() != null && h.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return habitos.stream()
                .anyMatch(h -> h.getId() != null && h.getId().equals(id));
    }
}

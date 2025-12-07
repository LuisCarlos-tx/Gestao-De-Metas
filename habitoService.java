package com.gestaometas.service;

import com.gestaometas.model.Habito;
import com.gestaometas.repository.HabitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitoService {
    
    @Autowired
    private HabitoRepository habitoRepository;
    
    public List<Habito> getAllHabitos() {
        return habitoRepository.findAll();
    }
    
    public Optional<Habito> getHabitoById(Long id) {
        return habitoRepository.findById(id);
    }
    
    public Habito createHabito(Habito habito) {
        return habitoRepository.save(habito);
    }
    
    public Optional<Habito> updateHabito(Long id, Habito habitoAtualizado) {
        return habitoRepository.findById(id).map(habito -> {
            habito.setNome(habitoAtualizado.getNome());
            habito.setDescricao(habitoAtualizado.getDescricao());
            return habitoRepository.save(habito);
        });
    }
    
    public boolean deleteHabito(Long id) {
        return habitoRepository.deleteById(id);
    }
    
    public Optional<Habito> registrarDia(Long id, LocalDate data, boolean realizado) {
        return habitoRepository.findById(id).map(habito -> {
            habito.registrarDia(data, realizado);
            return habitoRepository.save(habito);
        });
    }
}

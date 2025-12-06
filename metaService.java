package com.gestaometas.service;

import com.gestaometas.model.Meta;
import com.gestaometas.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {
    
    @Autowired
    private MetaRepository metaRepository;
    
    public List<Meta> getAllMetas() {
        return metaRepository.findAll();
    }
    
    public Optional<Meta> getMetaById(Long id) {
        return metaRepository.findById(id);
    }
    
    public Meta createMeta(Meta meta) {
        return metaRepository.save(meta);
    }
    
    public Optional<Meta> updateMeta(Long id, Meta metaAtualizada) {
        return metaRepository.findById(id).map(meta -> {
            meta.setTitulo(metaAtualizada.getTitulo());
            meta.setDescricao(metaAtualizada.getDescricao());
            meta.setDataFim(metaAtualizada.getDataFim());
            return metaRepository.save(meta);
        });
    }
    
    public boolean deleteMeta(Long id) {
        return metaRepository.deleteById(id);
    }
    
    public Optional<Meta> atualizarProgresso(Long id, int progresso) {
        return metaRepository.findById(id).map(meta -> {
            meta.setProgresso(progresso);
            return metaRepository.save(meta);
        });
    }
}

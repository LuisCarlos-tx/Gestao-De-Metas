package com.gestaometas.repository;

package com.gestaometas.repository;

import com.gestaometas.model.Meta;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MetaRepository {
    private final List<Meta> metas = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);
    
    public List<Meta> findAll() { return new ArrayList<>(metas); }
    
    public Optional<Meta> findById(Long id) {
        return metas.stream()
            .filter(m -> m.getId() != null && m.getId().equals(id))
            .findFirst();
    }
    
    public Meta save(Meta meta) {
        if (meta.getId() == null) {
            meta.setId(counter.getAndIncrement());
            metas.add(meta);
        } else {
            metas.removeIf(m -> m.getId().equals(meta.getId()));
            metas.add(meta);
        }
        return meta;
    }
    
    public boolean deleteById(Long id) {
        return metas.removeIf(m -> m.getId() != null && m.getId().equals(id));
    }
}

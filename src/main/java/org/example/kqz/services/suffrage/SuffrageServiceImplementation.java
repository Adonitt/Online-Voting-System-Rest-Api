package org.example.kqz.services.suffrage;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.SuffrageEntity;
import org.example.kqz.repositories.SuffrageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuffrageServiceImplementation implements SuffrageService {

    private final SuffrageRepository repository;

    @Override
    public SuffrageEntity add(SuffrageEntity entity) {
        return repository.save(entity);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsByPersonalNo(String personalNo) {
        return repository.findByPersonalNo(personalNo).isPresent();
    }

    @Override
    public List<SuffrageEntity> findAll() {
        return repository.findAll();
    }
}

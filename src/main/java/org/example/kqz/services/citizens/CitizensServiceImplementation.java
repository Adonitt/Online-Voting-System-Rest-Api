package org.example.kqz.services.citizens;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.CitizensEntity;
import org.example.kqz.repositories.CitizensRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizensServiceImplementation implements CitizensService {

    private final CitizensRepository repository;

    @Override
    public CitizensEntity add(CitizensEntity entity) {
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
    public List<CitizensEntity> findAll() {
        return repository.findAll();
    }
}

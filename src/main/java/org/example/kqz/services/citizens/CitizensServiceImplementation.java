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

    public boolean existsByPersonalNo(String personalNo) {
        List<CitizensEntity> results = repository.findAllByPersonalNo(personalNo);
        if (results.size() > 1) {
            throw new IllegalStateException("Ka më shumë se një citizen me të njejtin personalNo: " + personalNo);
        }
        return !results.isEmpty();
    }

    @Override
    public List<CitizensEntity> findAllByPersonalNo(String personalNo) {
        return repository.findAllByPersonalNo(personalNo);
    }


    @Override
    public List<CitizensEntity> findAll() {
        return repository.findAll();
    }
}

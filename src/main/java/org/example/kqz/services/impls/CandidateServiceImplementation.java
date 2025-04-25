package org.example.kqz.services.impls;

import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.services.interfaces.CandidateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImplementation implements CandidateService {
    @Override
    public CRDCandidateRequestDto add(CRDCandidateRequestDto entity) {
        return null;
    }

    @Override
    public List<CRDCandidateRequestDto> findAll() {
        return List.of();
    }

    @Override
    public CRDCandidateRequestDto findById(Long id) {
        return null;
    }

    @Override
    public void modify(UpdateCandidateRequestDto entity, Long id) {

    }

    @Override
    public void remove(Long id) {

    }
}

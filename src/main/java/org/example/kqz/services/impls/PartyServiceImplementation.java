package org.example.kqz.services.impls;

import org.example.kqz.dtos.parties.CrudPartyRequestDto;
import org.example.kqz.services.interfaces.PartyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyServiceImplementation implements PartyService {
    @Override
    public CrudPartyRequestDto add(CrudPartyRequestDto entity) {
        return null;
    }

    @Override
    public List<CrudPartyRequestDto> findAll() {
        return List.of();
    }

    @Override
    public CrudPartyRequestDto findById(Long id) {
        return null;
    }

    @Override
    public void modify(CrudPartyRequestDto entity, Long id) {

    }

    @Override
    public void remove(Long id) {

    }
}

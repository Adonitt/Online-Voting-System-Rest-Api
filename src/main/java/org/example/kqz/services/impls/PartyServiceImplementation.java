package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.exceptions.PartyAlreadyExistsException;
import org.example.kqz.helpers.FileStorageHelper;
import org.example.kqz.mappers.PartiesMapper;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.services.interfaces.PartyService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyServiceImplementation implements PartyService {
    private final PartiesMapper mapper;
    private final PartyRepository repository;
    private final PartyRepository partyRepository;
    private final FileStorageHelper fileStorageHelper;

    @Override
    public CRDPartyRequestDto add(CRDPartyRequestDto dto) {
        validateParty(dto);
        var entity = mapper.toEntity(dto);

        if (entity.getCandidates() != null && !entity.getCandidates().isEmpty()) {
            for (CandidatesEntity candidate : entity.getCandidates()) {
                candidate.setParty(entity);
            }
        }

        if (dto.getSymbol() != null && !dto.getSymbol().isEmpty()) {
            try {
                String savedFileName = fileStorageHelper.uploadFile("uploads", dto.getSymbol().getOriginalFilename(), dto.getSymbol().getBytes());
                entity.setSymbol(savedFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read photo bytes", e);
            }
        } else {
            entity.setSymbol("ks.jpg");
        }

        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        var savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }


    private void validateParty(CRDPartyRequestDto dto) {
        if (repository.existsByName(dto.getName())) {
            throw new PartyAlreadyExistsException("Party with name '" + dto.getName() + "' already exists");
        }

        if (repository.existsByNumberOfParty(dto.getNumberOfParty())) {
            throw new PartyAlreadyExistsException("Party with number '" + dto.getNumberOfParty() + "' already exists");
        }

    }

    @Override
    public List<PartyListingDto> findAll() {
        var partiesList = repository.findAll();
        return mapper.toPartyListingDto(partiesList);
    }

    @Override
    public CRDPartyRequestDto findById(Long id) {
        var partyEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Party not found"));
        System.out.println("Found Party: " + partyEntity.getName());
        return mapper.toDto(partyEntity);
    }


    @Override
    public void removeById(Long id) {
        findById(id);
        repository.deleteById(id);
    }


    @Override
    public UpdatePartyDto modify(UpdatePartyDto dto, Long id) {
        PartyEntity partyFromDB = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + id));

        if (dto.getSymbol() != null && !dto.getSymbol().isEmpty()) {
            try {
                String savedFileName = fileStorageHelper.uploadFile("uploads", dto.getSymbol().getOriginalFilename(), dto.getSymbol().getBytes());
                partyFromDB.setSymbol(savedFileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read photo bytes", e);
            }
        } else {
            partyFromDB.setSymbol("ks.jpg");
        }

        partyFromDB.setName(dto.getName());
        partyFromDB.setNumberOfParty(dto.getNumberOfParty());
        partyFromDB.setDescription(dto.getDescription());

        partyFromDB.setUpdatedAt(LocalDateTime.now());
        partyFromDB.setUpdatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        var saved = partyRepository.save(partyFromDB);
        return mapper.toUpdatePartyDto(saved);
    }
}

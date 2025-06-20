package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyDetailsDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.dtos.votes.VotingDates;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.exceptions.AddPartyCandidateClosedException;
import org.example.kqz.exceptions.PartyAlreadyExistsException;
import org.example.kqz.exceptions.PartyHasCandidateException;
import org.example.kqz.exceptions.PartyNotFoundException;
import org.example.kqz.mappers.PartiesMapper;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.services.interfaces.PartyService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartyServiceImplementation implements PartyService {
    private final PartiesMapper mapper;
    private final PartyRepository repository;
    private final PartyRepository partyRepository;

    @Override
    public CRDPartyRequestDto create(CRDPartyRequestDto dto, MultipartFile symbol) {
        validateParty(dto);

        var fileName = "";

        if (symbol != null && !symbol.isEmpty()) fileName = uploadFile(symbol);

        var entity = mapper.toEntity(dto);

        if (entity.getCandidates() != null && !entity.getCandidates().isEmpty()) {
            for (CandidatesEntity candidate : entity.getCandidates()) {
                candidate.setParty(entity);
            }
        }
        entity.setSymbol(fileName);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        var savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }

    public String uploadFile(MultipartFile imageFile) {
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir);
            Path imagePath = uploadDir.resolve(filename);
            Files.write(imagePath, imageFile.getBytes());
            return "uploads/" + filename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }

    private void validateParty(CRDPartyRequestDto dto) {

        if (LocalDate.now().isAfter(VotingDates.PARTY_CREATION_DEADLINE)) {
            throw new AddPartyCandidateClosedException("Adding parties is closed!");
        }

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
    public PartyDetailsDto findById(Long id) {
        var partyEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Party not found"));
        System.out.println("Found Party: " + partyEntity.getName());
        return mapper.toDetailsDto(partyEntity);
    }


    @Override
    public void removeById(Long id) {
        PartyEntity party = partyRepository.findById(id)
                .orElseThrow(() -> new PartyNotFoundException("Party not found"));

        if (!party.getCandidates().isEmpty()) {
            throw new PartyHasCandidateException("Party cannot be deleted because it has candidates.");
        }

        partyRepository.deleteById(id);
    }


    @Override
    public UpdatePartyDto modify(UpdatePartyDto dto, Long id, MultipartFile symbol) {
        PartyEntity partyFromDB = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + id));

        var fileName = "";

        if (symbol != null && !symbol.isEmpty()) fileName = uploadFile(symbol);

        partyFromDB.setName(dto.getName());
        partyFromDB.setNumberOfParty(dto.getNumberOfParty());
        partyFromDB.setDescription(dto.getDescription());
        partyFromDB.setAbbreviationName(dto.getAbbreviationName());

        if (fileName.isBlank()) {
            partyFromDB.setSymbol(partyFromDB.getSymbol()); // KEEP existing
        } else {
            partyFromDB.setSymbol(fileName); // SET new
        }


        partyFromDB.setUpdatedAt(LocalDateTime.now());
        partyFromDB.setUpdatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        var saved = partyRepository.save(partyFromDB);
        return mapper.toUpdatePartyDto(saved);
    }
}

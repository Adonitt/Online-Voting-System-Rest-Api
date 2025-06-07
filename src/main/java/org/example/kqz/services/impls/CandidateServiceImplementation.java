package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.CandidateListingDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.exceptions.CandidateNumberAlreadyExistsException;
import org.example.kqz.exceptions.MustBe18ToVote;
import org.example.kqz.exceptions.NotKosovoCitizenException;
import org.example.kqz.exceptions.PersonalNumberAlreadyExists;
import org.example.kqz.mappers.CandidatesMapper;
import org.example.kqz.repositories.CandidatesRepository;
import org.example.kqz.repositories.CitizensRepository;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.services.interfaces.CandidateService;
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
public class CandidateServiceImplementation implements CandidateService {
    private final CandidatesRepository candidateRepository;
    private final CandidatesMapper mapper;
    private final CitizensRepository citizensRepository;
    private final PartyRepository partyRepository;

    @Override
    public CRDCandidateRequestDto add(CRDCandidateRequestDto dto, MultipartFile photo) {
        validateCandidate(dto);

        var fileName = "";

        if (photo != null && !photo.isEmpty()) fileName = uploadFile(photo);

        var entity = mapper.toEntity(dto);

        PartyEntity party = partyRepository.findById(dto.getParty())
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + dto.getParty()));
        entity.setParty(party);

        Long maxCandidateNumber = candidateRepository.findMaxCandidateNumberByPartyId(party.getId());

        if (maxCandidateNumber == null) {
            maxCandidateNumber = 0L;
        }
        entity.setCandidateNumber(maxCandidateNumber + 1);
        entity.setPhoto(fileName);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(AuthServiceImplementation.getLoggedInUserEmail() + " - " + AuthServiceImplementation.getLoggedInUserRole());

        var saved = candidateRepository.save(entity);
        return mapper.toDto(saved);
    }

    public String uploadFile(MultipartFile imageFile) {
        String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir); // Create uploads/ if it doesn't exist
            Path imagePath = uploadDir.resolve(filename);
            Files.write(imagePath, imageFile.getBytes());
            return "uploads/" + filename;  // Return the path to the image file
        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file", e);
        }
    }

    private void validateCandidate(CRDCandidateRequestDto dto) {
        if (!citizensRepository.existsByPersonalNoAndFirstNameAndLastNameAndBirthDate(dto.getPersonalNo(), dto.getFirstName(), dto.getLastName(), dto.getBirthDate())) {
            throw new NotKosovoCitizenException("Incorrect data! Please recheck personal number, first name, last name and birth date.");
        }

        if (dto.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("Candidate must be at least 18 years old to register");
        }

        if (candidateRepository.existsByPersonalNo(dto.getPersonalNo())) {
            throw new PersonalNumberAlreadyExists("A candidate with Personal number " + dto.getPersonalNo() + " already exists");
        }

        if (dto.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("Candidate must be at least 18 years old to register");
        }
        if (dto.getParty() == null) {
            throw new IllegalArgumentException("Party ID cannot be null");
        }
    }

    @Override
    public List<CandidateListingDto> findAll() {
        var candidatesList = candidateRepository.findAll();
        return mapper.toListingDto(candidatesList);
    }

    @Override
    public CRDCandidateRequestDto findById(Long id) {
        var candidateEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        return mapper.toDto(candidateEntity);
    }

    @Override
    public UpdateCandidateRequestDto modify(UpdateCandidateRequestDto dto, Long id, MultipartFile photo) {
        CandidatesEntity candidateFromDB = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));

        var fileName = "";
        if (photo != null && !photo.isEmpty()) fileName = uploadFile(photo);

        PartyEntity party = partyRepository.findById(dto.getParty())
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + dto.getParty()));

        var existingCandidate = candidateRepository.findByCandidateNumber(dto.getCandidateNumber());

        if (existingCandidate.isPresent() && !existingCandidate.get().getId().equals(id)) {
            throw new CandidateNumberAlreadyExistsException("Candidate number already exists!");
        }

        if (fileName.isBlank()) {
            candidateFromDB.setPhoto(candidateFromDB.getPhoto());
        } else {
            candidateFromDB.setPhoto(fileName);
        }
        candidateFromDB.setNationality(dto.getNationality());

        candidateFromDB.setCandidateNumber(candidateFromDB.getCandidateNumber());

        candidateFromDB.setParty(party);

        candidateFromDB.setUpdatedAt(LocalDateTime.now());
        candidateFromDB.setUpdatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        CandidatesEntity updatedCandidate = candidateRepository.save(candidateFromDB);
        return mapper.toUpdateDto(updatedCandidate);
    }

    @Override
    public void removeById(Long id) {
        findById(id);
        candidateRepository.deleteById(id);
    }
}

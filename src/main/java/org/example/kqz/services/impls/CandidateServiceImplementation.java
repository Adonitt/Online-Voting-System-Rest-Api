package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.dtos.user.UpdateUserRequestDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.exceptions.MustBe18ToVote;
import org.example.kqz.exceptions.NotKosovoCitizenException;
import org.example.kqz.exceptions.PersonalNumberAlreadyExists;
import org.example.kqz.mappers.CandidatesMapper;
import org.example.kqz.repositories.CandidatesRepository;
import org.example.kqz.repositories.CitizensRepository;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.services.interfaces.CandidateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateServiceImplementation implements CandidateService {
    private final CandidatesRepository candidateRepository;
    private final CandidatesMapper mapper;
    private final CitizensRepository citizensRepository;
    private final PartyRepository partyRepository;

    @Override
    public CRDCandidateRequestDto add(CRDCandidateRequestDto dto) {
        validateCandidate(dto);
        var entity = mapper.toEntity(dto);

        PartyEntity party = partyRepository.findById(dto.getParty())
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + dto.getParty()));

        entity.setParty(party);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setCreatedBy(AuthServiceImplementation.getLoggedInUserEmail());

        var saved = candidateRepository.save(entity);
        return mapper.toDto(saved);
    }


    private void validateCandidate(CRDCandidateRequestDto dto) {
        if (!citizensRepository.existsByPersonalNoAndFirstNameAndLastName(dto.getPersonalNo(), dto.getFirstName(), dto.getLastName())) {
            throw new NotKosovoCitizenException("There is no such person in Kosovo with personal number " + dto.getPersonalNo() + "and name " + dto.getFirstName() + " " + dto.getLastName() + " in Kosovo");
        }
        if (candidateRepository.existsByPersonalNo(dto.getPersonalNo())) {
            throw new PersonalNumberAlreadyExists("Personal number " + dto.getPersonalNo() + " already exists");
        }
        if (dto.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("Candidate must be at least 18 years old to register");
        }
        if (dto.getParty() == null) {
            throw new IllegalArgumentException("Party ID cannot be null");
        }
    }

    @Override
    public List<CRDCandidateRequestDto> findAll() {
        var candidatesList = candidateRepository.findAll();
        return mapper.toDtoList(candidatesList);
    }

    @Override
    public CRDCandidateRequestDto findById(Long id) {
        var candidateEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
        return mapper.toDto(candidateEntity);
    }

    @Override
    public UpdateCandidateRequestDto modify(UpdateCandidateRequestDto dto, Long id) {
        CandidatesEntity candidateFromDB = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));

        candidateFromDB.setBirthDate(dto.getBirthDate());
        candidateFromDB.setNationality(dto.getNationality());

        PartyEntity party = partyRepository.findById(dto.getParty())
                .orElseThrow(() -> new RuntimeException("Party not found with ID: " + dto.getParty()));

        candidateFromDB.setUpdatedAt(LocalDateTime.now());
        candidateFromDB.setParty(party);
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

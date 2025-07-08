package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VotingDates;
import org.example.kqz.entities.VotingDatesEntity;
import org.example.kqz.repositories.VotingDatesRepository;
import org.example.kqz.services.interfaces.VotingDatesService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class VotingDatesServiceImpl implements VotingDatesService {

    private final VotingDatesRepository votingDatesRepository;

    private static final LocalDate DEFAULT_PARTY_CREATION_DEADLINE = LocalDate.of(2025, 8, 25);
    private static final LocalDate DEFAULT_VOTING_DAY = LocalDate.of(2025, 8, 26);

    @Override
    public List<VotingDates> getAllVotingDates() {
        return votingDatesRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public VotingDates updateVotingDates(Long id, VotingDates votingDates) {
        VotingDatesEntity entity = votingDatesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VotingDates not found with id: " + id));

        entity.setPartyCreationDeadline(votingDates.getPartyCreationDeadline());
        entity.setVotingDay(votingDates.getVotingDay());

        VotingDatesEntity updated = votingDatesRepository.save(entity);
        return toDto(updated);
    }

    public VotingDates getCurrentVotingDates() {
        VotingDatesEntity entity = votingDatesRepository.findTopByOrderByIdDesc();
        if (entity == null) {
            return new VotingDates(DEFAULT_PARTY_CREATION_DEADLINE, DEFAULT_VOTING_DAY);
        }
        return toDto(entity);
    }

    public VotingDates createVotingDates(VotingDates votingDates) {
        long count = votingDatesRepository.count();
        if (count >= 1) {
            throw new RuntimeException("Voting dates already exist. Only one record is allowed.");
        }

        VotingDatesEntity entity = new VotingDatesEntity();
        entity.setPartyCreationDeadline(votingDates.getPartyCreationDeadline());
        entity.setVotingDay(votingDates.getVotingDay());

        VotingDatesEntity saved = votingDatesRepository.save(entity);
        return toDto(saved);
    }

    private VotingDates toDto(VotingDatesEntity entity) {
        VotingDates dto = new VotingDates();
        dto.setPartyCreationDeadline(entity.getPartyCreationDeadline());
        dto.setVotingDay(entity.getVotingDay());
        return dto;
    }
}

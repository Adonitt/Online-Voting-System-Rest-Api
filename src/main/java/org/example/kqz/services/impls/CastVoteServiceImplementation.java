package org.example.kqz.services.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.dtos.votes.VoteResponseDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.example.kqz.exceptions.*;
import org.example.kqz.mappers.VoteMapper;
import org.example.kqz.repositories.CandidatesRepository;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.repositories.UserRepository;
import org.example.kqz.repositories.VoteRepository;
import org.example.kqz.services.interfaces.CastVoteService;
import org.example.kqz.services.interfaces.EmailService;
import org.example.kqz.services.interfaces.VotingDatesService; // <-- service to get voting dates dynamically
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CastVoteServiceImplementation implements CastVoteService {

    private final PartyRepository partyRepository;
    private final CandidatesRepository candidatesRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteMapper voteMapper;
    private final EmailService emailService;
    private final VotingDatesService votingDatesService;

    @Override
    @Transactional
    public VoteResponseDto castVote(VoteRequestDto dto) {

        LocalDate votingDay = votingDatesService.getCurrentVotingDates().getVotingDay();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = votingDay.atStartOfDay();
        LocalDateTime end = votingDay.atTime(23, 59, 59);

        if (now.isBefore(start)) {
            throw new ItIsNotTheVotingDayException("Voting has not started yet.");
        }
        if (now.isAfter(end)) {
            throw new ItIsNotTheVotingDayException("Voting has already closed.");
        }

        PartyEntity party = partyRepository.findById(dto.getParty())
                .orElseThrow(() ->
                        new PartyNotFoundException("Party not found, id=" + dto.getParty()));

        UserEntity user = validateVoteRequest(dto, party);

        List<CandidatesEntity> candidates =
                candidatesRepository.findAllById(dto.getCandidates());

        if (candidates.stream().anyMatch(c -> !c.getParty().equals(party))) {
            throw new RuntimeException("At least one selected candidate is not in the chosen party.");
        }

        VoteEntity vote = new VoteEntity();
        vote.setUser(user);
        vote.setParty(party);
        vote.setCandidates(candidates);
        vote.setTimeStamp(now);

        user.setHasVoted(true);

        voteRepository.save(vote);
        userRepository.save(user);

        emailService.sendVoteConfirmationEmail(
                user.getEmail(),
                user.getFirstName() + " " + user.getLastName(),
                party,
                candidates
        );

        return voteMapper.toResponseDto(vote);
    }

    private UserEntity validateVoteRequest(VoteRequestDto dto, PartyEntity party) {

        String email = AuthServiceImplementation.getLoggedInUserEmail();

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getBirthDate() == null ||
                user.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("You must be at least 18 years old to vote.");
        }

        if (user.isHasVoted()) {
            throw new AlreadyVotedException("You have already voted.");
        }

        List<Long> candidateIds = dto.getCandidates();
        if (candidateIds.size() < 1 || candidateIds.size() > 10) {
            throw new MustChooseBetween1And10Candidates("Select between 1 and 10 candidates.");
        }

        List<CandidatesEntity> fetched = candidatesRepository.findAllById(candidateIds);
        if (fetched.size() != candidateIds.size()) {
            throw new RuntimeException("One or more candidates not found.");
        }

        return user;
    }
}

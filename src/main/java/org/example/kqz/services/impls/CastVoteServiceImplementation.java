package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.votes.VoteRequestDto;
import org.example.kqz.dtos.votes.VoteResponseDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.example.kqz.exceptions.AlreadyVotedException;
import org.example.kqz.exceptions.MustBe18ToVote;
import org.example.kqz.exceptions.MustChooseBetween1And10Candidates;
import org.example.kqz.exceptions.PartyNotExistsException;
import org.example.kqz.mappers.VoteMapper;
import org.example.kqz.repositories.CandidatesRepository;
import org.example.kqz.repositories.PartyRepository;
import org.example.kqz.repositories.UserRepository;
import org.example.kqz.repositories.VoteRepository;
import org.example.kqz.services.interfaces.CastVoteService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CastVoteServiceImplementation implements CastVoteService {
    private final PartyRepository partyRepository;
    private final CandidatesRepository candidatesRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VoteMapper voteMapper;

    @Override
    public VoteResponseDto castVote(VoteRequestDto voteRequestDto) {
        String userEmail = AuthServiceImplementation.getLoggedInUserEmail();

        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getBirthDate() == null || user.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MustBe18ToVote("You must be 18 or older to vote.");
        }

        if (user.isHasVoted()) {
            throw new AlreadyVotedException("You have already voted.");
        }

        PartyEntity party = partyRepository.findById(voteRequestDto.getPartyId())
                .orElseThrow(() -> new PartyNotExistsException("Party not found with ID: " + voteRequestDto.getPartyId()));

        List<Long> candidateIds = voteRequestDto.getCandidateIds();
        if (candidateIds.size() < 1 || candidateIds.size() > 10) {
            throw new MustChooseBetween1And10Candidates("You must select between 1 and 10 candidates.");
        }

        List<CandidatesEntity> candidates = candidatesRepository.findAllById(candidateIds);
        if (candidates.size() != candidateIds.size()) {
            throw new RuntimeException("Some candidates were not found.");
        }

        for (CandidatesEntity candidate : candidates) {
            if (!candidate.getParty().getId().equals(party.getId())) {
                throw new RuntimeException("Candidate " + candidate.getId() + " does not belong to the selected party.");
            }
        }

        VoteEntity vote = voteMapper.toEntity(user, party, candidates);
        vote.setTimeStamp(LocalDateTime.now());
        user.setHasVoted(true);
        vote.setUser(user);
        VoteEntity savedVote = voteRepository.save(vote);

        return voteMapper.toResponseDto(savedVote);
    }


}

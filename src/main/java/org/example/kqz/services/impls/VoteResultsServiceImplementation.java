package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.dtos.results.CandidateVoteResultDto;
import org.example.kqz.dtos.results.CityVoteSummaryDto;
import org.example.kqz.dtos.results.PartyVoteResultsDto;
import org.example.kqz.dtos.results.UserVoteDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.VoteEntity;
import org.example.kqz.entities.enums.CityEnum;
import org.example.kqz.repositories.VoteRepository;
import org.example.kqz.services.interfaces.VoteResultsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VoteResultsServiceImplementation implements VoteResultsService {
    private final VoteRepository voteRepository;

    public List<PartyVoteResultsDto> getPartyResults() {
        List<VoteEntity> allVotes = voteRepository.findAll();

        // Count distinct users who voted
        long totalVoters = allVotes.stream()
                .map(vote -> vote.getUser().getId())
                .distinct()
                .count();

        // Group by party and count votes
        Map<PartyEntity, Long> counts = allVotes.stream()
                .collect(Collectors.groupingBy(VoteEntity::getParty, Collectors.counting()));

        return counts.entrySet().stream()
                .map(e -> {
                    Long partyVotes = e.getValue();
                    double percentage = totalVoters == 0 ? 0 :
                            (partyVotes * 100.0) / totalVoters;

                    return new PartyVoteResultsDto(
                            e.getKey().getId(),
                            e.getKey().getName(),
                            partyVotes,
                            percentage
                    );
                })
                .toList();
    }

    public PartyVoteResultsDto getPartyResultById(Long partyId) {
        List<VoteEntity> allVotes = voteRepository.findAll();

        Map<PartyEntity, Long> counts = allVotes.stream()
                .collect(Collectors.groupingBy(VoteEntity::getParty, Collectors.counting()));

        long totalVotes = allVotes.size();

        return counts.entrySet().stream()
                .filter(entry -> entry.getKey().getId().equals(partyId))
                .map(entry -> new PartyVoteResultsDto(
                        entry.getKey().getId(),
                        entry.getKey().getName(),
                        entry.getValue(),
                        (entry.getValue() * 100.0) / totalVotes
                ))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Party with ID " + partyId + " has no votes or does not exist"));
    }

    public List<CandidateVoteResultDto> getCandidateResults() {
        List<VoteEntity> allVotes = voteRepository.findAll();

        Map<CandidatesEntity, Long> counts = allVotes.stream()
                .flatMap(vote -> vote.getCandidates().stream())
                .collect(Collectors.groupingBy(candidate -> candidate, Collectors.counting()));

        return counts.entrySet().stream()
                .map(e -> {
                    CandidatesEntity c = e.getKey();
                    PartyEntity p = c.getParty();
                    return new CandidateVoteResultDto(
                            c.getId(),
                            c.getFirstName(),
                            c.getLastName(),
                            p.getId(),
                            p.getName(),
                            e.getValue()
                    );
                })
                .toList();
    }

    public List<UserVoteDto> getUserVotesById(Long userId) {
        List<VoteEntity> votes = voteRepository.findByUserId(userId);

        return votes.stream().map(vote -> {
            List<String> candidateNames = vote.getCandidates().stream()
                    .map(candidate -> candidate.getFirstName() + " " + candidate.getLastName())
                    .toList();

            return new UserVoteDto(
                    vote.getUser().getId(),
                    vote.getUser().getFirstName() + " " + vote.getUser().getLastName(),
                    vote.getParty().getId(),
                    vote.getParty().getName(),
                    candidateNames,
                    vote.getTimeStamp()
            );
        }).toList();
    }

    public List<UserVoteDto> getAllUserVotes() {
        List<VoteEntity> votes = voteRepository.findAll();
        // 2. Map each VoteEntity → UserVoteDto
        return votes.stream().map(vote -> {
            List<String> candidateNames = vote.getCandidates()
                    .stream()
                    .map(c -> c.getFirstName() + " " + c.getLastName())
                    .toList();

            return new UserVoteDto(
                    vote.getUser().getId(),
                    vote.getUser().getFirstName() + " " + vote.getUser().getLastName(),
                    vote.getParty().getId(),
                    vote.getParty().getName(),
                    candidateNames,
                    vote.getTimeStamp()
            );
        }).toList();
    }


    public List<CityVoteSummaryDto> getCityPartyVoteSummaryByCity() {
        var votes = voteRepository.findAll();

        Map<CityEnum, Map<String, Long>> cityPartyVotes = votes.stream()
                .filter(v -> v.getUser() != null && v.getUser().getCity() != null && v.getParty() != null)
                .collect(Collectors.groupingBy(
                        v -> v.getUser().getCity(),
                        Collectors.groupingBy(
                                v -> v.getParty().getName(),
                                Collectors.counting()
                        )
                ));

        List<CityVoteSummaryDto> result = new ArrayList<>();

        for (CityEnum city : CityEnum.values()) {
            Map<String, Long> partyVotes = cityPartyVotes.getOrDefault(city, Map.of());
            long totalVotesInCity = partyVotes.values().stream().mapToLong(Long::longValue).sum();

            for (var entry : partyVotes.entrySet()) {
                String partyName = entry.getKey();
                long count = entry.getValue();
                double percentage = totalVotesInCity > 0 ? (count * 100.0) / totalVotesInCity : 0;

                result.add(new CityVoteSummaryDto(city, partyName, count, percentage));
            }
        }

        return result;
    }


}

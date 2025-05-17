package org.example.kqz.mappers;

import org.example.kqz.dtos.votes.VoteResponseDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.entities.UserEntity;
import org.example.kqz.entities.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    VoteEntity toEntity(UserEntity user, PartyEntity party, List<CandidatesEntity> candidates);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "party", source = "party")
    @Mapping(target = "candidates", source = "candidates")
    VoteResponseDto toResponseDto(VoteEntity entity);

    // Map UserEntity to email String
    default String map(UserEntity user) {
        return user == null ? null : user.getEmail();
    }

    // Map PartyEntity to party ID Long
    default Long map(PartyEntity party) {
        return party == null ? null : party.getId();
    }

    // Map a single CandidatesEntity to its ID
    default Long map(CandidatesEntity candidate) {
        return candidate == null ? null : candidate.getId();
    }

    // Map a list of CandidatesEntity to list of Long (candidate IDs)
    default List<Long> map(List<CandidatesEntity> candidates) {
        if (candidates == null) return null;
        return candidates.stream()
                .map(this::map)
                .toList();
    }
}


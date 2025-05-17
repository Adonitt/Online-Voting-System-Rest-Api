package org.example.kqz.mappers;

import org.example.kqz.dtos.votes.VoteRequestDto;
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
    @Mapping(target = "timeStamp", expression = "java(java.time.LocalDateTime.now())")
    VoteEntity toEntity(UserEntity user, PartyEntity party, List<CandidatesEntity> candidates);

    VoteResponseDto toResponseDto(VoteEntity vote);

    // Mapping ndihmës për PartyEntity -> Long (id)
    default Long map(PartyEntity party) {
        if (party == null) {
            return null;
        }
        return party.getId();
    }
}

package org.example.kqz.mappers;

import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.entities.CandidatesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CandidatesMapper extends ZSimpleMapper<CandidatesEntity, CRDCandidateRequestDto> {

}

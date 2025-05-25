package org.example.kqz.mappers;

import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface CandidatesMapper extends ZSimpleMapper<CandidatesEntity, CRDCandidateRequestDto> {

    @Override
    @Mapping(source = "party.id", target = "party")
    CRDCandidateRequestDto toDto(CandidatesEntity entity);

    @Override
    @Mapping(source = "party", target = "party")
    CandidatesEntity toEntity(CRDCandidateRequestDto dto);

    UpdateCandidateRequestDto toUpdateDto(CandidatesEntity entity);

    // Metodë ndihmëse për MapStruct - konverton PartyEntity në Long (party id)
    default Long map(PartyEntity party) {
        if (party == null) {
            return null;
        }
        return party.getId();
    }

    // Metodë ndihmëse për MapStruct - konverton Long në PartyEntity
    default PartyEntity map(Long partyId) {
        if (partyId == null) {
            return null;
        }
        PartyEntity party = new PartyEntity();
        party.setId(partyId);
        return party;
    }

    // Map MultipartFile (DTO) to String (Entity)
    default String map(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        return file.getOriginalFilename(); // or any logic for filename
    }

    // Optional: Map String (Entity) back to MultipartFile (DTO) if needed
    default MultipartFile map(String filename) {
        return null; // or throw new UnsupportedOperationException();
    }
}


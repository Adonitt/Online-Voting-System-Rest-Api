package org.example.kqz.mappers;

import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.entities.PartyEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartiesMapper extends ZSimpleMapper<PartyEntity, CRDPartyRequestDto> {

    @BeanMapping(qualifiedByName = "partyDto")
    List<PartyListingDto> toPartyListingDto(List<PartyEntity> entityList);

    UpdatePartyDto toUpdatePartyDto(PartyEntity entity);

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

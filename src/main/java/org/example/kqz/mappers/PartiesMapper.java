package org.example.kqz.mappers;

import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.entities.PartyEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartiesMapper extends ZSimpleMapper<PartyEntity, CRDPartyRequestDto> {

    @BeanMapping(qualifiedByName = "partyDto")
    List<PartyListingDto> toPartyListingDto(List<PartyEntity> entityList);

    UpdatePartyDto toUpdatePartyDto(PartyEntity entity);

}

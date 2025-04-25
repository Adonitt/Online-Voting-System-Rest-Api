package org.example.kqz.mappers;

import org.example.kqz.dtos.parties.CrudPartyRequestDto;
import org.example.kqz.entities.PartyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartiesMapper extends ZSimpleMapper<PartyEntity, CrudPartyRequestDto> {

}

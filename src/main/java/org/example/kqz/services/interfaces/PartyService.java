package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyDetailsDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.services.base_services.*;
import org.springframework.web.multipart.MultipartFile;

public interface PartyService extends
        FindAll<PartyListingDto>,
        FindById<PartyDetailsDto, Long>,
        Removable<Long> {
    CRDPartyRequestDto create(CRDPartyRequestDto dto, MultipartFile symbol);

    UpdatePartyDto modify(UpdatePartyDto dto, Long id,MultipartFile symbol);
}
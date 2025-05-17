package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.parties.CRDPartyRequestDto;
import org.example.kqz.dtos.parties.PartyListingDto;
import org.example.kqz.dtos.parties.UpdatePartyDto;
import org.example.kqz.services.base_services.*;

public interface PartyService extends
        FindAll<PartyListingDto>,
        FindById<CRDPartyRequestDto, Long>,
        Addable<CRDPartyRequestDto>,
        Modifiable<UpdatePartyDto, Long>,
        Removable<Long> {
}
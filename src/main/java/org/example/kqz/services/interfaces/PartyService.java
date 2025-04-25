package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.parties.CrudPartyRequestDto;
import org.example.kqz.services.base_services.*;

public interface PartyService extends
        FindAll<CrudPartyRequestDto>,
        FindById<CrudPartyRequestDto, Long>,
        Addable<CrudPartyRequestDto>,
        Modifiable<CrudPartyRequestDto, Long>,
        Removable<Long> {
}
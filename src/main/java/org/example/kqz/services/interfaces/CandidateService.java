package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.services.base_services.*;

public interface CandidateService extends
        FindAll<CRDCandidateRequestDto>,
        FindById<CRDCandidateRequestDto, Long>,
        Addable<CRDCandidateRequestDto>,
        Modifiable<UpdateCandidateRequestDto, Long>,
        Removable<Long> {
}

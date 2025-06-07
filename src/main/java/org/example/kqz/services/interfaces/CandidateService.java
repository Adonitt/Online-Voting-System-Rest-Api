package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.candidates.CRDCandidateRequestDto;
import org.example.kqz.dtos.candidates.CandidateListingDto;
import org.example.kqz.dtos.candidates.UpdateCandidateRequestDto;
import org.example.kqz.services.base_services.*;
import org.springframework.web.multipart.MultipartFile;

public interface CandidateService extends
        FindAll<CandidateListingDto>,
        FindById<CRDCandidateRequestDto, Long>,
        Removable<Long> {
    CRDCandidateRequestDto add(CRDCandidateRequestDto dto, MultipartFile photo);
    UpdateCandidateRequestDto modify(UpdateCandidateRequestDto dto, Long id, MultipartFile photo);
}

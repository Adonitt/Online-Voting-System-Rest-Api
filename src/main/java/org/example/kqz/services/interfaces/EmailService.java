package org.example.kqz.services.interfaces;

import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;

import java.util.List;

public interface EmailService {
    void sendVoteConfirmationEmail(String toEmail,
                                   String fullName,
                                   PartyEntity party,
                                   List<CandidatesEntity> candidates);

    void sendLoginAlert(String toEmail, String fullName);

     void sendRegisteredUserEmail(String toEmail, String fullName);
}

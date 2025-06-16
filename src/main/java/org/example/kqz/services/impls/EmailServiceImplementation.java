package org.example.kqz.services.impls;

import lombok.RequiredArgsConstructor;
import org.example.kqz.entities.CandidatesEntity;
import org.example.kqz.entities.PartyEntity;
import org.example.kqz.services.interfaces.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService {

    private final JavaMailSender mailSender;

    public void sendVoteConfirmationEmail(String toEmail,
                                          String fullName,
                                          PartyEntity party,
                                          List<CandidatesEntity> candidates) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("kqz@rks-gov.org");
        msg.setTo(toEmail);
        msg.setSubject("Vote confirmation");

        msg.setText(buildBody(fullName, party, candidates));
        mailSender.send(msg);

    }

    @Override
    public void sendLoginAlert(String toEmail, String fullName) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Login Attempt Alert");

        message.setText("""
                    Dear %s,
                
                    We noticed a failed login attempt to your account.
                
                    If this was you, no action is needed. If not, we recommend resetting your password.
                
                    Best regards,
                    Online Voting System
                """.formatted(fullName != null ? fullName : "User"));

        message.setFrom("kqz@rks-gov.org");
        mailSender.send(message);
    }

    private String buildBody(String fullName,
                             PartyEntity party,
                             List<CandidatesEntity> candidates) {

        String candidateLines = candidates.isEmpty()
                ? "(no individual candidates were selected)"
                : candidates.stream()
                .map(c -> "- " + c.getFirstName() + " " + c.getLastName())
                .collect(Collectors.joining("\n"));

        return """
                Dear %s,
                
                Your vote was recorded on %s.
                
                • Party: %s
                • Candidates:
                %s
                
                Thank you for taking part in these elections.
                
                — Online Voting System
                """.formatted(
                fullName == null ? "voter" : fullName,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy‑MM‑dd HH:mm")),
                party.getName(),
                candidateLines
        );
    }
}

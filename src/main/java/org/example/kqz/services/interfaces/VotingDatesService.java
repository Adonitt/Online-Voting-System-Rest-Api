package org.example.kqz.services.interfaces;

import org.example.kqz.dtos.votes.VotingDates;

import java.util.List;

public interface VotingDatesService {

    List<VotingDates> getAllVotingDates();

    public VotingDates createVotingDates(VotingDates votingDates);


    VotingDates updateVotingDates(Long id, VotingDates votingDates);

    VotingDates getCurrentVotingDates();

}

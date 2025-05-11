CREATE TABLE vote_candidates (
                                 vote_id BIGINT NOT NULL,
                                 candidate_id BIGINT NOT NULL,
                                 CONSTRAINT pk_vote_candidates PRIMARY KEY (vote_id, candidate_id),
                                 CONSTRAINT FK_VOTE_CANDIDATES_ON_VOTE FOREIGN KEY (vote_id) REFERENCES votes (vote_id),
                                 CONSTRAINT FK_VOTE_CANDIDATES_ON_CANDIDATE FOREIGN KEY (candidate_id) REFERENCES candidates (candidate_id)
);

ALTER TABLE votes
DROP
CONSTRAINT fk_votes_on_candidate;

ALTER TABLE vote_candidates
DROP
CONSTRAINT pk_vote_candidates;

ALTER TABLE votes
DROP
COLUMN candidate_id;
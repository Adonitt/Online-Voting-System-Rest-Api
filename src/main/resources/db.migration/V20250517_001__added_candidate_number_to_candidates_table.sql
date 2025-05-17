ALTER TABLE candidates
    ADD candidate_number BIGINT;

ALTER TABLE candidates
    ALTER COLUMN candidate_number SET NOT NULL;
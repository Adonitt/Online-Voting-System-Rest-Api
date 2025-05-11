ALTER TABLE candidates
    ADD nationality VARCHAR(255);

ALTER TABLE candidates
    ALTER COLUMN nationality SET NOT NULL;
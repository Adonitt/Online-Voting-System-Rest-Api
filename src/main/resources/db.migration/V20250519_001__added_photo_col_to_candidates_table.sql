ALTER TABLE candidates
    ADD photo VARCHAR(255);

ALTER TABLE candidates
    ALTER COLUMN photo SET NOT NULL;
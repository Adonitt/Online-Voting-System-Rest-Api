ALTER TABLE parties
    ADD abbreviation_name VARCHAR(255);

ALTER TABLE parties
    ALTER COLUMN abbreviation_name SET NOT NULL;

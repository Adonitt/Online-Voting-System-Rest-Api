ALTER TABLE candidates
    ADD birth_date date;

ALTER TABLE candidates
    ALTER COLUMN birth_date SET NOT NULL;
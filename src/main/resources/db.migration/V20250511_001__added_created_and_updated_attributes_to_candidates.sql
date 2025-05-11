ALTER TABLE candidates
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE candidates
    ADD created_by VARCHAR(255);

ALTER TABLE candidates
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE candidates
    ADD updated_by VARCHAR(255);

ALTER TABLE candidates
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE candidates
    ALTER COLUMN created_by SET NOT NULL;
ALTER TABLE parties
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE parties
    ADD created_by VARCHAR(255);

ALTER TABLE parties
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE parties
    ADD updated_by VARCHAR(255);

ALTER TABLE parties
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE parties
    ALTER COLUMN created_by SET NOT NULL;
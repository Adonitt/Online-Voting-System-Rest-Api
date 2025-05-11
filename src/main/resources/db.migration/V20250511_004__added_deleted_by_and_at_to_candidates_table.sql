ALTER TABLE candidates
    ADD deleted_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE candidates
    ADD deleted_by VARCHAR(255);
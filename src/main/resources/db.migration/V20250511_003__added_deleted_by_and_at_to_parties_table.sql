ALTER TABLE parties
    ADD deleted_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE parties
    ADD deleted_by VARCHAR(255);
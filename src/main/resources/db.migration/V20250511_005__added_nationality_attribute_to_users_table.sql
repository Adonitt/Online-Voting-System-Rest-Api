ALTER TABLE users
    ADD nationality VARCHAR(255);

ALTER TABLE users
    ALTER COLUMN nationality SET NOT NULL;
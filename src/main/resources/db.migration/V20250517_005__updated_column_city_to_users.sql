ALTER TABLE users
    DROP
        COLUMN city;

ALTER TABLE users
    ADD city VARCHAR(255);
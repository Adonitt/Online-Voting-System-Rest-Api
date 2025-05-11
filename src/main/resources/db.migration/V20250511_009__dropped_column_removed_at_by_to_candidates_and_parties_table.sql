ALTER TABLE candidates
DROP
COLUMN deleted_at;

ALTER TABLE candidates
DROP
COLUMN deleted_by;

ALTER TABLE parties
DROP
COLUMN deleted_at;

ALTER TABLE parties
DROP
COLUMN deleted_by;
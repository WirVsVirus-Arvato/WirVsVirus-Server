ALTER TABLE people ADD status VARCHAR NOT NULL DEFAULT 'CREATED';
ALTER TABLE people ADD creation_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
CREATE TABLE answer
(
    id  BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question(id),
    people_id BIGINT NOT NULL REFERENCES people(id),
    content TEXT,
    timestamp TIMESTAMP DEFAULT current_timestamp
);

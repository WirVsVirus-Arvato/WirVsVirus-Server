CREATE TABLE text_lang(
    id BIGSERIAL PRIMARY KEY,
    language_id BIGINT NOT NULL,
    text TEXT NOT NULL
);

CREATE TABLE questionnaire(
    id BIGSERIAL PRIMARY KEY,
    title BIGINT NOT NULL REFERENCES text_lang(id)
);

CREATE TYPE question_type AS ENUM ('NUMERIC10');

CREATE TABLE question(
    id BIGSERIAL PRIMARY KEY,
    questionnaire_id BIGINT NOT NULL REFERENCES questionnaire(id),
    type question_type NOT NULL DEFAULT 'NUMERIC10',
    question_text BIGINT NOT NULL REFERENCES text_lang(id)
);



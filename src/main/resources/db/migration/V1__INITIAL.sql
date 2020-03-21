CREATE TABLE text_lang(
    id BIGSERIAL PRIMARY KEY,
    language_id BIGINT NOT NULL,
    text TEXT NOT NULL
);

CREATE TABLE questionnaire(
    id BIGSERIAL PRIMARY KEY,
    text_id BIGINT NOT NULL REFERENCES text_lang(id)
);

CREATE TABLE question(
    id BIGSERIAL PRIMARY KEY,
    questionnaire_id BIGINT NOT NULL REFERENCES questionnaire(id),
    type_id BIGINT NOT NULL,
    text_id BIGINT NOT NULL REFERENCES text_lang(id)
);



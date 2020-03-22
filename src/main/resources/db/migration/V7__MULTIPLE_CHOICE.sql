CREATE TABLE multiple_choice_answer (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL REFERENCES question(id),
    answer BIGINT NOT NULL REFERENCES text_lang(id)
);

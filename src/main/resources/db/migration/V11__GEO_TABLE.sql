CREATE TABLE geo (
    id BIGSERIAL PRIMARY KEY,
    longitude DECIMAL,
    latitude Decimal,
    people_id BIGINT REFERENCES people(id)
);

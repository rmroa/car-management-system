CREATE TABLE IF NOT EXISTS manufacturer
(
    id      SERIAL PRIMARY KEY,
    brand   VARCHAR(60) NOT NULL UNIQUE,
    country VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS vehicle_type
(
    id   SERIAL PRIMARY KEY,
    type VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS models
(
    id              BIGSERIAL PRIMARY KEY,
    model           VARCHAR(60) NOT NULL,
    manufacturer_id BIGINT REFERENCES manufacturer (id) ON DELETE CASCADE,
    production_year DATE        NOT NULL,
    vehicle_type_id BIGINT REFERENCES vehicle_type (id) ON DELETE CASCADE,
    transmission    VARCHAR(30) NOT NULL,
    drive_unit      VARCHAR(30) NOT NULL,
    engine_type     VARCHAR(30) NOT NULL,
    current_mileage BIGINT,
    price           NUMERIC     NOT NULL
);
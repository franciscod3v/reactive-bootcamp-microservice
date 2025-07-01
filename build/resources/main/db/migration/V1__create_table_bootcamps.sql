CREATE TABLE IF NOT EXISTS bootcamps (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    launch_date DATE NOT NULL,
    duration_in_days INTEGER NOT NULL
);

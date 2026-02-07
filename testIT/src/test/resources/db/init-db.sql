CREATE SCHEMA IF NOT EXISTS loans;

CREATE TABLE IF NOT EXISTS loans.personal_loan_application (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    personal_id VARCHAR NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    badge VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    modified_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

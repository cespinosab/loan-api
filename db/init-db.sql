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

INSERT INTO loans.personal_loan_application (id, first_name, last_name, personal_id, amount, badge, status, created_at, modified_at)
VALUES
    (nextval('loans.personal_loan_application_id_seq'), 'Cliente1', 'Apellido1', '12345678-A', 2000.0, 'EUR', 'PENDING', '2026-02-05T11:20:00','2026-02-05T11:20:00'),
    (nextval('loans.personal_loan_application_id_seq'), 'Cliente2', 'Apellido2', '91234567-B', 3000.0, 'EUR', 'APPROVED', '2026-02-05T11:20:00','2026-02-05T11:20:00'),
    (nextval('loans.personal_loan_application_id_seq'), 'Cliente3', 'Apellido3', '91234567-C', 3000.0, 'EUR', 'CANCELLED', '2026-02-05T11:20:00','2026-02-05T11:20:00')
;

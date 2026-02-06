INSERT INTO loans.personal_loan_application (id, first_name, last_name, personal_id, amount, badge,status, created_at, modified_at)
VALUES
    ((1L, "Cliente 1", "Apellido1", "12345678-A", "2000", "EUR", "PENDING", TIMESTAMP.now(), TIMESTAMP.now()),
     (2L, "Cliente 2", "Apellido2", "91234567-B", "3000", "EUR", "APPROVED", TIMESTAMP.now(), TIMESTAMP.now())
);

INSERT INTO loans.personal_loan_application (id, first_name, last_name, personal_id, amount, badge, status, created_at, modified_at)
VALUES
    ((1L, "Cliente1", "Apellido1", "12345678-A", "2000", "EUR", "PENDING", TIMESTAMP.now(), TIMESTAMP.now()),
     (2L, "Cliente2", "Apellido2", "91234567-B", "3000", "EUR", "APPROVED", TIMESTAMP.now(), TIMESTAMP.now())
);

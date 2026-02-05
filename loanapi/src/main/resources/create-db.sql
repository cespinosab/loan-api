CREATE TABLE IF NOT EXISTS loans.person_loan_application (
    id bigserial not null primary key,
    first_name varchar not null,
    last_name varchar not null,
    personal_id varchar not null,
    amount decimal not null,
    badge varchar not null,
    status varchar not null,
    created_at timestamp without zone not null,
    modified_at timestamp without zone not null
);
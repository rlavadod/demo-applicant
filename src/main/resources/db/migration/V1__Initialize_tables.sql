CREATE TABLE applicant_seq (
 next_val bigint
);

CREATE TABLE applicant (
    id bigint NOT NULL PRIMARY KEY,
    name varchar(100),
    lastname varchar(100),
    birthdate date
);

insert into applicant_seq values ( 1 );
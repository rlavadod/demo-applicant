CREATE TABLE users_seq (
  next_val bigint
);

CREATE TABLE users (
  id int NOT NULL PRIMARY KEY,
  email varchar(100) NOT NULL,
  name varchar(100) NOT NULL,
  password varchar(255) NOT NULL,
  UNIQUE KEY (email)
);

insert into users_seq values ( 1 );

CREATE TABLE token_seq (
  next_val bigint
);

CREATE TABLE token (
  id int NOT NULL PRIMARY KEY,
  is_expired bit(1) NOT NULL,
  is_revoked bit(1) NOT NULL,
  user_id int DEFAULT NULL,
  token varchar(255) DEFAULT NULL,
  token_type enum('BEARER') DEFAULT NULL,
  UNIQUE KEY (token),
  KEY (user_id),
  CONSTRAINT FK_TOKEN_USERS_ID FOREIGN KEY (user_id) REFERENCES users(id)
);

insert into token_seq values ( 1 );
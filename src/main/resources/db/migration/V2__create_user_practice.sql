CREATE TABLE user_practice
(
    id         VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    username   VARCHAR(40)  NOT NULL,
    CONSTRAINT pk_userpractice PRIMARY KEY (id)
);

ALTER TABLE user_practice
    ADD CONSTRAINT uc_userpractice_username UNIQUE (username);
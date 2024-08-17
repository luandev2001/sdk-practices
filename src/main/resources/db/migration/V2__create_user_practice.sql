CREATE TABLE user_practice
(
    id         VARCHAR(36) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    username   VARCHAR(40)  NOT NULL,
    CONSTRAINT pk_userpractice PRIMARY KEY (id)
);

ALTER TABLE user_practice
    ADD CONSTRAINT uc_userpractice_username UNIQUE (username);
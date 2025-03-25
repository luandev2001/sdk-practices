CREATE TABLE user_practice
(
    id         VARCHAR(36) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    username   VARCHAR(40)  NOT NULL,
    CONSTRAINT pk_userpractice PRIMARY KEY (id)
);
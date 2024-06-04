CREATE TABLE configuration
(
    id         VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    value      JSONB,
    name       VARCHAR(50)  NOT NULL,
    type       VARCHAR(25)  NOT NULL,
    is_edit    BOOLEAN,
    data_type  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_configuration PRIMARY KEY (id)
);

CREATE TABLE confirmation_object
(
    id         VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    updated_by VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    object     VARCHAR(255) NOT NULL,
    object_id  VARCHAR(255) NOT NULL,
    token      VARCHAR(50)  NOT NULL,
    expired_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    type       VARCHAR(20)  NOT NULL,
    CONSTRAINT pk_confirmationobject PRIMARY KEY (id)
);

CREATE TABLE data_sequence
(
    id             VARCHAR(255) NOT NULL,
    created_by     VARCHAR(255),
    updated_by     VARCHAR(255),
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    class_name     VARCHAR(255) NOT NULL,
    sequence_value VARCHAR(255) NOT NULL,
    type           INTEGER,
    CONSTRAINT pk_datasequence PRIMARY KEY (id)
);
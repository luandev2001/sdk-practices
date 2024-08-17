CREATE TABLE configuration
(
    id         VARCHAR(36) NOT NULL,
    created_at TIMESTAMPTZ,
    updated_at TIMESTAMPTZ,
    value      TEXT,
    name       VARCHAR(50) NOT NULL,
    type       VARCHAR(25) NOT NULL,
    is_edit    BOOLEAN,
    data_type  VARCHAR(20) NOT NULL,
    CONSTRAINT pk_configuration PRIMARY KEY (id)
);

CREATE TABLE confirmation_object
(
    id          VARCHAR(36) NOT NULL,
    created_at  TIMESTAMPTZ,
    updated_at  TIMESTAMPTZ,
    object_type VARCHAR(20) NOT NULL,
    object_id   VARCHAR(36) NOT NULL,
    token       VARCHAR(50) NOT NULL,
    expired_at  TIMESTAMPTZ NOT NULL,
    type        VARCHAR(20) NOT NULL,
    CONSTRAINT pk_confirmationobject PRIMARY KEY (id)
);

CREATE TABLE data_sequence
(
    id          VARCHAR(36) NOT NULL,
    created_at  TIMESTAMPTZ,
    updated_at  TIMESTAMPTZ,
    object_type VARCHAR(20) NOT NULL,
    value       VARCHAR(30) NOT NULL,
    type        INTEGER,
    CONSTRAINT pk_datasequence PRIMARY KEY (id)
);

-- default table
CREATE INDEX idx_configuration ON configuration (type, name);
CREATE INDEX idx_confirmation_object ON confirmation_object (type, object_type, object_id);
CREATE INDEX idx_data_sequence ON data_sequence (object_type);
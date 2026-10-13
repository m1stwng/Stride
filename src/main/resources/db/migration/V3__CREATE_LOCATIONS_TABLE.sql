CREATE TABLE locations
(
    id           UUID PRIMARY KEY DEFAULT GEN_RANDOM_UUID(),

    code         VARCHAR(255) NOT NULL UNIQUE CHECK (code = UPPER(code)),

    warehouse_id UUID REFERENCES warehouses (id) ON DELETE CASCADE,

    UNIQUE (warehouse_id, code)
);

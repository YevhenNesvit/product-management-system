CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO roles (role_id, role_name) VALUES
(uuid_generate_v4(), 'ROLE_ADMIN'),
(uuid_generate_v4(), 'ROLE_USER');